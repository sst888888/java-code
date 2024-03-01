//package com.bootkafka.springbootkafkademo.configuration;
//
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * @link https://www.jianshu.com/p/15f84722ecda
// */
//@Configuration
//public class TransactionConfig {
//
//    private final DataSource dataSource;
//
//    private final TransactionManagerCustomizers transactionManagerCustomizers;
//
//    TransactionConfig(DataSource dataSource,
//                      ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
//        this.dataSource = dataSource;
//        this.transactionManagerCustomizers = transactionManagerCustomizers.getIfAvailable();
//    }
//
//    @Bean
//    @Primary
//    public DataSourceTransactionManager transactionManager(DataSourceProperties properties) {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(this.dataSource);
//        if (this.transactionManagerCustomizers != null) {
//            this.transactionManagerCustomizers.customize(transactionManager);
//        }
//        return transactionManager;
//    }
//
//}
