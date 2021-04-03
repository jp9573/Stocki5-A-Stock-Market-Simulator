package com.csci5308.stocki5.user.db;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;
import com.csci5308.stocki5.user.factory.UserFactoryMock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDbMock implements IUserDb {

    UserAbstractFactoryMock userFactory = UserFactoryMock.instance();

    @Override
    public boolean insertUser(IUser user) {
        if (user.getFirstName().equals("Tony")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(IUser user) {
        System.out.println(user.getUserCode());
        if (user.getFirstName().equals("Tony")) {
            return false;
        }
        return true;
    }

    @Override
    public IUser getUser(String userCode) {
        IUser user = userFactory.createUser();
        switch (userCode) {
            case "AB123456": {
                user.setEmailId("test@test.com");
                user.setUserCode("AB123456");
                user.setContactNo("19324678786");
                user.setFirstName("John");
                user.setLastName("Doe");
                user.setProvince("halifax");
                user.setPassword("password");
                user.setConfirmPassword("password");
                user.setInternationalDerivativeExchange(1);
                user.setInternationalCommodityExchange(1);
                user.setInternationalStockExchange(1);
                user.setForeignExchange(1);
                user.setFunds(50000);
                Date dob = null;
                try {
                    dob = new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-12");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                user.setDateOfBirth(dob);
                return user;
            }
            case "AB1234567": {
                user.setEmailId("test@test.com");
                user.setUserCode("AB123456");
                user.setInternationalDerivativeExchange(1);
                user.setInternationalCommodityExchange(1);
                user.setInternationalStockExchange(1);
                user.setForeignExchange(1);
                user.setFunds(5000);
                Date dob = null;
                try {
                    dob = new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-12");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                user.setDateOfBirth(dob);
                return user;
            }
            case "AB12345678": {
                user.setEmailId("test@test.com");
                user.setUserCode("AB123456");
                user.setInternationalDerivativeExchange(1);
                user.setInternationalCommodityExchange(1);
                user.setInternationalStockExchange(1);
                user.setForeignExchange(1);
                user.setFunds(0);
                Date dob = null;
                try {
                    dob = new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-12");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                user.setDateOfBirth(dob);
                return user;
            }
            default:
                return user;
        }
    }

    @Override
    public IUser getUserByEmail(String email) {
        if (email.equals("test@example.com")) {
            IUser user = userFactory.createUser();
            user.setEmailId(email);
            user.setUserCode("TEST12456789");
            Date dob = null;
            try {
                dob = new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-12");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setDateOfBirth(dob);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateUserPassword(IUser user) {
        return true;
    }

    @Override
    public double getUserFunds(String userCode) {
        if (userCode.equals("AB123456")) {
            return 5000;
        } else {
            return 50000;
        }
    }

    @Override
    public boolean updateUserFunds(String userCode, double amount) {
        return true;
    }

}
