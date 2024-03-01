package com.bootkafka.springbootkafkademo.controller.origin.entity.source;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class PlayerAccountSource implements Serializable {

    @ExcelProperty("id")
    private Long id;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String createTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String modifyTime;

    @ExcelProperty()
    private Integer version;

    @ExcelProperty()
    private Integer deleted;

    @ExcelProperty()
    private Integer intention;

    @ExcelProperty()
    private BigDecimal balance;

    @ExcelProperty()
    private BigDecimal depositTotalAmount;

    @ExcelProperty()
    private BigDecimal withdrawalTotalAmount;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String firstDepositTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String lastDepositTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String lastWithdrawalTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String lastLoginTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String lastContactTime;

    @ExcelProperty()
    private BigDecimal lastDepositAmount;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String nextFollowTime;

    @ExcelProperty()
    private Integer depositTimes;

    @ExcelProperty()
    private Integer withdrawalTimes;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String lastRebateTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String fetchTime;

    @ExcelProperty()
    private Long tenantPlatId;

    @ExcelProperty()
    private Integer followWechatChange;

    @ExcelProperty()
    private String originSign;
}
