package com.csci5308.stocki5.user;

import java.util.Date;

import org.apache.commons.validator.routines.EmailValidator;

public class User
{
	private String userCode;

	private String firstName;

	private String lastName;

	private String emailId;

	private String contactNo;

	private String password;
	
	private String confirmPassword;

	private Date dateOfBirth;
	
	private String gender;

	private String address;

	private String province;

	private String country;

	private boolean internationalStockExchange;

	private boolean internationalDerivativeExchange;

	private boolean internationalCommodityExchange;

	private boolean foreignExchange;

	private double funds;

	public final String getUserCode()
	{
		return userCode;
	}

	public final void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

	public final String getFirstName()
	{
		return firstName;
	}

	public final void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public final String getLastName()
	{
		return lastName;
	}

	public final void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public final String getEmailId()
	{
		return emailId;
	}

	public final void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public final String getContactNo()
	{
		return contactNo;
	}

	public final void setContactNo(String contactNo)
	{
		this.contactNo = contactNo;
	}

	public final String getPassword()
	{
		return password;
	}

	public final void setPassword(String password)
	{
		this.password = password;
	}

	public final String getConfirmPassword()
	{
		return confirmPassword;
	}

	public final void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

	public final Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	public final void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public final String getGender()
	{
		return gender;
	}

	public final void setGender(String gender)
	{
		this.gender = gender;
	}

	public final String getAddress()
	{
		return address;
	}

	public final void setAddress(String address)
	{
		this.address = address;
	}

	public final String getProvince()
	{
		return province;
	}

	public final void setProvince(String province)
	{
		this.province = province;
	}

	public final String getCountry()
	{
		return country;
	}

	public final void setCountry(String country)
	{
		this.country = country;
	}

	public final boolean isInternationalStockExchange()
	{
		return internationalStockExchange;
	}

	public final void setInternationalStockExchange(boolean internationalStockExchange)
	{
		this.internationalStockExchange = internationalStockExchange;
	}

	public final boolean isInternationalDerivativeExchange()
	{
		return internationalDerivativeExchange;
	}

	public final void setInternationalDerivativeExchange(boolean internationalDerivativeExchange)
	{
		this.internationalDerivativeExchange = internationalDerivativeExchange;
	}

	public final boolean isInternationalCommodityExchange()
	{
		return internationalCommodityExchange;
	}

	public final void setInternationalCommodityExchange(boolean internationalCommodityExchange)
	{
		this.internationalCommodityExchange = internationalCommodityExchange;
	}

	public final boolean isForeignExchange()
	{
		return foreignExchange;
	}

	public final void setForeignExchange(boolean foreignExchange)
	{
		this.foreignExchange = foreignExchange;
	}

	public final double getFunds()
	{
		return funds;
	}

	public final void setFunds(double funds)
	{
		this.funds = funds;
	}

	public final String validate()
	{
		EmailValidator eValidator = EmailValidator.getInstance();
		if (!eValidator.isValid(this.getEmailId()))
		{
			return "Invalid Email Id";
		}

		String regexContactNumber = ("(1)?[7-9][0-9]{9}");
		if (!this.getContactNo().matches(regexContactNumber))
		{
			return "Invalid Contact Number";
		}

		if (this.getFirstName().length() < 3 || this.getFirstName().length() > 20)
		{
			return "First name cannot be less than 3 characters or more than 20 characters.";
		}

		if (this.getLastName().length() < 3 || this.getLastName().length() > 20)
		{
			return "Last name cannot be less than 3 characters or more than 20 characters.";
		}
		
		if (this.getProvince().length() < 3 || this.getProvince().length() > 20)
		{
			return "State/Province cannot be less than 3 characters or more than 20 characters.";
		}

		if (this.getPassword().length() < 8 || this.getPassword().length() > 20)
		{
			return "Password cannot be less than 8 characters or more than 20 characters.";
		}
		
		if (!this.getPassword().equals(this.getConfirmPassword()))
		{
			return "Password and confirm password missmatch.";
		}

		return "valid";
	}
}
