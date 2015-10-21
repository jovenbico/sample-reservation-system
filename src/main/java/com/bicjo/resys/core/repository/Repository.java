package com.bicjo.resys.core.repository;

import java.io.Serializable;
import java.util.List;

import com.bicjo.resys.domain.Domain;

public interface Repository {

	<T> List<T> find(Specification<T> specification);

	<T> T get(Specification<T> specification);

	Serializable insert(Domain entity);

	<T> List<T> findAll(Class<T> entityClass);

	<T> T retrieve(Class<T> entityClass, Serializable key);

	<T> void delete(T entity);

	@SuppressWarnings("unchecked")
	<T> void insertAll(T... entities);

	@SuppressWarnings("unchecked")
	<T> void deleteAll(T... entities);

}
