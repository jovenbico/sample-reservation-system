package com.bicjo.resys.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "users")
public class User implements Domain {

	private static final long serialVersionUID = 3279544047957545986L;

	@Id
	@GeneratedValue
	private Long id;
	private String username;

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	public User(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
