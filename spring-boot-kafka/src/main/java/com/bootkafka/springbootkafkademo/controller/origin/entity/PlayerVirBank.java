package com.bootkafka.springbootkafkademo.controller.origin.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.bootkafka.springbootkafkademo.controller.origin.convert.LocalDateTimeConverter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PlayerVirBank implements Serializable {

    @ExcelProperty("id")
    private Long id;

//    @ExcelProperty(value = "create_time", converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private LocalDateTime createTime;

//    @ExcelProperty(value = "modify_time", index = 2, converter = LocalDateTimeConverter.class)
@ExcelProperty()
    private LocalDateTime modifyTime;

    @ExcelProperty()
    private Integer version;

    @ExcelProperty()
    private Integer deleted;


    @ExcelProperty()
    private String vircurrency;

    @ExcelProperty()
    private String protocol;

    @ExcelProperty()
    private String virAddress;


    @ExcelProperty()
    private Integer defaultable;



    @ExcelProperty()
    private Long tenantId;

    @ExcelProperty()
    private Long tenantPlatId;



    @ExcelProperty()
    private Long playerId;

    @ExcelProperty()
    private Long modifierId;


    @ExcelProperty()
    private String modifierUsername;



    @ExcelProperty()
    private Integer dropped;

    @ExcelProperty()
    private String originSign;

}
