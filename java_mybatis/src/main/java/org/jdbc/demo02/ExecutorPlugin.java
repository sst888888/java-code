package org.jdbc.demo02;

import org.apache.catalina.startup.RealmRuleSet;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.jdbc.demo01.MybatisAutoGenerateIdInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class ExecutorPlugin implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(ExecutorPlugin.class);

    public static void printSqlLog(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        StringBuffer sb = new StringBuffer("===> param:");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", parameterObject.toString());
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        String parameterValue = obj.toString();
                        sql = sql.replaceFirst("\\?", parameterValue);
                        sb.append(parameterValue).append("(").append(obj.getClass().getSimpleName()).append("),");
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        String parameterValue = obj.toString();
                        sql = sql.replaceFirst("\\?", parameterValue);
                        sb.append(parameterValue).append("(").append(obj.getClass().getSimpleName()).append("),");
                    }
                }
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        log.info("===> sql: {}", sql);
        log.info(sb.toString());
        log.info("===> sql time: {} ms", time);
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("Executor Plugin 拦截：{}", invocation.getMethod());

        Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) args[1];
        BoundSql boundSql = statement.getBoundSql(paramMap);

        String sql = boundSql.getSql();
        log.info("===> origin sql:{}", sql);

        long startTime = System.currentTimeMillis();
        Configuration configuration = statement.getConfiguration();
        String sqlId = statement.getId();
        Object proceed = invocation.proceed();
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        printSqlLog(configuration, boundSql, sqlId, time);
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
