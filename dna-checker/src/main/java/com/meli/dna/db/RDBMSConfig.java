package com.meli.dna.db;

import java.util.HashMap;

import javax.naming.NamingException;
import javax.sql.DataSource;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.meli.dna.ICfgDNA;

@Log4j2
@Configuration
@EnableJpaRepositories(
	    basePackages = "com.meli.dna.dao", 
	    entityManagerFactoryRef = "entityManager", 
	    transactionManagerRef = "transactionManager"
)
public class RDBMSConfig {

	@Autowired
    private ICfgDNA config;
     
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManager()throws NamingException {
        
    	log.info("Loading Entity Manager....");
    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(jndiDatasource());
        em.setPackagesToScan(new String[] { "com.meli.dna.model" });
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", config.getHibernateHbm2ddlAuto());
        properties.put("hibernate.dialect", config.getHibernateDialect());
        properties.put("hibernate.show_sql", config.getHibernateShowSQL());
        em.setJpaPropertyMap(properties);
 
        log.debug("Entity Manager loaded....");
        return em;
    }
 
    @Primary
    @Bean
    public DataSource jndiDatasource() throws IllegalArgumentException, NamingException {
    	
    	log.info("Loading JNDI Datasource....");
    	
    	JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName(config.getDatasourceJNDIName());
        bean.setLookupOnStartup(true);
        bean.setProxyInterface(DataSource.class);
        bean.setResourceRef(true);
        bean.afterPropertiesSet();
        
        log.info("Loaded JNDI object:" + config.getDatasourceJNDIName());
        return (DataSource) bean.getObject();

    }
 
    @Primary
    @Bean
    public PlatformTransactionManager transactionManager() throws NamingException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager().getObject());
        return transactionManager;
    }
    
}
