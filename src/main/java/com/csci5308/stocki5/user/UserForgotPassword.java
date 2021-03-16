package com.csci5308.stocki5.user;

import com.csci5308.stocki5.Email.EmailInterface;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserForgotPassword extends UserChangePassword{

    public boolean validateUserCode(String userCode,
                                    UserDbInterface userDb){
        User user = userDb.getUser(userCode);
        if( user.getUserCode() == null){
            return false;
        }
        return true;
    }

    public boolean generateUserOtp(String userCode,
                                   UserOtpDbInterface userOtpDb,
                                   UserDbInterface userDb,
                                   EmailInterface email){
        UserOtp userOtp = new UserOtp();
        User user = userDb.getUser(userCode);

        userOtp.generateOtpForUser(userCode);
        userOtpDb.insertOtp(userOtp);

        String toEmail = user.getEmailId();
        String subject = "Stocki5: OTP TO RESET PASSWORD";
        String text = "The OTP to reset you password is - "+String.valueOf(userOtp.getOtp());
        email.sendEmail(toEmail, subject, text);
        return true;
    }

    public String verifyOtp(String userCode,
                            int otp,
                            UserOtpDbInterface userOtpDb){
        UserOtp userOtp = userOtpDb.getOtp(otp);
        if(userOtp == null){
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
        String result = super.changePassword(user, password, confirmPassword,userDb);
        if (result.equals("valid")) {
            userDb.updateUserPassword(user);
            userOtpDb.deleteOtpByUserCode(userCode);
            return "Success";
        }
        return result;
    }

}
