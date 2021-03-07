package com.csci5308.stocki5.user;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDbInterface
{
	public boolean insertUser(User user);

	public boolean updateUser(User user);

	public User getUser(String userCode);

	public User getUserByEmail(String email);

	public boolean updateUserPassword(User user);
}
