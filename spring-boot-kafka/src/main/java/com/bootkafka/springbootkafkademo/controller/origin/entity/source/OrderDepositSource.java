package com.bootkafka.springbootkafkademo.controller.origin.entity.source;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDepositSource implements Serializable {

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
    private Long payTypeId;

    @ExcelProperty()
    private Long payModeId;

    @ExcelProperty()
    private String thirdOrderNo;

    // 表没有字段
    @ExcelProperty()
    private String thirdTradeDate;


    @ExcelProperty()
    private BigDecimal actualAmount;

    @ExcelProperty()
    private Integer device;

    //    @ExcelProperty(converter = LocalDateTimeConverter.class)
    @ExcelProperty()
    private String finishedTime;

    @ExcelProperty()
    private String remark;

    @ExcelProperty()
    private Long bankTransferId;

    @ExcelProperty()
    private Integer times;


//    @ExcelProperty(converter = LocalDateTimeConverter.class)
    @ExcelProperty()
    private String callbackTime;

    @ExcelProperty()
    private String operateRemark;

    @ExcelProperty()
    private BigDecimal amountBefore;

    @ExcelProperty()
    private BigDecimal amountAfter;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
    @ExcelProperty()
    private String registTime;

    @ExcelProperty()
    private Long payAccountChangeLogId;


    @ExcelProperty()
    private String ip;

    @ExcelProperty()
    private String payTypeGroupName;

    @ExcelProperty()
    private String payAcctName;

    @ExcelProperty()
    private String payAcctNo;

    @ExcelProperty()
    private String payBankName;

    // 表没有字段
    @ExcelProperty()
    private String payBankCode;


    @ExcelProperty()
    private String originSign;



}
