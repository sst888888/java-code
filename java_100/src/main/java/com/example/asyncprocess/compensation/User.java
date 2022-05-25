package com.example.asyncprocess.compensation;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class User implements Serializable {
    private static AtomicLong atomicLong = new AtomicLong();
    private Long id = atomicLong.incrementAndGet();
    private String name = UUID.randomUUID().toString();
    private LocalDateTime registerTime = LocalDateTime.now();
}
