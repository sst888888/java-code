package com.example.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Member4PhoneDTO {

    @Excel(name = "手机号码1")
    private String phone1;


    @Excel(name = "手机号码2")
    private String phone2;


}