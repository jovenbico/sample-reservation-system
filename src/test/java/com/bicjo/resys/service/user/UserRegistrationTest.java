package com.bicjo.resys.service.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bicjo.resys.BaseTest;

public class UserRegistrationTest extends BaseTest {

	@Autowired
	private UserService userService;

	private static String username = "bicjo";

	@Before
	public void initialize() {
		try {
			userService.register(username);
		} catch (UserRegistrationException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void registrationFail() {

		try {
			userService.register(username);
			Assert.fail();
		} catch (UserRegistrationException e) {
			Assert.assertEquals(e.getMessage(), "Username " + username
					+ "already exist");
		}

	}

}
