package com.csci5308.stocki5.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
    private static final String YEAR_MONTH_PATTERN = "yyyyMM";
    private static final String YEAR_MONTH_DAY_PATTERN = "yyyy-MM-dd";
    private static final String DAY_PATTERN = "dd";
    private static final String HOUR_MINUTE_SECOND_PATTERN = "HHmmss";
    private static final String CONTACT_NUMBER_PATTERN = "(1)?[7-9][0-9]{9}";
    private static final int MINIMUM_CHARACTERS_LIMIT_FOR_NAME = 3;
    private static final int MAXIMUM_CHARACTERS_LIMIT_FOR_NAME = 20;
    private static final int MINIMUM_CHARACTERS_LIMIT_FOR_PASSWORD = 8;
    private static final int MAXIMUM_CHARACTERS_LIMIT_FOR_PASSWORD = 20;
    private static final String INVALID_DOB_MESSAGE = "Invalid Date of Birth.";
    private static final String INVALID_AGE_MESSAGE = "Minimum age to sign up with STOCKI5 is 18 years or above.";
    private static final String INVALID_PASSWORD_LENGTH_MESSAGE = String.format("Password cannot be less than %d characters or more than %d characters.", MINIMUM_CHARACTERS_LIMIT_FOR_PASSWORD, MAXIMUM_CHARACTERS_LIMIT_FOR_PASSWORD);
    private static final String INVALID_CONFIRM_PASSWORD_MESSAGE = "Password and confirm password mismatch.";
    private static final String INVALID_EMAIL_MESSAGE = "Invalid Email Id";
    private static final String INVALID_CONTACT_NUMBER_MESSAGE = "Invalid Contact Number";
    private static final String INVALID_FIRST_NAME_LENGTH_MESSAGE = String.format("First name cannot be less than %d characters or more than %d characters.", MINIMUM_CHARACTERS_LIMIT_FOR_NAME, MAXIMUM_CHARACTERS_LIMIT_FOR_NAME);
    private static final String INVALID_LAST_NAME_LENGTH_MESSAGE = String.format("Last name cannot be less than %d characters or more than %d characters.", MINIMUM_CHARACTERS_LIMIT_FOR_NAME, MAXIMUM_CHARACTERS_LIMIT_FOR_NAME);
    private static final String INVALID_PROVINCE_LENGTH_MESSAGE = String.format("State/Province cannot be less than %d characters or more than %d characters.", MINIMUM_CHARACTERS_LIMIT_FOR_NAME, MAXIMUM_CHARACTERS_LIMIT_FOR_NAME);
    private static final String VALID_MESSAGE = "Valid";

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
    private int internationalStockExchange;
    private int internationalDerivativeExchange;
    private int internationalCommodityExchange;
    private int foreignExchange;
    private double funds;
    private String role;
    private String validityMessage;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getInternationalStockExchange() {
        return internationalStockExchange;
    }

    public void setInternationalStockExchange(int internationalStockExchange) {
        this.internationalStockExchange = internationalStockExchange;
    }

    public int getInternationalDerivativeExchange() {
        return internationalDerivativeExchange;
    }

    public void setInternationalDerivativeExchange(int internationalDerivativeExchange) {
        this.internationalDerivativeExchange = internationalDerivativeExchange;
    }

    public int getInternationalCommodityExchange() {
        return internationalCommodityExchange;
    }

    public void setInternationalCommodityExchange(int internationalCommodityExchange) {
        this.internationalCommodityExchange = internationalCommodityExchange;
    }

    public int getForeignExchange() {
        return foreignExchange;
    }

    public void setForeignExchange(int foreignExchange) {
        this.foreignExchange = foreignExchange;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getValidityMessage() {
        return validityMessage;
    }

    public void setValidityMessage(String validityMessage) {
        this.validityMessage = validityMessage;
    }

    public void generateUserCode() {
        char firstNameFirstChar = this.getFirstName().charAt(0);
        char lastNameFirstChar = this.getLastName().charAt(0);
        SimpleDateFormat simpleDateFormatYearMonth = new SimpleDateFormat(YEAR_MONTH_PATTERN);
        SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat(DAY_PATTERN);
        SimpleDateFormat simpleDateFormatHourMinuteSecs = new SimpleDateFormat(HOUR_MINUTE_SECOND_PATTERN);
        long dateYearMonth = Long.parseLong(simpleDateFormatYearMonth.format(new Date()));
        long dateDay = Long.parseLong(simpleDateFormatDay.format(new Date()));
        long dateHourMinuteSecs = Long.parseLong(simpleDateFormatHourMinuteSecs.format(new Date()));
        long code = dateYearMonth + dateDay + dateHourMinuteSecs;
        String userCode = String.valueOf(firstNameFirstChar) + lastNameFirstChar + code;
        this.userCode = userCode.toUpperCase();
    }

    public boolean validateDateOfBirth(String dob) {
        try {
            this.setDateOfBirth(new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN).parse(dob));
        } catch (ParseException e) {
            e.printStackTrace();
            this.setValidityMessage(INVALID_DOB_MESSAGE);
            return false;
        }

        Date date = new Date();
        long diffInMilliSeconds = date.getTime() - this.getDateOfBirth().getTime();
        long diff = TimeUnit.DAYS.convert(diffInMilliSeconds, TimeUnit.MILLISECONDS);
        if (diff <= 0) {
            this.setValidityMessage(INVALID_DOB_MESSAGE);
            return false;
        }
        if (diff < 6570) {
            this.setValidityMessage(INVALID_AGE_MESSAGE);
            return false;
        }
        this.setValidityMessage(VALID_MESSAGE);
        return true;
    }

    public boolean validatePassword() {
        if (this.getPassword().length() < MINIMUM_CHARACTERS_LIMIT_FOR_PASSWORD || this.getPassword().length() > MAXIMUM_CHARACTERS_LIMIT_FOR_PASSWORD) {
            this.setValidityMessage(INVALID_PASSWORD_LENGTH_MESSAGE);
            return false;
        }

        if (!this.getPassword().equals(this.getConfirmPassword())) {
            this.setValidityMessage(INVALID_CONFIRM_PASSWORD_MESSAGE);
            return false;
        }

        this.setValidityMessage(VALID_MESSAGE);
        return true;
    }

    public boolean validate() {
        EmailValidator eValidator = EmailValidator.getInstance();
        if (!eValidator.isValid(this.getEmailId())) {
            this.setValidityMessage(INVALID_EMAIL_MESSAGE);
            return false;
        }

        if (!this.getContactNo().matches((CONTACT_NUMBER_PATTERN))) {
            this.setValidityMessage(INVALID_CONTACT_NUMBER_MESSAGE);
            return false;
        }

        if (this.getFirstName().length() < MINIMUM_CHARACTERS_LIMIT_FOR_NAME || this.getFirstName().length() > MAXIMUM_CHARACTERS_LIMIT_FOR_NAME) {
            this.setValidityMessage(INVALID_FIRST_NAME_LENGTH_MESSAGE);
            return false;
        }

        if (this.getLastName().length() < MINIMUM_CHARACTERS_LIMIT_FOR_NAME || this.getLastName().length() > MAXIMUM_CHARACTERS_LIMIT_FOR_NAME) {
            this.setValidityMessage(INVALID_LAST_NAME_LENGTH_MESSAGE);
            return false;
        }

        if (this.getProvince().length() < MINIMUM_CHARACTERS_LIMIT_FOR_NAME || this.getProvince().length() > MAXIMUM_CHARACTERS_LIMIT_FOR_NAME) {
            this.setValidityMessage(INVALID_PROVINCE_LENGTH_MESSAGE);
            return false;
        }

        this.setValidityMessage(VALID_MESSAGE);
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public String getUsername() {
        return this.getUserCode();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
