package com.csci5308.stocki5.user;

import com.csci5308.stocki5.user.profile.UserProfile;
import org.junit.Assert;
import org.junit.Test;

public class UserProfileTest {
    UserProfile userProfile = new UserProfile();

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setUserCode("TestCode");
        user.setFirstName("Jack");
        user.setLastName("Sparrow");
        UserDbMock userDbMock = new UserDbMock();
        Assert.assertTrue(userProfile.updateUser(userDbMock, user));
    }
}