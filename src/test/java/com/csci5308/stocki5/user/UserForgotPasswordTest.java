package com.csci5308.stocki5.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserForgotPasswordTest {
    UserForgotPassword userForgotPassword;
    UserDbMock userDbMock;
    UserOtpDbMock userOtpDbMock;

    @Before
    public void init(){
        this.userForgotPassword = new UserForgotPassword();
        this.userDbMock = new UserDbMock();
        this.userOtpDbMock = new UserOtpDbMock();
    }

    @Test
    public void validateUserCodeTestValid() {
        String userCode = "AB123456";
        Assert.assertEquals(true,userForgotPassword.validateUserCode(userCode, userDbMock));

    }

    @Test
    public void validateUserCodeTestInvalid() {
        String userCode = "INVALID";
        Assert.assertEquals(false,userForgotPassword.validateUserCode(userCode, userDbMock));
    }

    @Test
    public void generateUserOtpTest() {
        String userCode = "AB123456";
        int OTP = userForgotPassword.generateUserOtp(userCode, userOtpDbMock);
        Assert.assertTrue(OTP < 10000 && OTP > 999);
    }

    @Test
    public void verifyOtpTestValid() {
        String userCode = "AB123456";
        int otp = 9999;
        Assert.assertEquals("Valid",userForgotPassword.verifyOtp(userCode, otp, userOtpDbMock));
    }

    @Test
    public void verifyOtpTestInvalid() {
        String userCode = "AB123456";
        int otp = 1111;
        Assert.assertEquals("Invalid OTP",userForgotPassword.verifyOtp(userCode, otp, userOtpDbMock));
    }

    @Test
    public void verifyOtpTestExpired() {
        String userCode = "AB123456";
        int otp = 8888;
        Assert.assertEquals("OTP Expired",userForgotPassword.verifyOtp(userCode, otp, userOtpDbMock));
    }

    @Test
    public void verifyOtpTestInvalidUserCode() {
        String userCode = "INVALID";
        int otp = 9999;
        Assert.assertEquals("Invalid OTP",userForgotPassword.verifyOtp(userCode, otp, userOtpDbMock));
    }

    @Test
    public void verifyOtpTestInvalidUserCodeAndOtp() {
        String userCode = "INVALID";
        int otp = 1111;
        Assert.assertEquals("Invalid OTP",userForgotPassword.verifyOtp(userCode, otp, userOtpDbMock));
    }

    @Test
    public void resetPassword() {
        String userCode = "AB123456";
        String password= "password";
        String confirmPassword = "password";
        Assert.assertEquals("Success",userForgotPassword.resetPassword(userCode, password, confirmPassword, userDbMock, userOtpDbMock));
    }

    @Test
    public void resetPasswordInvalid() {
        String userCode = "AB123456";
        String password= "password";
        String confirmPassword = "confirmpassword";
        Assert.assertNotEquals("Success",userForgotPassword.resetPassword(userCode, password, confirmPassword, userDbMock, userOtpDbMock));
    }

}