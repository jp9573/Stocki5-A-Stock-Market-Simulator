package com.csci5308.stocki5.user.password;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserOtpDb
{
	public boolean insertOtp(IUserOtp userOtp);

	public UserOtp getOtp(int otp);

	public boolean deleteOtp(int otp);

	public boolean deleteOtpByUserCode(String userCode);

}
