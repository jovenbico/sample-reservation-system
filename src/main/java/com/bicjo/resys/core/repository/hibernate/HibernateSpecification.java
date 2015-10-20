package com.bicjo.resys.core.repository.hibernate;

import org.hibernate.criterion.DetachedCriteria;

import com.bicjo.resys.core.repository.Specification;

public interface HibernateSpecification<T> extends Specification<T> {

	DetachedCriteria createCriteria();

}
