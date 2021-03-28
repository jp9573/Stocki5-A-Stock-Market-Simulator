package com.csci5308.stocki5.email;

import com.csci5308.stocki5.email.IEmail;

public class EmailMock implements IEmail{
    @Override
    public boolean sendEmail(String toEmail, String subject, String content) {
        return true;
    }
}