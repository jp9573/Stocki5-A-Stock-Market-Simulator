package com.csci5308.stocki5.user.funds;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.IUser;

@Service
public class UserFunds implements IUserFunds {
    @Value("${user.resetfundamount}")
    private int resetFundAmount;

    @Override
    public void setResetFundAmount(int resetFundAmount) {
        this.resetFundAmount = resetFundAmount;
    }

    @Override
    public boolean resetFunds(IUser user, IUserDb userDb) {
        if (user.getFunds() < resetFundAmount) {
            user.setFunds(resetFundAmount);
            return userDb.updateUserFunds(user.getUserCode(), resetFundAmount);
        } else {
            return false;
        }
    }
}
