package com.bootkafka.springbootkafkademo.controller.origin.entity.source;

import com.alibaba.excel.annotation.ExcelProperty;
import com.bootkafka.springbootkafkademo.controller.origin.convert.LocalDateConverter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PlayerSource implements Serializable {
    @ExcelProperty("id")
    private Long id;

//    @ExcelProperty(value = "create_time", converter = LocalDateTimeConverter.class)
//    private LocalDateTime createTime;
//
//    @ExcelProperty(value = "modify_time", converter = LocalDateTimeConverter.class)
//    private LocalDateTime modifyTime;

    @ExcelProperty()
    private String createTime;

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
    private Long vipId;

    @ExcelProperty()
    private String username;

    @ExcelProperty()
    private String realName;

    @ExcelProperty()
    private String mobile;

    @ExcelProperty()
    private String password;

    @ExcelProperty()
    private Integer registDevice;

    @ExcelProperty()
    private String registIp;

    @ExcelProperty()
    private String registAddress;

    @ExcelProperty()
    private Long channelId;

    @ExcelProperty()
    private Long followerId;

    @ExcelProperty()
    private String email;

    @ExcelProperty()
    private String wechat;

    @ExcelProperty()
    private Integer agentable;

    @ExcelProperty()
    private Long rateAgentId;

    @ExcelProperty()
    private Long rebateAgentId;

    @ExcelProperty()
    private Long channelPartnerId;

    @ExcelProperty()
    private String gamePwd;

    @ExcelProperty()
    private Integer sex;

//    @ExcelProperty(converter = LocalDateConverter.class)
//    private LocalDate birthday;

    @ExcelProperty()
    private String birthday;

    @ExcelProperty()
    private String withdrawalPwd;

    @ExcelProperty()
    private Integer mobileChecked;

    @ExcelProperty()
    private Integer enabled;

    @ExcelProperty()
    private String deviceKey;

    @ExcelProperty()
    private Integer firstAutoRegist;

    @ExcelProperty()
    private String mobileAreaCode;

    @ExcelProperty()
    private Integer type;

//    @ExcelProperty(converter = LocalDateTimeConverter.class)
//    private LocalDateTime agentTime;
//
//    @ExcelProperty(converter = LocalDateTimeConverter.class)
//    private LocalDateTime checkedTime;

    @ExcelProperty()
    private String agentTime;

    @ExcelProperty()
    private String checkedTime;

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
