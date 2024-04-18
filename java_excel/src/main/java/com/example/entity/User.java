package com.example.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.example.converter.GenderConverter;
import lombok.Data;

@Data
public class User {

    @ExcelProperty(index = 0)
    private String name;

    @ExcelProperty(index = 1)
    private int age;

    @ExcelProperty(index = 2)
    private String address;

    @ExcelProperty(index = 3)
    private String work;

    @ExcelProperty(index = 4)
    private String bankcard;

    /**
     * 性别 1：男；2：女
     */
    @ExcelProperty(index = 5, converter = GenderConverter.class)
    private Integer gender;


    /**
     * 出生日期
     */
    @ExcelProperty(index = 6)
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private String birth;
}
