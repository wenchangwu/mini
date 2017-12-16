package com.lakala.mini.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: jpa configuration
 * User: xiao_dingo
 * Date: 2017-12-14
 * Time: 下午4:07
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.lakala.mini.server.core.dao")
public class JpaConfig {
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${mini.jpa.databasePlatform}")
    private String databasePlatform;

    /**
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DruidDataSource source = new DruidDataSource();
        source.setDriverClassName(driverClassName);
        source.setUrl(url);
        source.setUsername(userName);
        source.setPassword(password);
        return source;
    }

    /**
     * 2.配置EntityManagerFactory
     *
     * @return
     */
    @Bean
    @Autowired
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPersistenceUnitName("mini");
        /*factory.setPersistenceXmlLocation("classpath:META-INF/persistence_mini.xml");*/
        // 配置数据源
        factory.setDataSource(dataSource);
        // VendorAdapter
        factory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
        jpaProperties.put("hibernate.jdbc.batch_size",50);


        // entity包扫描路径
        factory.setPackagesToScan("com.lakala.mini.server.core.domain");
        factory.setJpaPropertyMap(jpaProperties);
        factory.afterPropertiesSet();
        return factory.getObject();
    }


    /**
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(emf);
        return manager;
    }

    /**
     * hibernate配置
     *
     * @return
     */
    private HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setDatabasePlatform(databasePlatform);
        return hibernateJpaVendorAdapter;
    }

}
