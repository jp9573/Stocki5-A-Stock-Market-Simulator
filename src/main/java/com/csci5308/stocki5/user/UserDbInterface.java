package com.csci5308.stocki5.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDbInterface
{
	public boolean insertUser(User user);

	public boolean updateUser(User user);

	public User getUser(String userCode);

	public User getUserByEmail(String email);

	public boolean updateUserPassword(User user);

	public double getUserFunds(String userCode);

	public boolean updateUserFunds(String userCode, double amount);
}
