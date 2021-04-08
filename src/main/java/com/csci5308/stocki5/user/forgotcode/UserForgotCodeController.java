package com.csci5308.stocki5.user.forgotcode;

import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserForgotCodeController
{
	public static final String FORGOT_USER_VIEW_NAME = "forgotuser";
	public static final String EMAIL_ID = "emailId";
	public static final String DOB = "dob";
	public static final String ERROR_MESSAGE = "Invalid email or date of birth.";
	public static final String ERROR = "error";
	public static final String SUCCESS_MESSAGE = "Your UserCode  is - ";

	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserDb userDb = userFactory.createUserDb();
	IUserForgotCode iUserForgotCode = userFactory.createUserForgotCode();

	@RequestMapping(value = "/forgotuser", method = RequestMethod.POST)
	public ModelAndView userForgotCode(@RequestParam(value = EMAIL_ID, required = true) String email, @RequestParam(value = DOB, required = true) String dob)
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("forgotuser");
		String result = iUserForgotCode.getUserCode(email, dob, userDb);
		if (null == result)
		{
			model.addObject(ERROR, ERROR_MESSAGE);
			return model;
		} else
		{
			model.addObject("success", SUCCESS_MESSAGE + result);
		}
		return model;
	}

	@RequestMapping(value = "/forgotuser", method = RequestMethod.GET)
	public ModelAndView userForgotCode()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("forgotuser");
		return model;
	}
}
