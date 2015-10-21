package com.bicjo.resys.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bicjo.resys.core.repository.Repository;
import com.bicjo.resys.domain.User;
import com.bicjo.resys.domain.specification.UserSpecByUsername;
import com.bicjo.resys.service.user.UserRegistrationException;
import com.bicjo.resys.service.user.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("hibernate")
	private Repository repository;

	@Override
	public void register(String username) throws UserRegistrationException {

		UserSpecByUsername byUsername = new UserSpecByUsername(username);
		User existUser = repository.get(byUsername);

		if (null != existUser) {
			throw new UserRegistrationException("Username " + username
					+ "already exist");
		}

		User newUser = new User(username);
		repository.insert(newUser);

	}

}
