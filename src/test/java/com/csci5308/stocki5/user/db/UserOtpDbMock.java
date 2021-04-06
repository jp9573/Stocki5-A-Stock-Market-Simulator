package com.csci5308.stocki5.user.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.password.IUserOtp;

public class UserOtpDbMock implements IUserOtpDb {

    private static IUserOtpDb uniqueInstance = null;


    UserAbstractFactory userFactory = UserAbstractFactory.instance();

    private UserOtpDbMock() { }

    public static IUserOtpDb instance(){
        if(null == uniqueInstance){
            uniqueInstance = new UserOtpDbMock();
        }
        return uniqueInstance;
    }

    @Override
    public boolean insertOtp(IUserOtp userOtp) {
        return true;
    }

    @Override
    public IUserOtp getOtp(int otp) {
        if (otp == 9999) {
            String format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat dateFormater = new SimpleDateFormat(format);
            Date currentDateTime = new Date();
            long currentDateTimeMS = currentDateTime.getTime();
            long newDateTimeMS = currentDateTimeMS + 3600000;
            Date newDateTime = new Date(newDateTimeMS);

            IUserOtp userOtp = userFactory.createUserOtp();
            userOtp.setOtp(9999);
            userOtp.setUserCode("AB123456");
            userOtp.setValidity(dateFormater.format(newDateTime));
            return userOtp;
        } else if (otp == 8888) {
            IUserOtp userOtp = userFactory.createUserOtp();
            userOtp.setOtp(8888);
            userOtp.setUserCode("AB123456");
            userOtp.setValidity("2000-10-10 12:12:12");
            return userOtp;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteOtp(int otp) {
        return true;
    }

    @Override
    public boolean deleteOtpByUserCode(String userCode) {
        return true;
    }

}