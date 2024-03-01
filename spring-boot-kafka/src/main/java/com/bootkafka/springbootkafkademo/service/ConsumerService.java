//package com.bootkafka.springbootkafkademo.service;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.kafka.annotation.DltHandler;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.listener.ConsumerRecordRecoverer;
//import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
//import org.springframework.kafka.listener.ErrorHandler;
//import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Service;
//import org.springframework.util.backoff.BackOff;
//import org.springframework.util.backoff.FixedBackOff;
//
///**
// * @link https://zhuanlan.zhihu.com/p/554967177
// *      https://dzone.com/articles/spring-for-apache-kafka-deep-dive-part-1-error-han
// *      https://cloud.tencent.com/developer/article/1863357
// */
//@Service
//@AllArgsConstructor
//@Slf4j
//public class ConsumerService {
//
//    /**
//     * 1.达到系统设定的重发次数后 停止发送
//     * 2.kafkaErrorHandler 达到重试次数后 发送到死信队列里面
//     * @param message
//     */
//    @KafkaListener(topics = "TOPIC_CP_TEST", groupId = "GROUP_AGENT_SETTLE")
//    public void consume(String message){
//        log.info("Consumed message is : {}", message);
//
//        throw new RuntimeException("test");
//    }
//
//    @DltHandler
//    public void dlt(String message) {
//        log.info("Dlt consumed message is : {}", message);
//    }
//
//    // 自定义 SeekToCurrentErrorHandler
//    @Configuration
//    static class Config {
//        @Bean
//        @Primary
//        public ErrorHandler kafkaErrorHandler(KafkaTemplate<?, ?> template) {
//            log.warn("kafkaErrorHandler begin to Handle");
//
//            // <1> 创建 DeadLetterPublishingRecoverer 对象
//            ConsumerRecordRecoverer recoverer = new DeadLetterPublishingRecoverer(template);
//            // <2> 创建 FixedBackOff 对象   设置重试间隔 10秒 重试次数为 3次 Consumer总计4次消费
//            BackOff backOff = new FixedBackOff(20 * 1000L, 3L);
//            // <3> 创建 SeekToCurrentErrorHandler 对象
//            return new SeekToCurrentErrorHandler(recoverer, backOff);
//        }
//    }
//
//    @KafkaListener(topics = "TOPIC_CP_TEST", groupId = "GROUP_AGENT_SETTLE")
//    public void consume2(String message/**, Acknowledgment ack**/){
//        log.info("Consumed message is : {}", message);
//
//        // 手动提交offset ，只有在配置文件中配置了手动提交模式，ack才有用
////        ack.acknowledge();
//
//    }
//
//}
