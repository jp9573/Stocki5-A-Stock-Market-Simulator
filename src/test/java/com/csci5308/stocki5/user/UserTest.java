package com.csci5308.stocki5.user;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.user.factory.UserAbstractFactory;

public class UserTest
{
	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	private IUser iUser = null;

	@Before
	public void createObjects()
	{
		iUser = userFactory.createUser();
	}

	@After
	public void destroyObjects()
	{
		iUser = null;
	}

	@Test
	public void validateEmailTest()
	{
		iUser.setEmailId("example");
		iUser.validate();
		Assert.assertEquals("Invalid Email Id", iUser.getValidityMessage());
	}

	@Test
	public void validateContactNumber()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("1234567");
		iUser.validate();
		Assert.assertEquals("Invalid Contact Number", iUser.getValidityMessage());
	}

	@Test
	public void validateFirstNameLessThanThreeChar()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("Jo");
		iUser.validate();
		Assert.assertEquals("First name cannot be less than 3 characters or more than 20 characters.", iUser.getValidityMessage());
	}

	@Test
	public void validateFirstNameMoreThanTwentyChar()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("Joxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		iUser.validate();
		Assert.assertEquals("First name cannot be less than 3 characters or more than 20 characters.", iUser.getValidityMessage());
	}

	@Test
	public void validateLastNameLessThanThreeChar()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("John");
		iUser.setLastName("Do");
		iUser.validate();
		Assert.assertEquals("Last name cannot be less than 3 characters or more than 20 characters.", iUser.getValidityMessage());
	}

	@Test
	public void validateLastNameMoreThanTwentyChar()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("John");
		iUser.setLastName("Doxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		iUser.validate();
		Assert.assertEquals("Last name cannot be less than 3 characters or more than 20 characters.", iUser.getValidityMessage());
	}

	@Test
	public void validateProvinceLessThanThreeChar()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("John");
		iUser.setLastName("Doe");
		iUser.setProvince("ha");
		iUser.validate();
		Assert.assertEquals("State/Province cannot be less than 3 characters or more than 20 characters.", iUser.getValidityMessage());
	}

	@Test
	public void validateProvinceMoreThanTwentyChar()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("John");
		iUser.setLastName("Doe");
		iUser.setProvince("halifaxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		iUser.validate();
		Assert.assertEquals("State/Province cannot be less than 3 characters or more than 20 characters.", iUser.getValidityMessage());
	}

	@Test
	public void validateDateOfBirth()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("John");
		iUser.setLastName("Doe");
		iUser.setProvince("halifax");
		iUser.setDateOfBirth(new Date());
		iUser.validateDateOfBirth("19940928");
		Assert.assertEquals("Invalid Date of Birth.", iUser.getValidityMessage());
	}

	@Test
	public void validateDateOfBirthMoreThanEighteenYears()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("John");
		iUser.setLastName("Doe");
		iUser.setProvince("halifax");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -17);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();

		iUser.setDateOfBirth(date);
		iUser.validateDateOfBirth("2009-09-28");
		Assert.assertEquals("Minimum age to sign up with STOCKI5 is 18 years or above.", iUser.getValidityMessage());
	}

	@Test
	public void validatePasswordLengthLessThanEight()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("John");
		iUser.setLastName("Doe");
		iUser.setProvince("halifax");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();

		iUser.setDateOfBirth(date);
		iUser.setPassword("1234567");
		iUser.validatePassword();
		Assert.assertEquals("Password cannot be less than 8 characters or more than 20 characters.", iUser.getValidityMessage());
	}

	@Test
	public void validatePasswordLengthMoreThanTwenty()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("John");
		iUser.setLastName("Doe");
		iUser.setProvince("halifax");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();

		iUser.setDateOfBirth(date);
		iUser.setPassword("1234567123334135788eus2rdf3");
		iUser.validatePassword();
		Assert.assertEquals("Password cannot be less than 8 characters or more than 20 characters.", iUser.getValidityMessage());
	}

	@Test
	public void validatePasswordMismatchConfirmPassword()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("John");
		iUser.setLastName("Doe");
		iUser.setProvince("halifax");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();

		iUser.setDateOfBirth(date);
		iUser.setPassword("12345678");
		iUser.setConfirmPassword("123456789");
		iUser.validatePassword();
		Assert.assertEquals("Password and confirm password mismatch.", iUser.getValidityMessage());
	}

	@Test
	public void validatePasswordMatchConfirmPassword()
	{
		iUser.setEmailId("example@example.com");
		iUser.setContactNo("19324678786");
		iUser.setFirstName("John");
		iUser.setLastName("Doe");
		iUser.setProvince("halifax");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();

		iUser.setDateOfBirth(date);
		iUser.setPassword("12345678");
		iUser.setConfirmPassword("12345678");
		iUser.validateDateOfBirth("1994-09-28");
		iUser.validatePassword();
		iUser.validate();
		Assert.assertEquals("Valid", iUser.getValidityMessage());
	}

	@Test
	public void generateUserCodeTestNegative()
	{
		iUser.generateUserCode();
		Assert.assertNull(iUser.getUserCode());
	}

	@Test
	public void generateUserCodeTest()
	{
		iUser.setFirstName("John");
		iUser.setLastName("Doe");
		iUser.generateUserCode();
		Assert.assertNotNull(iUser.getUserCode());
	}
}
