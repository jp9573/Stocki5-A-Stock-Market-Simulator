package com.csci5308.stocki5.user.signup;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDbMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserSignUpTest {
    UserDbMock userDbMock = new UserDbMock();
    UserSignUp userSignUp = new UserSignUp();
    User user = new User();

    @Before
    public void createObjects() {
        userSignUp = new UserSignUp();
        user = new User();
        userDbMock = new UserDbMock();
    }

    @After
    public void destroyObjects() {
        userSignUp = null;
        user = null;
        userDbMock = null;
    }

    @Test
    public void addUserTest() {
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("12345678");
        user.setConfirmPassword("12345678");
        user.setEmailId("test@best.com");
        user.setContactNo("19876543210");
        user.setProvince("Toronto");
        user.generateUserCode();
        Assert.assertNotEquals("error", userSignUp.addUser(userDbMock, user, "1994-09-28"));
    }
}
