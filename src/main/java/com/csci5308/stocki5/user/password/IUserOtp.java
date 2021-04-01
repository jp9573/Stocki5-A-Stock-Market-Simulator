package com.csci5308.stocki5.user.password;

public interface IUserOtp {
    public int getOtp();

    public String getValidity();

    public String getUserCode();

    public int getOtpMinValue();

    public int getOtpMaxValue();

    public void setOtpMinValue(int otpMinValue);

    public void setOtpMaxValue(int otpMaxValue);

    public void generateOtpForUser(String userCode);

}
