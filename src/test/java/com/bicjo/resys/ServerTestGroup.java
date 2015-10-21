package com.bicjo.resys;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bicjo.resys.domain.specification.UserSpecByUsernameTest;
import com.bicjo.resys.service.user.UserRegistrationTest;

@RunWith(Suite.class)
@SuiteClasses({ UserSpecByUsernameTest.class, UserRegistrationTest.class })
public class ServerTestGroup {

}
