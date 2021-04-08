package com.csci5308.stocki5.user.db;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.user.password.IUserOtp;

@Repository
public interface IUserOtpDb
{
	public boolean insertOtp(IUserOtp iUserOtp);

	public IUserOtp getOtp(int otp);

	public boolean deleteOtp(int otp);

	public boolean deleteOtpByUserCode(String userCode);
}
