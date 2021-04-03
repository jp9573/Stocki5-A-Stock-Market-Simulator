package com.csci5308.stocki5.user.forgotcode;

import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;
import com.csci5308.stocki5.user.factory.UserFactoryMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserForgotCodeTest {
    UserAbstractFactoryMock userFactoryMock = UserFactoryMock.instance();
    IUserForgotCode userForgotCode = null;
    IUserDb userDb = null;

    @Before
    public void createObjects() {
        userDb = userFactoryMock.createUserDbMock();
        userForgotCode = userFactoryMock.createUserForgotCode();
    }

    @After
    public void destroyObjects() {
        userDb = null;
        userForgotCode = null;
    }

    @Test
    public void getUserCodeTestBothEmpty() {
        String email = "";
        String dob = "";
        Assert.assertNull(userForgotCode.getUserCode(email, dob, userDb));
    }

    @Test
    public void getUserCodeTestOnlyEmail() {
        String email = "test@example.com";
        String dob = "";
        Assert.assertNull(userForgotCode.getUserCode(email, dob, userDb));
    }

    @Test
    public void getUserCodeTestOnlyDob() {
        String email = "";
        String dob = "2000-10-12";
        Assert.assertNull(userForgotCode.getUserCode(email, dob, userDb));
    }

    @Test
    public void getUserCodeTestBothCorrect() {
        String email = "test@example.com";
        String dob = "2000-10-12";
        Assert.assertEquals("TEST12456789", userForgotCode.getUserCode(email, dob, userDb));
    }

    @Test
    public void getUserCodeTestWrongDob() {
        String email = "test@example.com";
        String dob = "2001-10-12";
        Assert.assertNull(userForgotCode.getUserCode(email, dob, userDb));
    }
}