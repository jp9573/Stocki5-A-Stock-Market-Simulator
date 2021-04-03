package com.csci5308.stocki5.user.profile;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;
import com.csci5308.stocki5.user.factory.UserFactoryMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserProfileTest {
    private static final String UPDATE_USER_ERROR_MESSAGE = "Error updating user information. Please try again later.";
    private static final String INVALID_EMAIL_MESSAGE = "Invalid Email Id";
    private static final String INVALID_CONTACT_NUMBER_MESSAGE = "Invalid Contact Number";

    UserAbstractFactoryMock userFactoryMock = UserFactoryMock.instance();
    private IUserDb userDbMock = null;
    private IUserProfile userProfile = null;
    private IUser user = null;

    @Before
    public void createObjects() {
        userProfile = userFactoryMock.createUserProfile();
        user = userFactoryMock.createUser();
        user.setFirstName("Jack");
        user.setLastName("Sparrow");
        user.setProvince("Toronto");
        userDbMock = userFactoryMock.createUserDbMock();
    }

    @After
    public void destroyObjects() {
        userProfile = null;
        user = null;
        userDbMock = null;
    }

    @Test
    public void updateUserTest() {
        user.setEmailId("test@best.com");
        user.setContactNo("19876543210");
        user.generateUserCode();
        Assert.assertTrue(userProfile.updateUser(userDbMock, user, "1994-09-28"));
    }

    @Test
    public void updateUserWithInvalidDOBTest() {
        user.setEmailId("test@best.com");
        user.setContactNo("19876543210");
        Assert.assertFalse(userProfile.updateUser(userDbMock, user, "2020-09-28"));
    }

    @Test
    public void updateUserWithInvalidEmailTest() {
        user.setEmailId("test@best");
        user.setContactNo("19876543210");
        Assert.assertFalse(userProfile.updateUser(userDbMock, user, "1996-09-28"));
        Assert.assertEquals(INVALID_EMAIL_MESSAGE, user.getValidityMessage());
    }

    @Test
    public void updateUserWithInvalidContactNoTest() {
        user.setEmailId("test@best.ca");
        user.setContactNo("198765acb");
        Assert.assertFalse(userProfile.updateUser(userDbMock, user, "1996-09-28"));
        Assert.assertEquals(INVALID_CONTACT_NUMBER_MESSAGE, user.getValidityMessage());
    }

    @Test
    public void updateUserWithInvalidDBOperationResultTest() {
        user.setEmailId("test@best.ca");
        user.setContactNo("19876543210");
        user.setFirstName("Tony");
        Assert.assertFalse(userProfile.updateUser(userDbMock, user, "1996-09-28"));
        Assert.assertEquals(UPDATE_USER_ERROR_MESSAGE, user.getValidityMessage());
    }
}