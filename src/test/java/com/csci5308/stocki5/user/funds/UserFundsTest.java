package com.csci5308.stocki5.user.funds;

import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDbMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserFundsTest {

    IUserDb userDbMock = null;
    IUserFunds userFunds = null;

    @Before
    public void createObjects()
    {
        userDbMock = new UserDbMock();
        userFunds = new UserFunds();
        userFunds.setResetFundAmount(10000);
    }

    @After
    public void destroyObjects()
    {
        userDbMock = null;
        userFunds = null;
    }

    @Test
    public void resetFundsTest() {
        User user = userDbMock.getUser("AB1234567");
        boolean resetStatus = userFunds.resetFunds(user, userDbMock);
        Assert.assertTrue(resetStatus);
    }

    @Test
    public void resetFundsNegativeTest() {
        User user = userDbMock.getUser("AB123456");
        boolean resetStatus = userFunds.resetFunds(user, userDbMock);
        Assert.assertFalse(resetStatus);
    }
}