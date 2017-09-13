package ch.raiffeisen.hackzurich.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("file:${user.dir}/application.config")
@EnableJpaRepositories(basePackages="ch.raiffeisen.hackzurich.repositories")
@EnableTransactionManagement
public class DBConfig {
    @Autowired
    Environment env;
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
       LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
       em.setDataSource(dataSource());
       em.setPackagesToScan(new String[] { "ch.raiffeisen" });
       JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
       em.setJpaVendorAdapter(vendorAdapter);
       em.setJpaProperties(additionalProperties());
  
       return em;
    }
  
    @Bean
    public DataSource dataSource(){
       DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //jdbc:sqlserver://hackzurich.database.windows.net:1433;databaseName=hackzurich
        String url = "jdbc:sqlserver://"+env.getProperty("db.url")+":1433;databaseName=hackzurich";
       dataSource.setUrl(url);
       dataSource.setUsername(env.getProperty("db.user"));
       dataSource.setPassword(env.getProperty("db.password"));
       return dataSource;
    }
  
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(emf);
       return transactionManager;
    }
  
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
       return new PersistenceExceptionTranslationPostProcessor();
    }
  
    Properties additionalProperties() {
       Properties properties = new Properties();
       properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
       properties.setProperty("hibernate.show_sql", "true");
       properties.setProperty("hibernate.format_sql", "true");
       return properties;
    }
}
