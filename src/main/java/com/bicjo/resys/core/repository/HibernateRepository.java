package com.bicjo.resys.core.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bicjo.resys.core.repository.hibernate.HibernateSpecification;
import com.bicjo.resys.domain.Domain;

public class HibernateRepository implements Repository {

	private SessionFactory sessionFactory;

	public HibernateRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> find(Specification<T> specification) {

		HibernateSpecification<T> hibernateSpecification = (HibernateSpecification<T>) specification;
		Session session = sessionFactory.getCurrentSession();

		return hibernateSpecification.createCriteria()
				.getExecutableCriteria(session).list();

	}

	@Override
	public Serializable insert(Domain entity) {

		Session session = sessionFactory.getCurrentSession();
		return session.save(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<T> entityClass) {

		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(entityClass).list();

	}

	@Override
	public <T> T retrieve(Class<T> entityClass, Serializable key) {

		Session session = sessionFactory.getCurrentSession();
		return session.get(entityClass, key);

	}

	@Override
	public void insertAll(Domain... entities) {

		Session session = sessionFactory.getCurrentSession();
		for (Domain aDomain : entities) {
			session.save(aDomain);
		}

	}

	@Override
	public void deleteAll(Class<Domain> entityClass) {

		Session session = sessionFactory.getCurrentSession();
		List<Domain> enteties = findAll(entityClass);
		for (Domain aDomain : enteties) {
			session.delete(aDomain);
		}

	}
}
