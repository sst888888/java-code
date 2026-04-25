package com.example.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MemberStaticExcelVO {
    @Excel(name = "手机号码")
    private String phone;


}

