package com.bicjo.resys.core.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bicjo.resys.core.repository.hibernate.HibernateSpecification;

@org.springframework.stereotype.Repository
public class HibernateRepository implements Repository {

	private SessionFactory sessionFactory;

	public HibernateRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public <T> Serializable insert(T entity) {

		Session session = sessionFactory.getCurrentSession();
		return session.save(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> find(Specification<T> specification) {

		HibernateSpecification<T> hibernateSpecification = (HibernateSpecification<T>) specification;
		Session session = sessionFactory.getCurrentSession();

		return hibernateSpecification.createCriteria()
				.getExecutableCriteria(session).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Specification<T> specification) {

		HibernateSpecification<T> hibernateSpecification = (HibernateSpecification<T>) specification;
		Session session = sessionFactory.getCurrentSession();

		return (T) hibernateSpecification.createCriteria()
				.getExecutableCriteria(session).uniqueResult();
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
	public <T> void update(T entity) {

		Session session = sessionFactory.getCurrentSession();
		session.update(entity);

	}

	@Override
	public <T> void delete(T entity) {

		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void insertAll(T... entities) {

		Session session = sessionFactory.getCurrentSession();
		for (T aDomain : entities) {
			session.save(aDomain);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void deleteAll(T... entities) {

		Session session = sessionFactory.getCurrentSession();
		for (T aDomain : entities) {
			session.delete(aDomain);
		}

	}
}
