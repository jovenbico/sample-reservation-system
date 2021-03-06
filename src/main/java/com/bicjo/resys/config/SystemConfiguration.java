package com.bicjo.resys.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAsync
@PropertySource(value = { "classpath:${env:test}-config.properties" })
@ComponentScan(basePackageClasses = { RepositoryComponent.class }, basePackages = { "com.bicjo.resys.service.*.impl" })
public class SystemConfiguration {

	private final Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private Environment env;

	private final String DB_URL = "db.url";
	private final String DB_USERNAME = "db.username";
	private final String DB_PASSWORD = "db.password";

	private final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";

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
		hibernateProperties.put("hibernate.hbm2ddl.auto",
				env.getProperty(HIBERNATE_HBM2DDL_AUTO));
		hibernateProperties.put("hibernate.show_sql",
				env.getProperty(HIBERNATE_SHOW_SQL));
		hibernateProperties.put("hibernate.format_sql",
				env.getProperty(HIBERNATE_SHOW_SQL));

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
