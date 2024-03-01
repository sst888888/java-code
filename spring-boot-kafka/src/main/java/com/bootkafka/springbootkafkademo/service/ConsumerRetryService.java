package com.bootkafka.springbootkafkademo.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.FixedDelayStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

/**
 * @since kafka 2.8.11
 * @link https://zhuanlan.zhihu.com/p/554967177
 */
@Service
@AllArgsConstructor
@Slf4j
public class ConsumerRetryService {

    /**
     * 自定义
     * 重试 3 次，间隔为 5s，如果在重试结束后，还没有成功被消费，该消息会被发送到 DLT 中
     */
    @RetryableTopic(
        attempts = "3",
        backoff = @Backoff(delay = 10000, multiplier = 0.0),
//        fixedDelayTopicStrategy = FixedDelayStrategy.MULTIPLE_TOPICS
        fixedDelayTopicStrategy = FixedDelayStrategy.SINGLE_TOPIC
    )
//    @RetryableTopic() // 默认重试 3 次，间隔为 1s，如果在重试结束后，还没有成功被消费，该消息会被发送到 DLT 中
    @KafkaListener(topics = "TOPIC_CP_TEST", groupId = "GROUP_CP")
    public void consume(String message){
        log.info("ConsumerRetryService consumed message is : {}", message);
        throw new RuntimeException("test");
    }

    /**
     * 可以从死信队列消费消息
     */
    @DltHandler
    public void dlt(String message) {
        log.info("ConsumerRetryService dlt consumed message is : {}", message);
    }

    /**
     * 不需要代码
     */
//    @Configuration
//    static class Config {
//        @Bean
//        public ProducerFactory<String, String> producerFactory() {
//            Map<String, Object> configProps = new HashMap<>();
//            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.80.112:9092");
//            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//            return new DefaultKafkaProducerFactory<>(configProps);
//        }
//
//        @Bean(name = "kafkaTemplate")
//        public KafkaTemplate<String, String> kafkaTemplate() {
//            return new KafkaTemplate<>(producerFactory());
//        }
//    }
}
