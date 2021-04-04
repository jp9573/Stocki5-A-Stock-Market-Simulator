package com.csci5308.stocki5.user.password;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.user.factory.UserAbstractFactory;

public class UserOtpTest {
    IUserOtp userOtp;
    UserAbstractFactory userFactory = UserAbstractFactory.instance();

    @Before
    public void createObjects() {
        userOtp = userFactory.createUserOtp();
        userOtp.setOtpMinValue(999);
        userOtp.setOtpMaxValue(10000);
    }

    @After
    public void destroyObjects() {
        userOtp = null;
    }

    @Test
    public void generateOtpForUserTest() {
        String userCode = "TEST1234";
        userOtp.generateOtpForUser(userCode);
        Assert.assertTrue(userOtp.getOtp() > userOtp.getOtpMinValue() && userOtp.getOtp() < userOtp.getOtpMaxValue());
    }
}