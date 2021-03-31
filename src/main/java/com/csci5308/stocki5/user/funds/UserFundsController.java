package com.csci5308.stocki5.user.funds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDb;

@Controller
public class UserFundsController
{
	@Autowired
	IUserFunds iUserFunds;

	@Autowired
	UserDb userDb;

	@RequestMapping(value = "/resetFunds", method = RequestMethod.POST)
	public ModelAndView resetFunds(@RequestParam(value = "userCode", required = true) String userCode)
	{

		User user = userDb.getUser(userCode);

		ModelAndView model = new ModelAndView();
		model.addObject("userCode", user.getUserCode());
		model.addObject("firstName", user.getFirstName());
		model.addObject("lastName", user.getLastName());
		model.addObject("emailId", user.getEmailId());
		model.addObject("contactNo", user.getContactNo());
		model.addObject("address", user.getAddress());
		model.addObject("province", user.getProvince());
		model.addObject("country", user.getCountry());
		model.addObject("gender", user.getGender());
		model.addObject("dateOfBirth", user.getDateOfBirth());
		model.addObject("internationalStockExchange", String.valueOf(user.getInternationalStockExchange()));
		model.addObject("internationalDerivativeExchange", String.valueOf(user.getInternationalDerivativeExchange()));
		model.addObject("internationalCommodityExchange", String.valueOf(user.getInternationalCommodityExchange()));
		model.addObject("foreignExchange", String.valueOf(user.getForeignExchange()));

		boolean isUserFundsUpdated = iUserFunds.resetFunds(user, userDb);
		model.addObject("funds", user.getFunds());
		if (isUserFundsUpdated)
		{
			model.addObject("successFunds", "User funds added successfully.");
		} else
		{
			model.addObject("errorFunds", "Error adding user Funds. Current balance should be less than 10000.");
		}

		model.setViewName("profile");
		return model;
	}
}
