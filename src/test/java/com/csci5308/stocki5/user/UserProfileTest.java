package com.csci5308.stocki5.user;

import com.csci5308.stocki5.user.profile.UserProfile;
import org.junit.Assert;
import org.junit.Test;

public class UserProfileTest {
    UserProfile userProfile = new UserProfile();

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setFirstName("Jack");
        user.setLastName("Sparrow");
        user.setEmailId("test@best.com");
		user.setContactNo("19876543210");
		user.setProvince("Toronto");
		user.generateUserCode();
        UserDbMock userDbMock = new UserDbMock();
        Assert.assertTrue(userProfile.updateUser(userDbMock, user, "1994-09-28"));
    }
}