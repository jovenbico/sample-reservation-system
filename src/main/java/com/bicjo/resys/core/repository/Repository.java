package com.bicjo.resys.core.repository;

import java.io.Serializable;
import java.util.List;

import com.bicjo.resys.domain.Domain;

public interface Repository {

	<T> List<T> find(Specification<T> specification);

	Serializable insert(Domain entity);

	<T> List<T> findAll(Class<T> entityClass);

	<T> T retrieve(Class<T> entityClass, Serializable key);

	void insertAll(Domain... entities);

	void deleteAll(Class<Domain> entityType);

}
