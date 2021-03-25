package com.csci5308.stocki5.email;

import com.csci5308.stocki5.email.EmailInterface;

public class EmailMock implements EmailInterface{
    @Override
    public boolean sendEmail(String toEmail, String subject, String content) {
        return true;
    }
}