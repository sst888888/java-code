package com.bootkafka.springbootkafkademo.controller.origin.entity.source;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderWithdrawalSource implements Serializable {

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
    private Long auditorId;

    @ExcelProperty()
    private Long tenantId;

    @ExcelProperty()
    private Long tenantPlatId;

    @ExcelProperty()
    private Long playerId;

    @ExcelProperty()
    private String playerUsername;

    @ExcelProperty()
    private Integer status;

    @ExcelProperty()
    private BigDecimal amount;

    @ExcelProperty()
    private Long channelId;

    @ExcelProperty()
    private String accountName;

    @ExcelProperty()
    private String accountNo;

    @ExcelProperty()
    private String bankName;

    @ExcelProperty()
    private String bankProvince;

    @ExcelProperty()
    private String bankCity;

    @ExcelProperty()
    private String bankAddress;

    @ExcelProperty()
    private String thirdOrderNo;

    // 表没有字段
    @ExcelProperty()
    private String thirdTradeDate;


    @ExcelProperty()
    private Long followerId;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
    @ExcelProperty()
    private String finishedTime;


    @ExcelProperty()
    private String remark;

    @ExcelProperty()
    private Integer times;

    @ExcelProperty()
    private Integer device;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String callbackTime;

    @ExcelProperty()
    private String callbackRemark;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
    @ExcelProperty()
    private String auditTime;

    @ExcelProperty()
    private String auditRemark;

    @ExcelProperty()
    private String operateRemark;

    @ExcelProperty()
    private BigDecimal amountBefore;

    @ExcelProperty()
    private BigDecimal amountAfter;

    @ExcelProperty()
    private String ip;

    @ExcelProperty()
    private Long payModeId;

    @ExcelProperty()
    private Long payAccountChangeLogId;

    @ExcelProperty()
    private Long withdrawalTypeId;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private String receiveTime;


    @ExcelProperty()
    private Integer splitFlag;

    @ExcelProperty()
    private Long mainOrderNo;

    @ExcelProperty()
    private BigDecimal feeAmount;

    @ExcelProperty()
    private BigDecimal actAmount;

    @ExcelProperty()
    private Integer auditStep;

    @ExcelProperty()
    private Integer auditTimes;

    // 表没有字段
    @ExcelProperty()
    private String bankCode;


    // 新字段
    @ExcelProperty()
    private BigDecimal auditAmount;

    @ExcelProperty()
    private Integer riskStatus;

    @ExcelProperty()
    private Integer orderRiskType;

    @ExcelProperty()
    private String originSign;

    @ExcelProperty()
    private String ipAttribution;

}
