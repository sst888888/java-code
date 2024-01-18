package org.jdbc.demo01;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * Mybatis安全更新拦截器，对update、delete操作进行保护处理，防止出现全表的更新或删除
 */
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}),
})
public class MybatisSafeUpdateInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(MybatisSafeUpdateInterceptor.class);

    // 关于该插件的配置
    private final AllowUpdateTables allowUpdateTables;

    public MybatisSafeUpdateInterceptor(AllowUpdateTables allowUpdateTables) {
        this.allowUpdateTables = allowUpdateTables;
    }
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (allowUpdateTables == null) {
            throw new BaseException("allowUpdateTables为空，请注册成Bean");
        }
        if (!allowUpdateTables.isEnable()) {
            return invocation.proceed();
        }

        final Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];

        // 全表更新、删除的时候
        if (SqlCommandType.UPDATE.equals(statement.getSqlCommandType())) {
            boolean allow = checkAllowTableOperation(invocation);
            if (!allow) {
                throw new BaseException("该表不支持不带where条件的更新语句");
            }
        }

        if (SqlCommandType.DELETE.equals(statement.getSqlCommandType())) {
            // 删除
            boolean allow = checkAllowTableOperation(invocation);
            if (!allow) {
                throw new BaseException("该表不支持不带where条件的删除语句");
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

    /**
     * 对没有带where条件的update和delete语句进行检查，看是否在允许列表中
     * 目的是为了避免忘记写条件导致的全表误操作
     */
    private boolean checkAllowTableOperation(Invocation invocation) throws BaseException, JSQLParserException {
        String sql = getSqlByInvocation(invocation);
        if (StringUtils.isEmpty(sql)) {
            throw new BaseException("待执行的SQL不能为空");
        }

        // 这里用JSqlParser工具来判断是否存在where条件

        final Statement smt = CCJSqlParserUtil.parse(sql);
        if (smt instanceof net.sf.jsqlparser.statement.update.Update) {
            final net.sf.jsqlparser.statement.update.Update update = (net.sf.jsqlparser.statement.update.Update) smt;
            return update.getWhere() != null;
        } else if (smt instanceof net.sf.jsqlparser.statement.delete.Delete) {
            final net.sf.jsqlparser.statement.delete.Delete delete = (net.sf.jsqlparser.statement.delete.Delete) smt;
            return delete.getWhere() != null;
        }

        return false;
    }

    /**
     * 获取待执行的sql语句
     */
    private String getSqlByInvocation(Invocation invocation) {
        try {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            final Object[] queryArgs = invocation.getArgs();
            final Object parameter = queryArgs[1];
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            return boundSql.getSql();
        } catch (Exception e) {
            log.error("获取SQL语句失败", e);
        }
        return null;
    }


    private class BaseException extends Throwable {
        public BaseException(String 待执行的SQL不能为空) {
        }
    }
}
