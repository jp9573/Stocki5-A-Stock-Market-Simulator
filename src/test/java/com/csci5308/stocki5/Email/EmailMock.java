package com.csci5308.stocki5.Email;


public class EmailMock implements EmailInterface{
    @Override
    public boolean sendEmail(String toEmail, String subject, String content) {
        return true;
    }
}