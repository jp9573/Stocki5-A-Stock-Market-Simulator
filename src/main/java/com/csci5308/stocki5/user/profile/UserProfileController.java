package com.csci5308.stocki5.user.profile;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDb;

@Controller
public class UserProfileController
{
	@Autowired
	UserDb userDb;

	@Autowired
	IUserProfile iUserProfile;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView userProfile(HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();

		User user = userDb.getUser(principal.getName());

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
		model.addObject("funds", user.getFunds());

		model.setViewName("profile");
		return model;
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ModelAndView userProfile(@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName,
			@RequestParam(value = "emailId", required = true) String emailId,
			@RequestParam(value = "contactNo", required = true) String contactNo,
			@RequestParam(value = "address", required = true) String address,
			@RequestParam(value = "province", required = true) String province,
			@RequestParam(value = "country", required = true) String country,
			@RequestParam(value = "dateOfBirth", required = true) String dateOfBirth,
			@RequestParam(value = "gender", required = true) String gender,
			@RequestParam(value = "funds", required = true) double funds,
			@RequestParam(value = "internationalStockExchange", required = false) String internationalStockExchange,
			@RequestParam(value = "internationalDerivativeExchange", required = false) String internationalDerivativeExchange,
			@RequestParam(value = "internationalCommodityExchange", required = false) String internationalCommodityExchange,
			@RequestParam(value = "foreignExchange", required = false) String foreignExchange,
			@RequestParam(value = "userCode", required = true) String userCode)
	{

		if (internationalStockExchange == null)
		{
			internationalStockExchange = "0";
		}
		if (internationalDerivativeExchange == null)
		{
			internationalDerivativeExchange = "0";
		}
		if (internationalCommodityExchange == null)
		{
			internationalCommodityExchange = "0";
		}
		if (foreignExchange == null)
		{
			foreignExchange = "0";
		}

		ModelAndView model = new ModelAndView();
		model.addObject("firstName", firstName);
		model.addObject("lastName", lastName);
		model.addObject("emailId", emailId);
		model.addObject("contactNo", contactNo);
		model.addObject("address", address);
		model.addObject("province", province);
		model.addObject("country", country);
		model.addObject("dateOfBirth", dateOfBirth);
		model.addObject("gender", gender);
		model.addObject("userCode", userCode);
		model.addObject("internationalStockExchange", internationalStockExchange);
		model.addObject("internationalDerivativeExchange", internationalDerivativeExchange);
		model.addObject("internationalCommodityExchange", internationalCommodityExchange);
		model.addObject("foreignExchange", foreignExchange);
		model.addObject("funds", funds);

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setContactNo(contactNo);
		user.setEmailId(emailId);
		user.setAddress(address);
		user.setProvince(province);
		user.setCountry(country);
		user.setGender(gender);
		user.setUserCode(userCode);
		user.setInternationalStockExchange(Integer.parseInt(internationalStockExchange));
		user.setInternationalDerivativeExchange(Integer.parseInt(internationalDerivativeExchange));
		user.setInternationalCommodityExchange(Integer.parseInt(internationalCommodityExchange));
		user.setForeignExchange(Integer.parseInt(foreignExchange));

		boolean isUserUpdated = iUserProfile.updateUser(userDb, user, dateOfBirth);
		if (isUserUpdated)
		{
			model.addObject("success", "User information updated succesfully.");
		}

		model.addObject("error", user.getValidityMessage());
		model.setViewName("profile");
		return model;

	}

}
