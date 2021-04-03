package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.UserDbMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserChangePasswordTest{

    IUserChangePassword userChangePassword = null;
    IUserDb userDbMock = null;
    IUser user = null;

    @Before
    public void createObjects()
    {
        userChangePassword = new UserChangePassword();
        userDbMock = new UserDbMock();
        user = userDbMock.getUser("AB123456");
    }

    @After
    public void destroyObjects()
    {
        userChangePassword = null;
        userDbMock = null;
        user = null;
    }

    @Test
    public void validateCurrentPasswordTestValid() {
        String currentPassword = "password";
        Assert.assertTrue(userChangePassword.validateCurrentPassword(user, currentPassword ));
    }

    @Test
    public void validateCurrentPasswordTestInvalid() {
        String currentPassword = "pass";
        Assert.assertFalse(userChangePassword.validateCurrentPassword(user, currentPassword));
    }

    @Test
    public void changePasswordTestValid() {
        String newpassword = "newpassword";
        String confirmNewPassword = "newpassword";
        Assert.assertTrue(userChangePassword.changePassword(user, newpassword, confirmNewPassword, userDbMock));

    }

    @Test
    public void changePasswordTestInvalid() {
        String newpassword = "new";
        String confirmNewPassword = "new1";
        Assert.assertFalse(userChangePassword.changePassword(user, newpassword, confirmNewPassword, userDbMock));

    }
}