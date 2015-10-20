package com.bicjo.resys.core.repository;

public interface Specification<T> {
	Class<T> getEntityType();
}
