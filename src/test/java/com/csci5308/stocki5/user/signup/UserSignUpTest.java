package com.csci5308.stocki5.user.signup;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;
import com.csci5308.stocki5.user.factory.UserFactoryMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserSignUpTest {
    private static final String INVALID_EMAIL_MESSAGE = "Invalid Email Id";
    private static final String ADD_USER_ERROR_MESSAGE = "Error in adding user. Please try again later.";
    private static final String INVALID_CONTACT_NUMBER_MESSAGE = "Invalid Contact Number";

    UserAbstractFactoryMock userFactory = UserAbstractFactoryMock.instance();
    private IUserDb userDbMock = null;
    private IUserSignUp userSignUp = null;
    private IUser user = null;

    @Before
    public void createObjects() {
        userSignUp = userFactory.createUserSignUp();
        user = userFactory.createUser();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setProvince("Toronto");
        userDbMock = userFactory.createUserDbMock();
    }

    @After
    public void destroyObjects() {
        userSignUp = null;
        user = null;
        userDbMock = null;
    }

    @Test
    public void addUserTest() {
        user.setPassword("12345678");
        user.setConfirmPassword("12345678");
        user.setEmailId("test@best.com");
        user.setContactNo("19876543210");
        user.generateUserCode();
        Assert.assertNotEquals("error", userSignUp.addUser(userDbMock, user, "1994-09-28"));
    }

    @Test
    public void addUserWithInvalidDOBTest() {
        user.setPassword("12345678");
        user.setConfirmPassword("12345678");
        user.setEmailId("test@best.com");
        user.setContactNo("19876543210");
        Assert.assertFalse(userSignUp.addUser(userDbMock, user, "2020-09-28"));
    }

    @Test
    public void addUserWithInvalidEmailTest() {
        user.setPassword("12345678");
        user.setConfirmPassword("12345678");
        user.setEmailId("test@best");
        user.setContactNo("19876543210");
        Assert.assertFalse(userSignUp.addUser(userDbMock, user, "1996-09-28"));
        Assert.assertEquals(INVALID_EMAIL_MESSAGE, user.getValidityMessage());
    }

    @Test
    public void addUserWithInvalidPasswordTest() {
        user.setPassword("123");
        user.setConfirmPassword("123");
        user.setEmailId("test@best");
        user.setContactNo("19876543210");
        Assert.assertFalse(userSignUp.addUser(userDbMock, user, "1996-09-28"));
    }

    @Test
    public void updateUserWithInvalidContactNoTest() {
        user.setPassword("12345678");
        user.setConfirmPassword("12345678");
        user.setEmailId("test@best.ca");
        user.setContactNo("198765acb");
        Assert.assertFalse(userSignUp.addUser(userDbMock, user, "1996-09-28"));
        Assert.assertEquals(INVALID_CONTACT_NUMBER_MESSAGE, user.getValidityMessage());
    }

    @Test
    public void addUserWithInvalidDBOperationResultTest() {
        user.setPassword("12345678");
        user.setConfirmPassword("12345678");
        user.setEmailId("test@best.ca");
        user.setContactNo("19876543210");
        user.setFirstName("Tony");
        Assert.assertFalse(userSignUp.addUser(userDbMock, user, "1996-09-28"));
        Assert.assertEquals(ADD_USER_ERROR_MESSAGE, user.getValidityMessage());
    }
}
