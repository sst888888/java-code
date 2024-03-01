package com.bootkafka.springbootkafkademo.controller.origin;


import org.springframework.web.multipart.MultipartFile;

public interface ExeService {

    int BATCH_COUNT = 100;

    boolean exe(MultipartFile file);
}
