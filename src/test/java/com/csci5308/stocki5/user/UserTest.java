package com.csci5308.stocki5.user;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.user.factory.UserAbstractFactory;

public class UserTest {
    private IUser user = null;
    UserAbstractFactory userFactory = UserAbstractFactory.instance();

    @Before
    public void createObjects() {
        user = userFactory.createUser();
    }

    @After
    public void destroyObjects() {
        user = null;
    }

    @Test
    public void validateEmailTest() {
        user.setEmailId("example");
        user.validate();
        Assert.assertEquals("Invalid Email Id", user.getValidityMessage());
    }

    @Test
    public void validateContactNumber() {
        user.setEmailId("example@example.com");
        user.setContactNo("1234567");
        user.validate();
        Assert.assertEquals("Invalid Contact Number", user.getValidityMessage());
    }

    @Test
    public void validateFirstNameLessThanThreeChar() {
        user.setEmailId("example@example.com");
        user.setContactNo("19324678786");
        user.setFirstName("Jo");
        user.validate();
        Assert.assertEquals("First name cannot be less than 3 characters or more than 20 characters.", user.getValidityMessage());
    }

    @Test
    public void validateFirstNameMoreThanTwentyChar() {
        user.setEmailId("example@example.com");
        user.setContactNo("19324678786");
        user.setFirstName("Joxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        user.validate();
        Assert.assertEquals("First name cannot be less than 3 characters or more than 20 characters.", user.getValidityMessage());
    }

    @Test
    public void validateLastNameLessThanThreeChar() {
        user.setEmailId("example@example.com");
        user.setContactNo("19324678786");
        user.setFirstName("John");
        user.setLastName("Do");
        user.validate();
        Assert.assertEquals("Last name cannot be less than 3 characters or more than 20 characters.", user.getValidityMessage());
    }

    @Test
    public void validateLastNameMoreThanTwentyChar() {
        user.setEmailId("example@example.com");
        user.setContactNo("19324678786");
        user.setFirstName("John");
        user.setLastName("Doxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        user.validate();
        Assert.assertEquals("Last name cannot be less than 3 characters or more than 20 characters.", user.getValidityMessage());
    }

    @Test
    public void validateProvinceLessThanThreeChar() {
        user.setEmailId("example@example.com");
        user.setContactNo("19324678786");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setProvince("ha");
        user.validate();
        Assert.assertEquals("State/Province cannot be less than 3 characters or more than 20 characters.", user.getValidityMessage());
    }

    @Test
    public void validateProvinceMoreThanTwentyChar() {
        user.setEmailId("example@example.com");
        user.setContactNo("19324678786");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setProvince("halifaxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        user.validate();
        Assert.assertEquals("State/Province cannot be less than 3 characters or more than 20 characters.", user.getValidityMessage());
    }

    @Test
    public void validateDateOfBirth() {
        user.setEmailId("example@example.com");
        user.setContactNo("19324678786");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setProvince("halifax");
        user.setDateOfBirth(new Date());
        user.validateDateOfBirth("19940928");
        Assert.assertEquals("Invalid Date of Birth.", user.getValidityMessage());
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
        user.validateDateOfBirth("2009-09-28");
        Assert.assertEquals("Minimum age to sign up with STOCKI5 is 18 years or above.", user.getValidityMessage());
    }

    @Test
    public void validatePasswordLengthLessThanEight() {
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
        user.validatePassword();
        Assert.assertEquals("Password cannot be less than 8 characters or more than 20 characters.", user.getValidityMessage());
    }

    @Test
    public void validatePasswordLengthMoreThanTwenty() {
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
        user.validatePassword();
        Assert.assertEquals("Password cannot be less than 8 characters or more than 20 characters.", user.getValidityMessage());
    }

    @Test
    public void validatePasswordMismatchConfirmPassword() {
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
        user.validatePassword();
        Assert.assertEquals("Password and confirm password mismatch.", user.getValidityMessage());
    }

    @Test
    public void validatePasswordMatchConfirmPassword() {
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
        user.validateDateOfBirth("1994-09-28");
        user.validatePassword();
        user.validate();
        Assert.assertEquals("Valid", user.getValidityMessage());
    }

    @Test
    public void generateUserCodeTestNegative() {
        user.generateUserCode();
        Assert.assertNull(user.getUserCode());
    }

    @Test
    public void generateUserCodeTest() {
        user.setFirstName("John");
        user.setLastName("Doe");
        user.generateUserCode();
        Assert.assertNotNull(user.getUserCode());
    }
}
