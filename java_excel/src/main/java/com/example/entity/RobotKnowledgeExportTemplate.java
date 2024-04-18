package com.example.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class RobotKnowledgeExportTemplate {

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
}