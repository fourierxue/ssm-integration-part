package tech.xuedong.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * 持久层配置类：连接池，sqlSessionFactory，Mapper代理对象
 * 方式1：保留mybatis配置文件
 * 如果将datasource和mybatis的组件配置到一起，出现@Value注解不生效的问题
 *      原因：mybatis组件优先加载，@Value还没有读取
 *      解决：分开配置，写到不同的类即可
 */
@Configuration
public class MapperJavaConfig {
    //sqlSessionFactory
    // 方式1：外部指定mybatis-config.xml 连接池和mapper配置不需要在配置文件中配置
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 指定连接池
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 指定mybatis配置文件
        Resource resource = new ClassPathResource("mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
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
