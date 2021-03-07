package com.csci5308.stocki5.user;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserForgotPassword {

    public boolean validateUserCode(String userCode,
                                    UserDbInterface userDb){
        User user = userDb.getUser(userCode);
        if( user.getUserCode() == null){
            return false;
        }
        return true;
    }

    public boolean generateUserOtp(String userCode,
                                   UserOtpDbInterface userOtpDb){
        UserOtp userOtp = new UserOtp();
        userOtp.generateOtpForUser(userCode);
        userOtpDb.insertOtp(userOtp);
        return true;
    }

    public String verifyOtp(String userCode,
                            int otp,
                            UserOtpDbInterface userOtpDb){
        UserOtp userOtp = userOtpDb.getOtp(otp);
        if(userOtp == null){
            System.out.println("null get");
            return "Invalid OTP";
        }else{
            if( (userCode.equals(userOtp.getUserCode())) && (otp == userOtp.getOtp())){
                try {
                    Date currentDateTime = new Date();
                    Date validityDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(userOtp.getValidity());
                    if(currentDateTime.compareTo(validityDateTime) > 0){
                        userOtpDb.deleteOtp(otp);
                        return "OTP Expired";
                    }
                    else{
                        return "Valid";
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    return "Error. Please try again later.";
                }
            }
            return "Invalid OTP";
        }
    }

    public String resetPassword(String userCode,
                                 String password,
                                 String confirmPassword,
                                 UserDbInterface userDb,
                                 UserOtpDbInterface userOtpDb){
        User user = userDb.getUser(userCode);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        String result = user.validate();
        if (!result.equals("valid")) {
            return result;
        }
        userDb.updateUserPassword(user);
        userOtpDb.deleteOtpByUserCode(userCode);
        return "Success";
    }

}
