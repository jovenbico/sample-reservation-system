package com.bicjo.resys.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.bicjo.resys.core.email.EmailSender;
import com.bicjo.resys.core.email.impl.EmailSenderImpl;
import com.bicjo.resys.core.repository.HibernateRepository;
import com.bicjo.resys.core.repository.Repository;

@Component
public class RepositoryComponent {

	@Bean
	@Qualifier("hibernate")
	@Autowired
	public Repository hibernateRepository(SessionFactory sessionFactory) {
		return new HibernateRepository(sessionFactory);
	}

	@Bean
	public EmailSender emailSender() {
		return new EmailSenderImpl();
	}

}
