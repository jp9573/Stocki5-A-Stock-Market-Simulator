package com.csci5308.stocki5.user.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDb;

@Controller
public class UserSignUpController {
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL_ID = "emailId";
    public static final String CONTACT_NUMBER = "contactNumber";
    public static final String ADDRESS = "address";
    public static final String PROVINCE = "province";
    public static final String COUNTRY = "country";
    public static final String PASSWORD = "password";
    public static final String CONFIRM_PASSWORD = "confirmPassword";
    public static final String DOB = "dob";
    public static final String GENDER = "gender";
    public static final String ERROR = "error";
    public static final String USERNAME = "username";

    @Autowired
    UserDb userDb;

    @Autowired
    IUserSignUp IUserSignUp;

    @RequestMapping(value = "/signupuser", method = RequestMethod.POST)
    public ModelAndView signUpUser(@RequestParam(value = FIRST_NAME, required = true) String firstName,
                                   @RequestParam(value = LAST_NAME, required = true) String lastName,
                                   @RequestParam(value = EMAIL_ID, required = true) String emailId,
                                   @RequestParam(value = CONTACT_NUMBER, required = true) String contactNumber,
                                   @RequestParam(value = ADDRESS, required = true) String address,
                                   @RequestParam(value = PROVINCE, required = true) String province,
                                   @RequestParam(value = COUNTRY, required = true) String country,
                                   @RequestParam(value = PASSWORD, required = true) String password,
                                   @RequestParam(value = CONFIRM_PASSWORD, required = true) String confirmPassword,
                                   @RequestParam(value = DOB, required = true) String dob,
                                   @RequestParam(value = GENDER, required = true) String gender) {
        ModelAndView model = new ModelAndView();
        model.addObject(FIRST_NAME, firstName);
        model.addObject(LAST_NAME, lastName);
        model.addObject(EMAIL_ID, emailId);
        model.addObject(CONTACT_NUMBER, contactNumber);
        model.addObject(ADDRESS, address);
        model.addObject(PROVINCE, province);
        model.addObject(PASSWORD, password);
        model.addObject(CONFIRM_PASSWORD, confirmPassword);
        model.addObject(GENDER, gender);
        model.addObject(COUNTRY, country);
        model.addObject(DOB, dob);

        User user = new User();
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        user.setEmailId(emailId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setContactNo(contactNumber);
        user.setAddress(address);
        user.setProvince(province);
        user.setCountry(country);
        user.setGender(gender);

        boolean isUserAdded = IUserSignUp.addUser(userDb, user, dob);
        if (isUserAdded) {
            model.addObject(USERNAME, user.getUserCode());
            model.setViewName("index");
            return model;
        }
        model.addObject(ERROR, user.getValidityMessage());
        model.setViewName("signup");
        return model;
    }
}
