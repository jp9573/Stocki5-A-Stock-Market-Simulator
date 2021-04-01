package com.csci5308.stocki5.user.password;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserOtpDbMock  implements IUserOtpDb {

    @Override
    public boolean insertOtp(IUserOtp userOtp) {
        return true;
    }

    @Override
    public UserOtp getOtp(int otp) {
        if(otp == 9999) {
            String format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat dateFormater = new SimpleDateFormat(format);
            Date currentDateTime = new Date();
            long currentDateTimeMS = currentDateTime.getTime();
            long newDateTimeMS = currentDateTimeMS + 3600000;
            Date newDateTime = new Date(newDateTimeMS);

            UserOtp userOtp = new UserOtp();
            userOtp.setOtp(9999);
            userOtp.setUserCode("AB123456");
            userOtp.setValidity(dateFormater.format(newDateTime));
            return userOtp;
        }
        else if(otp == 8888){
            UserOtp userOtp = new UserOtp();
            userOtp.setOtp(8888);
            userOtp.setUserCode("AB123456");
            userOtp.setValidity("2000-10-10 12:12:12");
            return userOtp;
        }
        else {
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