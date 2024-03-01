package com.bootkafka.springbootkafkademo.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class ProducerTransService {

    private static final String TOPIC = "TOPIC_CP_TEST";


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional(rollbackFor = Exception.class)
    public void sendMessage(String message) {

        this.jdbcTemplate.execute("insert into rpt_stat_settle_bill (id) values ('" + System.currentTimeMillis() + "')");

        // 场景1：发送消息失败 topic里面没有消息记录
        this.kafkaTemplate.send(TOPIC, message);
        int a = 1 / 0;

        // 场景2：发送多个消息，部分发送成功，topic里面有消息记录但是消费者不消费
        for (int i = 1; i < 23; i++) {
            LocalDateTime localDateTime = LocalDateTime.now().withHour(i).withMinute(0).withSecond(0).withNano(0);
            ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(TOPIC, localDateTime.toString());

            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("Send message fail: {}-{}", message, throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, String> stringStringSendResult) {
                    log.info("Send Message success: {}-{}", message, stringStringSendResult.getRecordMetadata().offset());
                }
            });

            if (i == 12) {
                // 抛出一个错误
                int b = 1 / 0;
            }

        }
    }


}
