package com.csci5308.stocki5.user.funds;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import org.springframework.stereotype.Service;

@Service
public interface IUserFunds {
    public boolean resetFunds(IUser user, IUserDb userDb);

    public void setResetFundAmount(int resetFundAmount);
}
