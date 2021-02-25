package com.csci5308.stocki5.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController
{

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
		try
		{
			user.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(dob));
		} catch (ParseException e)
		{
			e.printStackTrace();
			model.addObject("error", "Invalid Date of Birth.");
			model.setViewName("signup");
			return model;
		}
		user.setGender(gender);

		String isValid = user.validate();

		if (!isValid.equals("valid"))
		{
			model.addObject("error", isValid);
			model.setViewName("signup");
			return model;
		}
		
		model.setViewName("index");
		return model;
	}
}
