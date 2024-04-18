package com.example.entity;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class RobotKnowledgeExport {
    /**
     * 1：现金网、2：棋牌、3：直播；0:其他
     */
    @ColumnWidth(15)
    @ExcelProperty("渠道类型")
    private String typeName;

    @ColumnWidth(40)
    @ExcelProperty("业务场景")
    private String scene;

    @ColumnWidth(40)
    @ExcelProperty("问题")
    private String question;

    @ColumnWidth(60)
    @ExcelProperty("标答")
    private String answer;

    @ColumnWidth(15)
    @ExcelProperty("操作人")
    private String modifyUserName;

    @ColumnWidth(20)
    @ExcelProperty("操作时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyTime;

    @ExcelIgnore
    private int type;
}