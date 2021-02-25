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

	public String getUserCode()
	{
		return userCode;
	}

	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getContactNo()
	{
		return contactNo;
	}

	public void setContactNo(String contactNo)
	{
		this.contactNo = contactNo;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public boolean isInternationalStockExchange()
	{
		return internationalStockExchange;
	}

	public void setInternationalStockExchange(boolean internationalStockExchange)
	{
		this.internationalStockExchange = internationalStockExchange;
	}

	public boolean isInternationalDerivativeExchange()
	{
		return internationalDerivativeExchange;
	}

	public void setInternationalDerivativeExchange(boolean internationalDerivativeExchange)
	{
		this.internationalDerivativeExchange = internationalDerivativeExchange;
	}

	public boolean isInternationalCommodityExchange()
	{
		return internationalCommodityExchange;
	}

	public void setInternationalCommodityExchange(boolean internationalCommodityExchange)
	{
		this.internationalCommodityExchange = internationalCommodityExchange;
	}

	public boolean isForeignExchange()
	{
		return foreignExchange;
	}

	public void setForeignExchange(boolean foreignExchange)
	{
		this.foreignExchange = foreignExchange;
	}

	public double getFunds()
	{
		return funds;
	}

	public void setFunds(double funds)
	{
		this.funds = funds;
	}

	public String validate()
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
