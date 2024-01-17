package org.jdbc.demo01;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "allow-tables")
@Data
public class AllowUpdateTables {
    /**
     * 启用
     */
    private boolean enable = false;

    /**
     * 允许全表删除的表
     */
    private List<String> delete;

    /**
     * 允许全表更新的表
     */
    private List<String> update;

}

