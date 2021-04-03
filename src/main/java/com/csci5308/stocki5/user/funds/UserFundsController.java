package com.csci5308.stocki5.user.funds;

import com.csci5308.stocki5.user.db.IUserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

@Controller
public class UserFundsController {
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL_ID = "emailId";
    private static final String CONTACT_NUMBER = "contactNo";
    private static final String ADDRESS = "address";
    private static final String PROVINCE = "province";
    private static final String COUNTRY = "country";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String GENDER = "gender";
    private static final String USER_CODE = "userCode";
    private static final String INTERNATIONAL_STOCK_EXCHANGE = "internationalStockExchange";
    private static final String INTERNATIONAL_DERIVATIVE_EXCHANGE = "internationalDerivativeExchange";
    private static final String INTERNATIONAL_COMMODITY_EXCHANGE = "internationalCommodityExchange";
    private static final String FOREIGN_EXCHANGE = "foreignExchange";
    private static final String FUNDS = "funds";
    private static final String FUNDS_UPDATE_SUCCESS_MESSAGE = "User funds added successfully.";
    private static final String FUNDS_UPDATE_ERROR_MESSAGE = "Error adding user Funds. Current balance should be less than 10000.";

    @Autowired
    IUserFunds iUserFunds;

    @Autowired
    IUserDb userDb;

    @RequestMapping(value = "/resetFunds", method = RequestMethod.POST)
    public ModelAndView resetFunds(@RequestParam(value = USER_CODE, required = true) String userCode) {

        IUser user = userDb.getUser(userCode);

        ModelAndView model = new ModelAndView();
        model.addObject(USER_CODE, user.getUserCode());
        model.addObject(FIRST_NAME, user.getFirstName());
        model.addObject(LAST_NAME, user.getLastName());
        model.addObject(EMAIL_ID, user.getEmailId());
        model.addObject(CONTACT_NUMBER, user.getContactNo());
        model.addObject(ADDRESS, user.getAddress());
        model.addObject(PROVINCE, user.getProvince());
        model.addObject(COUNTRY, user.getCountry());
        model.addObject(GENDER, user.getGender());
        model.addObject(DATE_OF_BIRTH, user.getDateOfBirth());
        model.addObject(INTERNATIONAL_STOCK_EXCHANGE, String.valueOf(user.getInternationalStockExchange()));
        model.addObject(INTERNATIONAL_DERIVATIVE_EXCHANGE, String.valueOf(user.getInternationalDerivativeExchange()));
        model.addObject(INTERNATIONAL_COMMODITY_EXCHANGE, String.valueOf(user.getInternationalCommodityExchange()));
        model.addObject(FOREIGN_EXCHANGE, String.valueOf(user.getForeignExchange()));

        boolean isUserFundsUpdated = iUserFunds.resetFunds(user, userDb);
        model.addObject(FUNDS, user.getFunds());
        if (isUserFundsUpdated) {
            model.addObject("successFunds", FUNDS_UPDATE_SUCCESS_MESSAGE);
        } else {
            model.addObject("errorFunds", FUNDS_UPDATE_ERROR_MESSAGE);
        }

        model.setViewName("profile");
        return model;
    }
}
