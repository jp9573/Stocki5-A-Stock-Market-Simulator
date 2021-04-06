package com.csci5308.stocki5.user.db;

import com.csci5308.stocki5.user.IUser;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDb {
    public boolean insertUser(IUser user);

    public boolean updateUser(IUser user);

    public IUser getUser(String userCode);

    public IUser getUserByEmail(String email);

    public boolean updateUserPassword(IUser user);

    public double getUserFunds(String userCode);

    public boolean updateUserFunds(String userCode, double amount);
}
