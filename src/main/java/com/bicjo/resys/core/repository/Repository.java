package com.bicjo.resys.core.repository;

import java.io.Serializable;
import java.util.List;

public interface Repository {

	public static final String HIBERNATE = "hibernate";

	<T> Serializable insert(T entity);

	<T> List<T> find(Specification<T> specification);

	<T> T get(Specification<T> specification);

	<T> T retrieve(Class<T> entityClass, Serializable key);

	<T> List<T> findAll(Class<T> entityClass);

	<T> void update(T entity);

	<T> void delete(T entity);

	@SuppressWarnings("unchecked")
	<T> void insertAll(T... entities);

	@SuppressWarnings("unchecked")
	<T> void deleteAll(T... entities);

}
