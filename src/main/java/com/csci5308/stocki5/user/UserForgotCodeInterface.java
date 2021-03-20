package com.csci5308.stocki5.user;

public interface UserForgotCodeInterface {
    public String getUserCode(String email, String dob, UserDbInterface userDb);
}
