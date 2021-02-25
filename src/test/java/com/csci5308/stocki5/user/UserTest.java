package com.csci5308.stocki5.user;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class UserTest
{
	private User user = new User();

	@Test
	public void validateEmailTest()
	{
		user.setEmailId("example");
		Assert.assertEquals("Invalid Email Id", user.validate());
	}
	
	@Test
	public void validateContactNumber()
	{
		user.setEmailId("example@example.com");
		user.setContactNo("1234567");
		Assert.assertEquals("Invalid Contact Number", user.validate());
	}
	
	@Test
	public void validateFirstNameLessThanThreeChar()
	{
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("Jo");
		Assert.assertEquals("First name cannot be less than 3 characters or more than 20 characters.", user.validate());
	}
	
	@Test
	public void validateFirstNameMoreThanTwentyChar()
	{
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("Joxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Assert.assertEquals("First name cannot be less than 3 characters or more than 20 characters.", user.validate());
	}
	
	@Test
	public void validateLastNameLessThanThreeChar()
	{
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("John");
		user.setLastName("Do");
		Assert.assertEquals("Last name cannot be less than 3 characters or more than 20 characters.", user.validate());
	}
	
	@Test
	public void validateLastNameMoreThanTwentyChar()
	{
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("John");
		user.setLastName("Doxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Assert.assertEquals("Last name cannot be less than 3 characters or more than 20 characters.", user.validate());
	}
	
	@Test
	public void validateProvinceLessThanThreeChar()
	{
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setProvince("ha");
		Assert.assertEquals("State/Province cannot be less than 3 characters or more than 20 characters.", user.validate());
	}
	
	@Test
	public void validateProvinceMoreThanTwentyChar()
	{
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setProvince("halifaxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Assert.assertEquals("State/Province cannot be less than 3 characters or more than 20 characters.", user.validate());
	}
	
	@Test
	public void validateDateOfBirth() {
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setProvince("halifax");
		user.setDateOfBirth(new Date());
		Assert.assertEquals("Invalid Date of Birth.", user.validate());
	}
	
	@Test
	public void validateDateOfBirthMoreThanEighteenYears() {
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setProvince("halifax");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -17);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();
		
		user.setDateOfBirth(date);
		Assert.assertEquals("Minimum age to sign up with STOCKI5 is 18 years or above.", user.validate());
	}
	
	@Test
	public void validatePasswordLengthLessThanEight()
	{
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setProvince("halifax");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();
		
		user.setDateOfBirth(date);
		user.setPassword("1234567");
		Assert.assertEquals("Password cannot be less than 8 characters or more than 20 characters.", user.validate());
	}
	
	@Test
	public void validatePasswordLengthMoreThanTwenty()
	{
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setProvince("halifax");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();
		
		user.setDateOfBirth(date);
		user.setPassword("1234567123334135788eus2rdf3");
		Assert.assertEquals("Password cannot be less than 8 characters or more than 20 characters.", user.validate());
	}
	
	@Test
	public void validatePasswordMismatchConfirmPassword()
	{
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setProvince("halifax");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();
		
		user.setDateOfBirth(date);
		user.setPassword("12345678");
		user.setConfirmPassword("123456789");
		Assert.assertEquals("Password and confirm password missmatch.", user.validate());
	}
	
	@Test
	public void validatePasswordMatchConfirmPassword()
	{
		user.setEmailId("example@example.com");
		user.setContactNo("19324678786");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setProvince("halifax");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();
		
		user.setDateOfBirth(date);
		user.setPassword("12345678");
		user.setConfirmPassword("12345678");
		Assert.assertEquals("valid", user.validate());
	}
}
