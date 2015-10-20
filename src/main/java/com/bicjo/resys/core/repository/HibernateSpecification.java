package com.bicjo.resys.core.repository;

import org.hibernate.criterion.DetachedCriteria;

public interface HibernateSpecification<T> extends Specification<T> {

	DetachedCriteria createCriteria();

}
