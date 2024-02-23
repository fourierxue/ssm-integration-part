package tech.xuedong.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 持久层配置类：连接池，sqlSessionFactory，Mapper代理对象
 * 方式2：不保留mybatis配置文件，mybatis属性都在代码中配置
 * 如果将datasource和mybatis的组件配置到一起，出现@Value注解不生效的问题
 *      原因：mybatis组件优先加载，@Value还没有读取
 *      解决：分开配置，写到不同的类即可
 */
@Configuration
public class MapperJavaConfigNew {
    //sqlSessionFactory
    // 方式1：外部指定mybatis-config.xml 连接池和mapper配置不需要在配置文件中配置
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 指定连接池
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 指定mybatis配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(Slf4jImpl.class);
        configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setTypeAliasesPackage("tech.xuedong.pojo");
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        sqlSessionFactoryBean.addPlugins(pageInterceptor);
        return sqlSessionFactoryBean;
    }
    // mapper代理对象
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        // mapper代理对象的factoryBean -》 指定一个包 -》mapper接口 -》mapper代理对象 -》ioc容器
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("tech.xuedong.mapper");
        return mapperScannerConfigurer;
    }
}
