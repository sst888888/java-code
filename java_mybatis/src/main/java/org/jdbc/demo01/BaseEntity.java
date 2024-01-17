package org.jdbc.demo01;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: cp
 * @date: 2024-01-16 18:07
 */
@Data
public class BaseEntity {

    private Long id;
    private Long createBy;
    private Long updateBy;
    private Long clientId;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
