package com.csci5308.stocki5.user.profile;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDbMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserProfileTest {
    UserDbMock userDbMock = null;
    UserProfile userProfile = null;
    User user = null;

    @Before
    public void createObjects() {
        userProfile = new UserProfile();
        user = new User();
        userDbMock = new UserDbMock();
    }

    @After
    public void destroyObjects() {
        userProfile = null;
        user = null;
        userDbMock = null;
    }

    @Test
    public void updateUserTest() {
        user.setFirstName("Jack");
        user.setLastName("Sparrow");
        user.setEmailId("test@best.com");
        user.setContactNo("19876543210");
        user.setProvince("Toronto");
        user.generateUserCode();
        Assert.assertTrue(userProfile.updateUser(userDbMock, user, "1994-09-28"));
    }
}