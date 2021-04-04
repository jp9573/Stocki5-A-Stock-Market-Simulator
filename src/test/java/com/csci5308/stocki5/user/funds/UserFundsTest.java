package com.csci5308.stocki5.user.funds;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserFundsTest {

    UserAbstractFactoryMock userFactory = UserAbstractFactoryMock.instance();
    IUserDb userDbMock = null;
    IUserFunds userFunds = null;

    @Before
    public void createObjects() {
        userDbMock = userFactory.createUserDbMock();
        userFunds = userFactory.createUserFunds();
        userFunds.setResetFundAmount(10000);
    }

    @After
    public void destroyObjects() {
        userDbMock = null;
        userFunds = null;
    }

    @Test
    public void resetFundsTest() {
        IUser user = userDbMock.getUser("AB1234567");
        boolean resetStatus = userFunds.resetFunds(user, userDbMock);
        Assert.assertTrue(resetStatus);
    }

    @Test
    public void resetFundsNegativeTest() {
        IUser user = userDbMock.getUser("AB123456");
        boolean resetStatus = userFunds.resetFunds(user, userDbMock);
        Assert.assertFalse(resetStatus);
    }
}