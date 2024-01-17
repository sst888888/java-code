package org.jdbc.demo01;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.CollectionUtils;
import org.springframework.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}),
})
public class MybatisAutoGenerateIdInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(MybatisAutoGenerateIdInterceptor.class);


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement)args[0];

        // 插入对象 如：user
        Object obj = args[1];

        String userId = MDC.get("userId");

        if (SqlCommandType.INSERT.equals(statement.getSqlCommandType())) {
            // 单个插入
            if (obj instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) obj;
                assignIdIfNull(entity, Long.parseLong(userId));
            }

            // 批量插入
            if (obj instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) obj;
                List<?> list = (List<?>) map.get("list");
                if (!CollectionUtils.isEmpty(list)) {
                    list.forEach(entity -> {
                        assignIdIfNull((BaseEntity) entity, Long.parseLong(userId));
                        assignDefaultValue((BaseEntity) entity);
                    });
                }
            }
        }


        if (SqlCommandType.UPDATE.equals(statement.getSqlCommandType())) {
            // 单个插入
            if (obj instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) obj;
                entity.setUpdateBy(Optional.of(Long.parseLong(userId)).orElse(0L));
            }

            // 批量插入
            if (obj instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) obj;
                List<?> list = (List<?>) map.get("list");
                if (!CollectionUtils.isEmpty(list)) {
                    list.forEach(item -> {
                        BaseEntity entity = (BaseEntity) item;
                        entity.setUpdateBy(Optional.of(Long.parseLong(userId)).orElse(0L));
                    });
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }


    private void assignIdIfNull(BaseEntity entity, Long userId) {
        if (entity.getId() == null || entity.getId() == 0L) {
//            entity.setId(IdGenerator.generateId().timestamp());
        }

        if (entity.getCreateBy() == null || userId != null) {
            entity.setCreateBy(userId);
        }
    }

    /**
     * 批量插入的时候给基础属性赋予默认值，不然会失败
     * 这里是插入的最后一级，所以优先级最高，业务代码内手动设置的也会被这里覆盖掉，也应该被覆盖
     * @param entity
     */
    private void assignDefaultValue(BaseEntity entity) {
        entity.setUpdateBy(0L);
        entity.setClientId(0L);
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
    }

}
