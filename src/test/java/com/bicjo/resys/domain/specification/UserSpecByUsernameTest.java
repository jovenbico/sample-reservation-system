package com.bicjo.resys.domain.specification;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bicjo.resys.config.SystemConfiguration;
import com.bicjo.resys.core.repository.Repository;
import com.bicjo.resys.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SystemConfiguration.class })
@Transactional
public class UserSpecByUsernameTest {

	private final Logger LOG = Logger.getLogger(getClass());

	@Autowired
	@Qualifier("hibernate")
	private Repository repository;

	private User user;

	@Before
	public void initialize() {
		user = new User("bicjo");
		repository.insert(user);
	}

	@After
	public void after() {
		repository.delete(user);
	}

	@Test
	@Ignore
	public void repositoryCheckNull() {
		Assert.assertNotNull(repository);
	}

	@Test
	public void userNotNull() {

		LOG.debug("user.id: " + user.getId());
		User aUser1 = repository.retrieve(User.class, user.getId());
		Assert.assertNotNull(aUser1);

		UserSpecByUsername byUsername = new UserSpecByUsername(
				user.getUsername());
		User aUser2 = repository.get(byUsername);
		Assert.assertNotNull(aUser2);

	}

	@Test
	public void userNull() {

		LOG.debug("user.id: " + user.getId());
		UserSpecByUsername byUsername = new UserSpecByUsername("noOne");
		User aUser = repository.get(byUsername);
		Assert.assertNull(aUser);

	}

}
