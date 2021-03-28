package com.csci5308.stocki5.user;

import com.csci5308.stocki5.user.updatepassword.UserOtp;
import org.junit.Assert;
import org.junit.Test;

public class UserOtpTest {
    UserOtp userOtp = new UserOtp();

    @Test
    public void generateOtpForUserTest() {
        String userCode = "TEST1234";
        userOtp.generateOtpForUser(userCode);
        Assert.assertTrue(userOtp.getOtp()>999 && userOtp.getOtp()<10000);
    }
}