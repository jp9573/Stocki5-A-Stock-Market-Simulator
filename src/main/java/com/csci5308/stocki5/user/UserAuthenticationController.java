package com.csci5308.stocki5.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAuthenticationController
{
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView welcomePage()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error)
	{

		ModelAndView model = new ModelAndView();
		if (error != null)
		{
			model.addObject("error", "Invalid Credentials.");
		}

		model.setViewName("index");
		return model;
	}
}
