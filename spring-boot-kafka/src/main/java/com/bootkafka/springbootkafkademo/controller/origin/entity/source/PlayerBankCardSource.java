package com.bootkafka.springbootkafkademo.controller.origin.entity.source;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerBankCardSource implements Serializable {

    @ExcelProperty("id")
    private Long id;

//    @ExcelProperty(value = "create_time", converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String createTime;

//    @ExcelProperty(value = "modify_time", index = 2, converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String modifyTime;

    @ExcelProperty()
    private Integer version;

    @ExcelProperty()
    private Integer deleted;


    @ExcelProperty()
    private Long modifierId;


    @ExcelProperty()
    private String modifierUsername;

    @ExcelProperty()
    private Long tenantId;

    @ExcelProperty()
    private Long tenantPlatId;

    @ExcelProperty()
    private Long playerId;

    @ExcelProperty()
    private String playerUsername;

    @ExcelProperty()
    private String cardNo;

    @ExcelProperty()
    private String bankName;


    @ExcelProperty()
    private String province;

    @ExcelProperty()
    private String city;

    @ExcelProperty()
    private String address;

    @ExcelProperty()
    private String accountName;

    @ExcelProperty()
    private Integer defaultable;

    @ExcelProperty()
    private Long provinceId;

    @ExcelProperty()
    private Long cityId;

    @ExcelProperty()
    private Long bankId;

    @ExcelProperty()
    private Integer dropped;

    @ExcelProperty()
    private String originSign;

}
