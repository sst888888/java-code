package org.jdbc.demo01;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}),
})
public class MybatisTestInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(MybatisTestInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("触发Mybatis拦截器了");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
