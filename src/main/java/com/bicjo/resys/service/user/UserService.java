package com.bicjo.resys.service.user;


public interface UserService {

	void register(String username) throws UserRegistrationException;

}
