package com.bootkafka.springbootkafkademo.controller.origin.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.bootkafka.springbootkafkademo.controller.origin.convert.LocalDateTimeConverter;
import com.bootkafka.springbootkafkademo.controller.origin.convert.LocalDateConverter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class Player implements Serializable {
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer version;

    private Integer deleted;

    private Long tenantId;

    private Long tenantPlatId;

    private Long vipId;

    private String username;

    private String realName;

    private String mobile;

    private String password;

    private Integer registDevice;

    private String registIp;

    private String registAddress;

    private Long channelId;

    private Long followerId;

    private String email;

    private String wechat;

    private Integer agentable;

    private Long rateAgentId;

    private Long rebateAgentId;

    private Long channelPartnerId;

    private String gamePwd;

    private Integer sex;

    private LocalDate birthday;

    private String withdrawalPwd;

    private Integer mobileChecked;

    private Integer enabled;

    private String deviceKey;

    private Integer firstAutoRegist;

    private String mobileAreaCode;

    private Integer type;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
    private LocalDateTime agentTime;
//
//    @ExcelProperty(converter = LocalDateTimeConverter.class)
    private LocalDateTime checkedTime;


    @ExcelProperty()
    private String registDomain;


    @ExcelProperty()
    private Integer speedTransferType;
    @ExcelProperty()
    private String pushAppKey;
    @ExcelProperty()
    private String thirdUserNo;
    @ExcelProperty()
    private String nickName;

    @ExcelProperty()
    private String originSign;

    // TODO
    @ExcelProperty()
    private String mobileAttribution;
}
