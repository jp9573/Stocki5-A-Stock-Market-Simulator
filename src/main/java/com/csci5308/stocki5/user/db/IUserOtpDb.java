package com.csci5308.stocki5.user.db;

import com.csci5308.stocki5.user.password.IUserOtp;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserOtpDb {
    public boolean insertOtp(IUserOtp userOtp);

    public IUserOtp getOtp(int otp);

    public boolean deleteOtp(int otp);

    public boolean deleteOtpByUserCode(String userCode);
}
