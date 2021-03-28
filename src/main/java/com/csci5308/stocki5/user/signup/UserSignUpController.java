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
public class UserSignUpController
{
	@Autowired
	UserDb userDb;

	@Autowired
	IUserSignUp iUserSignUp;

	@RequestMapping(value = "/signupuser", method = RequestMethod.POST)
	public ModelAndView signUpUser(@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName,
			@RequestParam(value = "emailId", required = true) String emailId,
			@RequestParam(value = "contactNumber", required = true) String contactNumber,
			@RequestParam(value = "address", required = true) String address,
			@RequestParam(value = "province", required = true) String province,
			@RequestParam(value = "country", required = true) String country,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "confirmPassword", required = true) String confirmPassword,
			@RequestParam(value = "dob", required = true) String dob,
			@RequestParam(value = "gender", required = true) String gender)
	{
		ModelAndView model = new ModelAndView();
		model.addObject("firstName", firstName);
		model.addObject("lastName", lastName);
		model.addObject("emailId", emailId);
		model.addObject("contactNumber", contactNumber);
		model.addObject("address", address);
		model.addObject("province", province);
		model.addObject("password", password);
		model.addObject("confirmPassword", confirmPassword);
		model.addObject("gender", gender);
		model.addObject("country", country);
		model.addObject("dob", dob);

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setContactNo(contactNumber);
		user.setEmailId(emailId);
		user.setAddress(address);
		user.setProvince(province);
		user.setCountry(country);
		user.setPassword(password);
		user.setConfirmPassword(confirmPassword);
		user.setGender(gender);

		boolean isUserAdded = iUserSignUp.addUser(userDb, user, dob);
		if (isUserAdded)
		{
			model.addObject("username", user.getUserCode());
			model.setViewName("index");
			return model;
		}
		model.addObject("error", user.getValidityMessage());
		model.setViewName("signup");
		return model;
	}
}
