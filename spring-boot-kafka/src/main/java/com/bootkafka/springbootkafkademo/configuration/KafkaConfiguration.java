//package com.bootkafka.springbootkafkademo.configuration;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.listener.ConsumerRecordRecoverer;
//import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
//import org.springframework.kafka.listener.ErrorHandler;
//import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
//import org.springframework.util.backoff.BackOff;
//import org.springframework.util.backoff.FixedBackOff;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Logger;
//
//@Configuration
//@Slf4j
//public class KafkaConfiguration {
//
//    @Bean
//    public ProducerFactory<String, String> producerFactoryString(){
//        Map<String, Object> configProps = new HashMap<>();
//
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.80.112:9092");
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplateString(){
//        return new KafkaTemplate<>(producerFactoryString());
//    }
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory(){
//        Map<String, Object> configProps = new HashMap<>();
//
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.80.112:9092");
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
//
//        return new DefaultKafkaConsumerFactory<>(configProps);
//    }
////
////    @Bean
////    public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory(){
////        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
////        factory.setConsumerFactory(consumerFactory());
////
////        return factory;
////    }
////
////
////
////
////    @Bean
////    @Primary
////    public ErrorHandler kafkaErrorHandler(KafkaTemplate<?, ?> template) {
////
////        log.warn("kafkaErrorHandler begin to Handle");
////
////        // <1> 创建 DeadLetterPublishingRecoverer 对象
////        ConsumerRecordRecoverer recoverer = new DeadLetterPublishingRecoverer(template);
////        // <2> 创建 FixedBackOff 对象   设置重试间隔 10秒 次数为 3次
////        BackOff backOff = new FixedBackOff(10 * 1000L, 3L);
////        // <3> 创建 SeekToCurrentErrorHandler 对象
////        return new SeekToCurrentErrorHandler(recoverer, backOff);
////    }
//
//
//}
