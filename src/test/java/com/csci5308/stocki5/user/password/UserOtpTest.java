package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;
import com.csci5308.stocki5.user.factory.UserFactoryMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserOtpTest {
    IUserOtp userOtp;
    UserAbstractFactoryMock userFactory = UserFactoryMock.instance();

    @Before
    public void createObjects(){
        userOtp = userFactory.createUserOtp();
        userOtp.setOtpMinValue(999);
        userOtp.setOtpMaxValue(10000);
    }

    @After
    public void destroyObjects(){
         userOtp = null;
    }

    @Test
    public void generateOtpForUserTest() {
        String userCode = "TEST1234";
        userOtp.generateOtpForUser(userCode);
        Assert.assertTrue(userOtp.getOtp()> userOtp.getOtpMinValue() && userOtp.getOtp()< userOtp.getOtpMaxValue());
    }
}