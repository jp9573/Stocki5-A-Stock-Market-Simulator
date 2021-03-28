package com.csci5308.stocki5.user;

import org.junit.Assert;
import org.junit.Test;

import com.csci5308.stocki5.user.password.UserChangePassword;

public class UserChangePasswordTest{

    UserChangePassword userChangePassword = new UserChangePassword();
    UserDbMock userDbMock = new UserDbMock();
    User user = userDbMock.getUser("AB123456");

    @Test
    public void validateCurrentPasswordTestValid() {
        String userCode = "AB123456";
        String currentPassword = "password";
        Assert.assertEquals(true,userChangePassword.validateCurrentPassword(user, currentPassword ));
    }

    @Test
    public void validateCurrentPasswordTestInvalid() {
        String userCode = "AB123456";
        String currentPassword = "pass";
        Assert.assertEquals(false,userChangePassword.validateCurrentPassword(user, currentPassword));
    }

    @Test
    public void changePasswordTestValid() {
        String newpassword = "newpassword";
        String confirmNewPassword = "newpassword";
        Assert.assertEquals("Valid",userChangePassword.changePassword(user, newpassword, confirmNewPassword, userDbMock));

    }

    @Test
    public void changePasswordTestInvalid() {
        String newpassword = "new";
        String confirmNewPassword = "new1";
        Assert.assertNotEquals("Valid",userChangePassword.changePassword(user, newpassword, confirmNewPassword, userDbMock));

    }
}