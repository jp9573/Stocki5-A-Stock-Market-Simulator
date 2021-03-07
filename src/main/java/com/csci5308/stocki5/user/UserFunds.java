package com.csci5308.stocki5.user;

import org.springframework.beans.factory.annotation.Value;

public class UserFunds{

    @Value("${user.resetFundAmount}")
    private String resetFundAmount;

    private double funds;

    public UserFunds(String userCode, UserDbInterface userDb){
        this.funds = userDb.getUserFunds(userCode);
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public boolean resetFunds(User user, UserDbInterface userDb){
        if(user.getFunds() < 10000){
            user.setFunds(10000);
            return userDb.updateUserFunds(user.getUserCode(), 10000);
        } else {
            return false;
        }
    }
}
