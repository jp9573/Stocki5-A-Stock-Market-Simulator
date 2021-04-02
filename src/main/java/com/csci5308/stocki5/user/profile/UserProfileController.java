package com.csci5308.stocki5.user.profile;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.db.UserDb;

@Controller
public class UserProfileController {
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
    private static final String USER_PROFILE_UPDATE_SUCCESS_MESSAGE = "User information updated successfully.";
    private static final String SECTOR_DEFAULT_VALUE = "0";

    @Autowired
    UserDb userDb;

    @Autowired
    IUserProfile iUserProfile;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView userProfile(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        User user = userDb.getUser(principal.getName());

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
        model.addObject(FUNDS, user.getFunds());

        model.setViewName("profile");
        return model;
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public ModelAndView userProfile(@RequestParam(value = FIRST_NAME) String firstName,
                                    @RequestParam(value = LAST_NAME) String lastName,
                                    @RequestParam(value = EMAIL_ID) String emailId,
                                    @RequestParam(value = CONTACT_NUMBER) String contactNo,
                                    @RequestParam(value = ADDRESS) String address,
                                    @RequestParam(value = PROVINCE) String province,
                                    @RequestParam(value = COUNTRY) String country,
                                    @RequestParam(value = DATE_OF_BIRTH) String dateOfBirth,
                                    @RequestParam(value = GENDER) String gender,
                                    @RequestParam(value = FUNDS) double funds,
                                    @RequestParam(value = INTERNATIONAL_STOCK_EXCHANGE, required = false) String internationalStockExchange,
                                    @RequestParam(value = INTERNATIONAL_DERIVATIVE_EXCHANGE, required = false) String internationalDerivativeExchange,
                                    @RequestParam(value = INTERNATIONAL_COMMODITY_EXCHANGE, required = false) String internationalCommodityExchange,
                                    @RequestParam(value = FOREIGN_EXCHANGE, required = false) String foreignExchange,
                                    @RequestParam(value = USER_CODE) String userCode) {

        if (null == internationalStockExchange) {
            internationalStockExchange = SECTOR_DEFAULT_VALUE;
        }
        if (null == internationalDerivativeExchange) {
            internationalDerivativeExchange = SECTOR_DEFAULT_VALUE;
        }
        if (null == internationalCommodityExchange) {
            internationalCommodityExchange = SECTOR_DEFAULT_VALUE;
        }
        if (null == foreignExchange) {
            foreignExchange = SECTOR_DEFAULT_VALUE;
        }

        ModelAndView model = new ModelAndView();
        model.addObject(FIRST_NAME, firstName);
        model.addObject(LAST_NAME, lastName);
        model.addObject(EMAIL_ID, emailId);
        model.addObject(CONTACT_NUMBER, contactNo);
        model.addObject(ADDRESS, address);
        model.addObject(PROVINCE, province);
        model.addObject(COUNTRY, country);
        model.addObject(DATE_OF_BIRTH, dateOfBirth);
        model.addObject(GENDER, gender);
        model.addObject(USER_CODE, userCode);
        model.addObject(INTERNATIONAL_STOCK_EXCHANGE, internationalStockExchange);
        model.addObject(INTERNATIONAL_DERIVATIVE_EXCHANGE, internationalDerivativeExchange);
        model.addObject(INTERNATIONAL_COMMODITY_EXCHANGE, internationalCommodityExchange);
        model.addObject(FOREIGN_EXCHANGE, foreignExchange);
        model.addObject(FUNDS, funds);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setContactNo(contactNo);
        user.setEmailId(emailId);
        user.setAddress(address);
        user.setProvince(province);
        user.setCountry(country);
        user.setGender(gender);
        user.setUserCode(userCode);
        user.setInternationalStockExchange(Integer.parseInt(internationalStockExchange));
        user.setInternationalDerivativeExchange(Integer.parseInt(internationalDerivativeExchange));
        user.setInternationalCommodityExchange(Integer.parseInt(internationalCommodityExchange));
        user.setForeignExchange(Integer.parseInt(foreignExchange));

        boolean isUserUpdated = iUserProfile.updateUser(userDb, user, dateOfBirth);
        if (isUserUpdated) {
            model.addObject("success", USER_PROFILE_UPDATE_SUCCESS_MESSAGE);
        }

        model.addObject("error", user.getValidityMessage());
        model.setViewName("profile");
        return model;

    }

}
