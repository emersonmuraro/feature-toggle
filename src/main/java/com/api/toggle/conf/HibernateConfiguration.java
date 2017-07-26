package com.api.toggle.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.dialect.H2Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe com as configurações de acesso a banco, datasources e do hibernate.
 * 
 * 
 * @author emersonmuraro
 *
 */

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.api.toggle" })
public class HibernateConfiguration {
 
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.api.toggle.model.entity" });
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
     }
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
	    dataSource.setUrl("jdbc:h2:~/myDataSource;DB_CLOSE_DELAY=-1");
	    dataSource.setUsername("sa");
	    dataSource.setPassword("");
	    return dataSource;
    }
     
    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", H2Dialect.class.getName());
        properties.put("hibernate.show_sql",true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
//        properties.put("spring.jpa.generate-ddl", true);
        return properties;        
    }
     
    @Bean
    public HibernateTransactionManager transactionManager() {
    	HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}
