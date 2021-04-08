package com.csci5308.stocki5.user.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAuthenticationController
{
	private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid Credentials.";
	public static final String ERROR = "error";

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView welcomePage()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = ERROR, required = false) String error)
	{
		ModelAndView model = new ModelAndView();
		if (null == error)
		{
			model.setViewName("index");
		} else
		{
			model.addObject(ERROR, INVALID_CREDENTIALS_MESSAGE);
			model.setViewName("index");
		}
		return model;
	}
}
