package com.csci5308.stocki5.user;

import org.junit.Assert;
import org.junit.Test;

public class UserForgotCodeTest {
    UserForgotCode userForgotCode = new UserForgotCode();

    @Test
    public void getUserCodeTestBothEmpty(){
        String email = "";
        String dob = "";
        UserDbMock userDb = new UserDbMock();
        Assert.assertEquals(null, userForgotCode.getUserCode(email, dob, userDb));
    }

    @Test
    public void getUserCodeTestOnlyEmail(){
        String email = "test@example.com";
        String dob = "";
        UserDbMock userDb = new UserDbMock();
        Assert.assertEquals(null, userForgotCode.getUserCode(email, dob, userDb));
    }

    @Test
    public void getUserCodeTestOnlyDob(){
        String email = "";
        String dob = "2000-10-12";
        UserDbMock userDb = new UserDbMock();
        Assert.assertEquals(null, userForgotCode.getUserCode(email, dob, userDb));
    }

    @Test
    public void getUserCodeTestBothCorrect(){
        String email = "test@example.com";
        String dob = "2000-10-12";
        UserDbMock userDb = new UserDbMock();
        Assert.assertEquals("TEST12456789", userForgotCode.getUserCode(email, dob, userDb));
    }

    @Test
    public void getUserCodeTestWrongDob(){
        String email = "test@example.com";
        String dob = "2001-10-12";
        UserDbMock userDb = new UserDbMock();
        Assert.assertEquals(null, userForgotCode.getUserCode(email, dob, userDb));
    }
}