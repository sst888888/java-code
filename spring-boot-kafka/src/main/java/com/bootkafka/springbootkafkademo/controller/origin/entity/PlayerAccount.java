package com.bootkafka.springbootkafkademo.controller.origin.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.bootkafka.springbootkafkademo.controller.origin.convert.LocalDateTimeConverter;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class PlayerAccount  implements Serializable {

    @ExcelProperty("id")
    private Long id;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private LocalDateTime createTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private LocalDateTime modifyTime;

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
    private LocalDateTime firstDepositTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private LocalDateTime lastDepositTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private LocalDateTime lastWithdrawalTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private LocalDateTime lastLoginTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private LocalDateTime lastContactTime;

    @ExcelProperty()
    private BigDecimal lastDepositAmount;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private LocalDateTime nextFollowTime;

    @ExcelProperty()
    private Integer depositTimes;

    @ExcelProperty()
    private Integer withdrawalTimes;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private LocalDateTime lastRebateTime;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private LocalDateTime fetchTime;

    @ExcelProperty()
    private Long tenantPlatId;

    @ExcelProperty()
    private Integer followWechatChange;

    @ExcelProperty()
    private String originSign;
}
