package com.csci5308.stocki5.user.forgotcode;

import com.csci5308.stocki5.user.UserDbInterface;

public interface UserForgotCodeInterface {
    public String getUserCode(String email, String dob, UserDbInterface userDb);
}
