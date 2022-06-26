package com.example.oom;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Slf4j
public class UserDTO {
    private String name;
    @EqualsAndHashCode.Exclude
    private String payload;

    public UserDTO(String name) {
        this.name = name;
        this.payload = IntStream.rangeClosed(1, 10_000)
                .mapToObj(__ -> "a")
                .collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        UserDTO userDTO = new UserDTO("SST");
        log.info("payload = {}", userDTO.getPayload());
    }
}