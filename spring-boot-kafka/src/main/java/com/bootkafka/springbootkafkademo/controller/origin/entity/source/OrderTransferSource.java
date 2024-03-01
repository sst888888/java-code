package com.bootkafka.springbootkafkademo.controller.origin.entity.source;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 同表一致 直接入库 没有boolean转换
 */
@Data
public class OrderTransferSource implements Serializable {

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
    private Long creatorId;



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
    private Long fromPlatId;

    @ExcelProperty()
    private Long toPlatId;

    @ExcelProperty()
    private String thirdOrderNo;

    @ExcelProperty()
    private BigDecimal amountBefore;

    @ExcelProperty()
    private BigDecimal amountAfter;


    @ExcelProperty()
    private String operateRemark;

    @ExcelProperty()
    private String originSign;

}