package com.csci5308.stocki5.user.signup;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.csci5308.stocki5.user.UserCode;
import com.csci5308.stocki5.user.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserSignUpController {
	@Autowired
	UserDb userDb;

	@Autowired
	UserSignUp userSignUp;

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
			@RequestParam(value = "gender", required = true) String gender) {
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

		UserCode user = new UserCode();
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
		try {
			user.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(dob));
		} catch (ParseException e) {
			e.printStackTrace();
			model.addObject("error", "Invalid Date of Birth.");
			model.setViewName("signup");
			return model;
		}

		String isValid = user.validate();

		if (!isValid.equals("valid")) {
			model.addObject("error", isValid);
			model.setViewName("signup");
			return model;
		}

		String insertMessage = userSignUp.addUser(userDb, user);

		if (insertMessage.equals("error")) {
			model.addObject("error", "Error in adding user. Please try again later.");
			model.setViewName("signup");
			return model;
		}

		model.addObject("username", insertMessage);
		model.setViewName("index");
		return model;
	}
}
