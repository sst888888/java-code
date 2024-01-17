package org.jdbc.demo01;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Mybatis拦截器执行顺序配置类
 * 多个拦截器存在时，这里对执行顺序进行配置
 */
public class MybatisInterceptorConfig {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;


    @Autowired
    private AllowUpdateTables allowUpdateTables;

    @PostConstruct
    public void addDefaultTimeInterceptor() {
        /**
         * Mybatis拦截器可以使用@Component注解也可以在这里进行配置
         * 在这里配置可以控制拦截器的执行顺序，所以注意去掉@Component注解
         */

        MybatisAutoGenerateIdInterceptor autoGenerateIdInterceptor = new MybatisAutoGenerateIdInterceptor();
        MybatisSafeUpdateInterceptor safeUpdateInterceptor = new MybatisSafeUpdateInterceptor(allowUpdateTables);

        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();

            // 最后添加的会更早执行
            configuration.addInterceptor(new MybatisTestInterceptor());
            configuration.addInterceptor(autoGenerateIdInterceptor);
            configuration.addInterceptor(safeUpdateInterceptor);
        }
    }


}
