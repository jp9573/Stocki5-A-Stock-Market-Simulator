package com.csci5308.stocki5.user;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserDb {
    String userTable = "user";
    String firstNameColumn = "firstName";
    String lastNameColumn = "lastName";
    String passwordColumn = "password";
    String confirmPasswordColumn = "confirmPassword";
    String emailIdColumn = "emailId";
    String contactNoColumn = "contactNo";
    String dateOfBirthColumn = "dateOfBirth";
    String genderColumn = "gender";
    String addressColumn = "address";
    String provinceColumn = "province";
    String countryColumn = "country";
    String internationalStockExchangeColumn = "internationalStockExchange";
    String internationalDerivativeExchangeColumn = "internationalDerivativeExchange";
    String internationalCommodityExchangeColumn = "internationalCommodityExchange";
    String foreignExchangeColumn = "foreignExchange";
    String userCodeColumn = "userCode";
    String fundsColumn = "funds";

    boolean insertUser(User user);

    boolean updateUser(User user);

    User getUser(String userCode);

    User getUserByEmail(String email);

    boolean updateUserPassword(User user);

    double getUserFunds(String userCode);

    boolean updateUserFunds(String userCode, double amount);

}
