package com.bicjo.resys.domain.specification;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.bicjo.resys.core.repository.hibernate.HibernateSpecification;
import com.bicjo.resys.domain.User;

public class UserSpecByUsername implements HibernateSpecification<User> {

	private final Class<User> ENTITY_TYPE = User.class;
	private String username;

	public UserSpecByUsername(String username) {
		this.username = username;
	}

	@Override
	public Class<User> getEntityType() {
		return ENTITY_TYPE;
	}

	@Override
	public DetachedCriteria createCriteria() {

		DetachedCriteria criteria = DetachedCriteria.forClass(ENTITY_TYPE);
		criteria.add(Restrictions.eq("username", username));

		return criteria;
	}

}
