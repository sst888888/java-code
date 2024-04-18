package com.example.entity;

import lombok.Data;

@Data
public class ExportRecord {

    /**
     * 状态：0-处理中；1-已完成；
     */
    private Integer status;
    /**
     * 成功数量
     */
    private Integer successNumber;
    /**
     * 失败数量
     */
    private Integer failNumber;
    /**
     * 导入总数
     */
    private Integer totalNumber;
    /**
     * 下载地址
     */
    private String url;

    /**
     * 描述，成功或失败，以及失败原因
     */
    private String description;


}
