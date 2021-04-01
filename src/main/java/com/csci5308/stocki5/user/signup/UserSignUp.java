package com.csci5308.stocki5.user.signup;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.IUserDb;
import org.springframework.stereotype.Service;

@Service
public class UserSignUp implements IUserSignUp {
    private static final double INITIAL_FUNDS = 50000.0;
    private static final int ONE_VALUE = 1;
    private static final String USER_ROLE = "ROLE_USER";
    private static final String ADD_USER_ERROR_MESSAGE = "Error in adding user. Please try again later.";

    public boolean addUser(IUserDb dbInterface, User user, String dob) {
        boolean isDobValid = user.validateDateOfBirth(dob);
        boolean isPasswordValid = user.validatePassword();
        boolean isUserValid = user.validate();

        if (isDobValid && isPasswordValid && isUserValid) {
            user.setFunds(INITIAL_FUNDS);
            user.setInternationalCommodityExchange(ONE_VALUE);
            user.setInternationalDerivativeExchange(ONE_VALUE);
            user.setInternationalStockExchange(ONE_VALUE);
            user.setForeignExchange(ONE_VALUE);
            user.setRole(USER_ROLE);
            user.generateUserCode();
            boolean isUserAdded = dbInterface.insertUser(user);
            if (isUserAdded) {
                return true;
            } else {
                user.setValidityMessage(ADD_USER_ERROR_MESSAGE);
            }
        }
        return false;
    }
}
