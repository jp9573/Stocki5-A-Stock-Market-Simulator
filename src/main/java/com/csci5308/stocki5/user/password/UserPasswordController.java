package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.email.Email;
import com.csci5308.stocki5.email.IEmail;
import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.IUserOtpDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.factory.UserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserPasswordController {
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
    private static final String OTP_SENT_MESSAGE = "OTP sent to registered email.";
    private static final String INVALID_USER_CODE_ERROR_MESSAGE = "Invalid Usercode";
    private static final String USER_OTP = "userOtp";
    private static final String OTP_VERIFIED_SUCCESS_MESSAGE = "Verified! Please reset your password.";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String PASSWORD_RESSET_SUCCESS_MESSAGE = "Password reset successful.";
    private static final String PASSWORD_CHANGE_SUCCESS_MESSAGE = "Password changed.";
    private static final String INVALID_CURRENT_PASSWORD_ERROR_MESSAGE = "Invalid Current Password";

    UserAbstractFactory userFactory = UserFactory.instance();
    IEmail email = Email.instance();

    IUserDb userDb = userFactory.createUserDb();
    IUserOtpDb userOtpDb = userFactory.createUserOtpDb();
    IUserOtp userOtp = userFactory.createUserOtp();
    IUserForgotPassword userForgotPassword = userFactory.createUserForgotPassword();
    IUserChangePassword userChangePassword = userFactory.createUserChangePassword();

    private ModelAndView getUserModel(IUser user) {
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

        return model;
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public ModelAndView userForgotPassword() {
        ModelAndView model = new ModelAndView();
        model.setViewName("forgotpassword");
        return model;
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public ModelAndView userForgotPassword(@RequestParam(value = USER_CODE, required = true) String userCode) {
        ModelAndView model = new ModelAndView();
        boolean validUserCode = userForgotPassword.validateUserCode(userCode, userDb);
        if (validUserCode) {
            userForgotPassword.generateUserOtp(userCode, userOtp, userOtpDb, userDb, email);
            model.addObject(USER_CODE, userCode);
            model.addObject("success", OTP_SENT_MESSAGE);
            model.setViewName("verifyotp");
        } else {
            model.addObject("error", INVALID_USER_CODE_ERROR_MESSAGE);
            model.setViewName("forgotpassword");
        }
        return model;
    }

    @RequestMapping(value = "/verifyotp", method = RequestMethod.GET)
    public ModelAndView verifyOtp() {
        ModelAndView model = new ModelAndView();
        model.setViewName("verifyotp");
        return model;
    }

    @RequestMapping(value = "/verifyotp", method = RequestMethod.POST)
    public ModelAndView verifyOtp(@RequestParam(value = USER_OTP, required = true) int otp,
                                  @RequestParam(value = USER_CODE, required = true) String userCode) {
        ModelAndView model = new ModelAndView();
        model.addObject(USER_CODE, userCode);
        boolean isValidOtp = userForgotPassword.verifyOtp(userCode, otp, userOtpDb);
        if (isValidOtp) {
            model.addObject("success", OTP_VERIFIED_SUCCESS_MESSAGE);
            model.setViewName("resetpassword");
        } else {
            model.addObject("error", userForgotPassword.getOtpValidityMessage());
            model.setViewName("verifyotp");
        }
        return model;
    }

    @RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
    public ModelAndView resetPassword() {
        ModelAndView model = new ModelAndView();
        model.setViewName("resetpassword");
        return model;
    }

    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public ModelAndView resetPassword(@RequestParam(value = PASSWORD, required = true) String password,
                                      @RequestParam(value = CONFIRM_PASSWORD, required = true) String confirmPassword,
                                      @RequestParam(value = USER_CODE, required = true) String userCode) {
        ModelAndView model = new ModelAndView();
        boolean isResset = userForgotPassword.resetPassword(userCode, password, confirmPassword, userDb, userOtpDb, userChangePassword);
        if (isResset) {
            model.addObject("success", PASSWORD_RESSET_SUCCESS_MESSAGE);
            model.setViewName("index");
        } else {
            model.addObject("error", userForgotPassword.getPasswordValidityMessage());
            model.setViewName("resetpassword");
        }
        return model;
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ModelAndView changePassword(@RequestParam(value = "userCode", required = true) String userCode,
                                       @RequestParam(value = PASSWORD, required = true) String currentPassword,
                                       @RequestParam(value = NEW_PASSWORD, required = true) String newPassword,
                                       @RequestParam(value = CONFIRM_PASSWORD, required = true) String confirmNewPassword) {

        IUser user = userDb.getUser(userCode);
        ModelAndView model = getUserModel(user);
        boolean currentPasswordIsValid = userChangePassword.validateCurrentPassword(user, currentPassword);
        if (currentPasswordIsValid) {
            boolean isChanged = userChangePassword.changePassword(user, newPassword, confirmNewPassword, userDb);
            if (isChanged) {
                model.addObject("successChangePassword", PASSWORD_CHANGE_SUCCESS_MESSAGE);
            } else {
                model.addObject("errorChangePassword", userChangePassword.getPasswordValidityMessage());
            }
        } else {
            model.addObject("errorChangePassword", INVALID_CURRENT_PASSWORD_ERROR_MESSAGE);
        }
        model.setViewName("profile");
        return model;
    }
}
