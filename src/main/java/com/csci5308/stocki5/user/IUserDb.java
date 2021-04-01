package com.csci5308.stocki5.user;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserDb {
    String USER_TABLE = "user";
    String FIRST_NAME_COLUMN = "firstName";
    String LAST_NAME_COLUMN = "lastName";
    String PASSWORD_COLUMN = "password";
    String CONFIRM_PASSWORD_COLUMN = "confirmPassword";
    String EMAIL_ID_COLUMN = "emailId";
    String CONTACT_NO_COLUMN = "contactNo";
    String DATE_OF_BIRTH_COLUMN = "dateOfBirth";
    String GENDER_COLUMN = "gender";
    String ADDRESS_COLUMN = "address";
    String PROVINCE_COLUMN = "province";
    String COUNTRY_COLUMN = "country";
    String INTERNATIONAL_STOCK_EXCHANGE_COLUMN = "internationalStockExchange";
    String INTERNATIONAL_DERIVATIVE_EXCHANGE_COLUMN = "internationalDerivativeExchange";
    String INTERNATIONAL_COMMODITY_EXCHANGE_COLUMN = "internationalCommodityExchange";
    String FOREIGN_EXCHANGE_COLUMN = "foreignExchange";
    String USER_CODE_COLUMN = "userCode";
    String FUNDS_COLUMN = "funds";

    boolean insertUser(User user);

    boolean updateUser(User user);

    User getUser(String userCode);

    User getUserByEmail(String email);

    boolean updateUserPassword(User user);

    double getUserFunds(String userCode);

    boolean updateUserFunds(String userCode, double amount);
}
