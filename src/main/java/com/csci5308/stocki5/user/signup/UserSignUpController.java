package com.csci5308.stocki5.user.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;

@Controller
public class UserSignUpController
{
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL_ID = "emailId";
	private static final String CONTACT_NUMBER = "contactNumber";
	private static final String ADDRESS = "address";
	private static final String PROVINCE = "province";
	private static final String COUNTRY = "country";
	private static final String PASSWORD = "password";
	private static final String CONFIRM_PASSWORD = "confirmPassword";
	private static final String DOB = "dob";
	private static final String GENDER = "gender";
	private static final String ERROR = "error";
	private static final String USERNAME = "username";

	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserSignUp iUserSignUp = userFactory.createUserSignUp();
	IUserDb iUserDb = userFactory.createUserDb();

	@RequestMapping(value = "/signupuser", method = RequestMethod.POST)
	public ModelAndView signUpUser(@RequestParam(value = FIRST_NAME) String firstName, @RequestParam(value = LAST_NAME) String lastName, @RequestParam(value = EMAIL_ID) String emailId, @RequestParam(value = CONTACT_NUMBER) String contactNumber, @RequestParam(value = ADDRESS) String address, @RequestParam(value = PROVINCE) String province, @RequestParam(value = COUNTRY) String country, @RequestParam(value = PASSWORD) String password, @RequestParam(value = CONFIRM_PASSWORD) String confirmPassword, @RequestParam(value = DOB) String dob, @RequestParam(value = GENDER) String gender)
	{
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

		IUser iUser = userFactory.createUser();
		iUser.setPassword(password);
		iUser.setConfirmPassword(confirmPassword);
		iUser.setEmailId(emailId);
		iUser.setFirstName(firstName);
		iUser.setLastName(lastName);
		iUser.setContactNo(contactNumber);
		iUser.setAddress(address);
		iUser.setProvince(province);
		iUser.setCountry(country);
		iUser.setGender(gender);

		boolean isUserAdded = iUserSignUp.addUser(iUserDb, iUser, dob);
		if (isUserAdded)
		{
			model.addObject(USERNAME, iUser.getUserCode());
			model.setViewName("index");
			return model;
		}
		model.addObject(ERROR, iUser.getValidityMessage());
		model.setViewName("signup");
		return model;
	}
}
