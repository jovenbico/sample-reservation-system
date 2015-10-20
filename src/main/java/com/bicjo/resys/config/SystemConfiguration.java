package com.bicjo.resys.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:${env:test}-config.properties" })
public class SystemConfiguration {

	private final Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private Environment env;

	private final String DB_URL = "db.url";
	private final String DB_USERNAME = "db.username";
	private final String DB_PASSWORD = "db.password";

	public SystemConfiguration() {

	}

	@Bean
	public DataSource dataSource() {
		String dbUrl = env.getProperty(DB_URL);
		LOG.info("DB url: " + dbUrl);

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/" + dbUrl
				+ "?createDatabaseIfNotExist=true");
		dataSource.setUsername(env.getProperty(DB_USERNAME));
		dataSource.setPassword(env.getProperty(DB_PASSWORD));

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect",
				"org.hibernate.dialect.MySQLDialect");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.put("hibernate.show_sql", "true");
		hibernateProperties.put("hibernate.format_sql", "true");

		sessionFactory.setDataSource(dataSource());
		sessionFactory.setHibernateProperties(hibernateProperties);
		sessionFactory.setPackagesToScan("com.bicjo.resys.domain");

		return sessionFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());

		return transactionManager;
	}

}
