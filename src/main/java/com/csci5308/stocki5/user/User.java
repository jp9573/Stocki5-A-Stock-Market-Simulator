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

@SuppressWarnings("serial")
public class User implements UserDetails {
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
        if (null != this.getFirstName() && null != this.getLastName()) {
            char firstNameFirstChar = this.getFirstName().charAt(0);
            char lastNameFirstChar = this.getLastName().charAt(0);
            SimpleDateFormat simpleDateFormatYearMonth = new SimpleDateFormat("yyyyMM");
            SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("dd");
            SimpleDateFormat simpleDateFormatHourMinuteSecs = new SimpleDateFormat("HHmmss");
            long dateYearMonth = Long.parseLong(simpleDateFormatYearMonth.format(new Date()));
            long dateDay = Long.parseLong(simpleDateFormatDay.format(new Date()));
            long dateHourMinuteSecs = Long.parseLong(simpleDateFormatHourMinuteSecs.format(new Date()));
            long code = dateYearMonth + dateDay + dateHourMinuteSecs;
            String userCode = String.valueOf(firstNameFirstChar) + String.valueOf(lastNameFirstChar) + code;
            this.userCode = userCode.toUpperCase();
        }
    }

    public boolean validateDateOfBirth(String dob) {
        try {
            this.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(dob));
        } catch (ParseException e) {
            e.printStackTrace();
            this.setValidityMessage("Invalid Date of Birth.");
            return false;
        }

        Date date = new Date();
        long diffInMilliSeconds = date.getTime() - this.getDateOfBirth().getTime();
        long diff = TimeUnit.DAYS.convert(diffInMilliSeconds, TimeUnit.MILLISECONDS);
        if (diff <= 0) {
            this.setValidityMessage("Invalid Date of Birth.");
            return false;
        }
        if (diff < 6570) {
            this.setValidityMessage("Minimum age to sign up with STOCKI5 is 18 years or above.");
            return false;
        }
        this.setValidityMessage("Valid");
        return true;
    }

    public boolean validatePassword() {
        if (this.getPassword().length() < 8 || this.getPassword().length() > 20) {
            this.setValidityMessage("Password cannot be less than 8 characters or more than 20 characters.");
            return false;
        }

        if (!this.getPassword().equals(this.getConfirmPassword())) {
            this.setValidityMessage("Password and confirm password missmatch.");
            return false;
        }

        this.setValidityMessage("Valid");
        return true;
    }

    public boolean validate() {
        EmailValidator eValidator = EmailValidator.getInstance();
        if (!eValidator.isValid(this.getEmailId())) {
            this.setValidityMessage("Invalid Email Id");
            return false;
        }

        String regexContactNumber = ("(1)?[7-9][0-9]{9}");
        if (!this.getContactNo().matches(regexContactNumber)) {
            this.setValidityMessage("Invalid Contact Number");
            return false;
        }

        if (this.getFirstName().length() < 3 || this.getFirstName().length() > 20) {
            this.setValidityMessage("First name cannot be less than 3 characters or more than 20 characters.");
            return false;
        }

        if (this.getLastName().length() < 3 || this.getLastName().length() > 20) {
            this.setValidityMessage("Last name cannot be less than 3 characters or more than 20 characters.");
            return false;
        }

        if (this.getProvince().length() < 3 || this.getProvince().length() > 20) {
            this.setValidityMessage("State/Province cannot be less than 3 characters or more than 20 characters.");
            return false;
        }

        this.setValidityMessage("Valid");
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
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
