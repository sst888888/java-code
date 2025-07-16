package com.example.entity;

import com.example.xss.Xss;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class XssDTO {

    @NotNull(message = "申请记录ID不能为空")
    private Long applyId;


    @NotNull(message = "缴纳费用不能为空")
    private BigDecimal payment;


    @Xss(message = "不能包含脚本字符")
    @NotBlank(message = "姓名不能为空")
    private String name;

}
