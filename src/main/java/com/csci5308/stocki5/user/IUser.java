package com.csci5308.stocki5.user;

import java.util.Date;

public interface IUser
{
	public String getUserCode();

	public void setUserCode(String userCode);

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getEmailId();

	public void setEmailId(String emailId);

	public String getContactNo();

	public void setContactNo(String contactNo);

	public String getPassword();

	public void setPassword(String password);

	public String getConfirmPassword();

	public void setConfirmPassword(String confirmPassword);

	public Date getDateOfBirth();

	public void setDateOfBirth(Date dateOfBirth);

	public String getGender();

	public void setGender(String gender);

	public String getAddress();

	public void setAddress(String address);

	public String getProvince();

	public void setProvince(String province);

	public String getCountry();

	public void setCountry(String country);

	public int getInternationalStockExchange();

	public void setInternationalStockExchange(int internationalStockExchange);

	public int getInternationalDerivativeExchange();

	public void setInternationalDerivativeExchange(int internationalDerivativeExchange);

	public int getInternationalCommodityExchange();

	public void setInternationalCommodityExchange(int internationalCommodityExchange);

	public int getForeignExchange();

	public void setForeignExchange(int foreignExchange);

	public double getFunds();

	public void setFunds(double funds);

	public String getRole();

	public void setRole(String role);

	public String getValidityMessage();

	public void setValidityMessage(String validityMessage);

	public void generateUserCode();

	public boolean validateDateOfBirth(String dob);

	public boolean validatePassword();

	public boolean validate();
}
