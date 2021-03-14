package com.csci5308.stocki5.user;

import org.junit.Assert;
import org.junit.Test;

public class UserFundsTest {

    UserDbInterface userDbMock = new UserDbMock();
    UserFunds userFunds = new UserFunds("AB123456", userDbMock);

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