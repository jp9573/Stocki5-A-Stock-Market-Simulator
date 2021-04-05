package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.email.EmailMock;
import com.csci5308.stocki5.email.IEmail;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.IUserOtpDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserForgotPasswordTest {
    IUserForgotPassword userForgotPassword = null;
    IUserDb userDbMock = null;
    IUserOtpDb userOtpDbMock = null;
    IEmail emailMock = null;
    IUserChangePassword userChangePassword = null;
    IUserOtp userOtp = null;
    UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
    UserAbstractFactory userFactory = UserAbstractFactory.instance();

    @Before
    public void createObjects() {
        userForgotPassword = userFactory.createUserForgotPassword();
        userDbMock = userFactoryMock.createUserDbMock();
        userOtpDbMock = userFactoryMock.createUserOtpDbMock();
        emailMock = EmailMock.instance();
        userChangePassword = userFactory.createUserChangePassword();
        userOtp = userFactory.createUserOtp();
    }

    @After
    public void destroyObjects() {
        userForgotPassword = null;
        userDbMock = null;
        userOtpDbMock = null;
        emailMock = null;
        userChangePassword = null;
        userOtp = null;
    }

    @Test
    public void validateUserCodeTestValid() {
        String userCode = "AB123456";
        Assert.assertTrue(userForgotPassword.validateUserCode(userCode, userDbMock));

    }

    @Test
    public void validateUserCodeTestInvalid() {
        String userCode = "INVALID";
        Assert.assertFalse(userForgotPassword.validateUserCode(userCode, userDbMock));
    }

    @Test
    public void generateUserOtpTest() {
        String userCode = "AB123456";
        Assert.assertTrue(userForgotPassword.generateUserOtp(userCode, userOtp, userOtpDbMock, userDbMock, emailMock));
    }

    @Test
    public void verifyOtpTestValid() {
        String userCode = "AB123456";
        int otp = 9999;
        Assert.assertTrue(userForgotPassword.verifyOtp(userCode, otp, userOtpDbMock));
    }

    @Test
    public void verifyOtpTestInvalid() {
        String userCode = "AB123456";
        int otp = 1111;
        Assert.assertFalse(userForgotPassword.verifyOtp(userCode, otp, userOtpDbMock));
    }

    @Test
    public void verifyOtpTestExpired() {
        String userCode = "AB123456";
        int otp = 8888;
        Assert.assertFalse(userForgotPassword.verifyOtp(userCode, otp, userOtpDbMock));
    }

    @Test
    public void verifyOtpTestInvalidUserCode() {
        String userCode = "INVALID";
        int otp = 9999;
        Assert.assertFalse(userForgotPassword.verifyOtp(userCode, otp, userOtpDbMock));
    }

    @Test
    public void verifyOtpTestInvalidUserCodeAndOtp() {
        String userCode = "INVALID";
        int otp = 1111;
        Assert.assertFalse(userForgotPassword.verifyOtp(userCode, otp, userOtpDbMock));
    }

    @Test
    public void resetPassword() {
        String userCode = "AB123456";
        String password = "password";
        String confirmPassword = "password";
        Assert.assertTrue(userForgotPassword.resetPassword(userCode, password, confirmPassword, userDbMock, userOtpDbMock, userChangePassword));
    }

    @Test
    public void resetPasswordInvalid() {
        String userCode = "AB123456";
        String password = "password";
        String confirmPassword = "confirmpassword";
        Assert.assertFalse(userForgotPassword.resetPassword(userCode, password, confirmPassword, userDbMock, userOtpDbMock, userChangePassword));
    }

}