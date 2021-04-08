package com.csci5308.stocki5.user.profile;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;

@Controller
public class UserProfileController
{
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL_ID = "emailId";
	private static final String CONTACT_NUMBER = "contactNo";
	private static final String ADDRESS = "address";
	private static final String PROVINCE = "province";
	private static final String COUNTRY = "country";
	private static final String DATE_OF_BIRTH = "dateOfBirth";
	private static final String GENDER = "gender";
	private static final String USER_CODE = "userCode";
	private static final String INTERNATIONAL_STOCK_EXCHANGE = "internationalStockExchange";
	private static final String INTERNATIONAL_DERIVATIVE_EXCHANGE = "internationalDerivativeExchange";
	private static final String INTERNATIONAL_COMMODITY_EXCHANGE = "internationalCommodityExchange";
	private static final String FOREIGN_EXCHANGE = "foreignExchange";
	private static final String FUNDS = "funds";
	private static final String USER_PROFILE_UPDATE_SUCCESS_MESSAGE = "User information updated successfully.";
	private static final String SECTOR_DEFAULT_VALUE = "0";

	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserProfile iUserProfile = userFactory.createUserProfile();
	IUserDb iUserDb = userFactory.createUserDb();

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView userProfile(HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();

		IUser iUser = iUserDb.getUser(principal.getName());

		ModelAndView model = new ModelAndView();
		model.addObject(USER_CODE, iUser.getUserCode());
		model.addObject(FIRST_NAME, iUser.getFirstName());
		model.addObject(LAST_NAME, iUser.getLastName());
		model.addObject(EMAIL_ID, iUser.getEmailId());
		model.addObject(CONTACT_NUMBER, iUser.getContactNo());
		model.addObject(ADDRESS, iUser.getAddress());
		model.addObject(PROVINCE, iUser.getProvince());
		model.addObject(COUNTRY, iUser.getCountry());
		model.addObject(GENDER, iUser.getGender());
		model.addObject(DATE_OF_BIRTH, iUser.getDateOfBirth());
		model.addObject(INTERNATIONAL_STOCK_EXCHANGE, String.valueOf(iUser.getInternationalStockExchange()));
		model.addObject(INTERNATIONAL_DERIVATIVE_EXCHANGE, String.valueOf(iUser.getInternationalDerivativeExchange()));
		model.addObject(INTERNATIONAL_COMMODITY_EXCHANGE, String.valueOf(iUser.getInternationalCommodityExchange()));
		model.addObject(FOREIGN_EXCHANGE, String.valueOf(iUser.getForeignExchange()));
		model.addObject(FUNDS, iUser.getFunds());

		model.setViewName("profile");
		return model;
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ModelAndView userProfile(@RequestParam(value = FIRST_NAME) String firstName, @RequestParam(value = LAST_NAME) String lastName, @RequestParam(value = EMAIL_ID) String emailId, @RequestParam(value = CONTACT_NUMBER) String contactNo, @RequestParam(value = ADDRESS) String address, @RequestParam(value = PROVINCE) String province, @RequestParam(value = COUNTRY) String country, @RequestParam(value = DATE_OF_BIRTH) String dateOfBirth, @RequestParam(value = GENDER) String gender, @RequestParam(value = FUNDS) double funds, @RequestParam(value = INTERNATIONAL_STOCK_EXCHANGE, required = false) String internationalStockExchange, @RequestParam(value = INTERNATIONAL_DERIVATIVE_EXCHANGE, required = false) String internationalDerivativeExchange, @RequestParam(value = INTERNATIONAL_COMMODITY_EXCHANGE, required = false) String internationalCommodityExchange, @RequestParam(value = FOREIGN_EXCHANGE, required = false) String foreignExchange, @RequestParam(value = USER_CODE) String userCode)
	{

		if (null == internationalStockExchange)
		{
			internationalStockExchange = SECTOR_DEFAULT_VALUE;
		}
		if (null == internationalDerivativeExchange)
		{
			internationalDerivativeExchange = SECTOR_DEFAULT_VALUE;
		}
		if (null == internationalCommodityExchange)
		{
			internationalCommodityExchange = SECTOR_DEFAULT_VALUE;
		}
		if (null == foreignExchange)
		{
			foreignExchange = SECTOR_DEFAULT_VALUE;
		}

		ModelAndView model = new ModelAndView();
		model.addObject(FIRST_NAME, firstName);
		model.addObject(LAST_NAME, lastName);
		model.addObject(EMAIL_ID, emailId);
		model.addObject(CONTACT_NUMBER, contactNo);
		model.addObject(ADDRESS, address);
		model.addObject(PROVINCE, province);
		model.addObject(COUNTRY, country);
		model.addObject(DATE_OF_BIRTH, dateOfBirth);
		model.addObject(GENDER, gender);
		model.addObject(USER_CODE, userCode);
		model.addObject(INTERNATIONAL_STOCK_EXCHANGE, internationalStockExchange);
		model.addObject(INTERNATIONAL_DERIVATIVE_EXCHANGE, internationalDerivativeExchange);
		model.addObject(INTERNATIONAL_COMMODITY_EXCHANGE, internationalCommodityExchange);
		model.addObject(FOREIGN_EXCHANGE, foreignExchange);
		model.addObject(FUNDS, funds);
		model.setViewName("profile");

		IUser iUser = userFactory.createUser();
		iUser.setFirstName(firstName);
		iUser.setLastName(lastName);
		iUser.setContactNo(contactNo);
		iUser.setEmailId(emailId);
		iUser.setAddress(address);
		iUser.setProvince(province);
		iUser.setCountry(country);
		iUser.setGender(gender);
		iUser.setUserCode(userCode);
		iUser.setInternationalStockExchange(Integer.parseInt(internationalStockExchange));
		iUser.setInternationalDerivativeExchange(Integer.parseInt(internationalDerivativeExchange));
		iUser.setInternationalCommodityExchange(Integer.parseInt(internationalCommodityExchange));
		iUser.setForeignExchange(Integer.parseInt(foreignExchange));

		boolean isUserUpdated = iUserProfile.updateUser(iUserDb, iUser, dateOfBirth);
		if (isUserUpdated)
		{
			model.addObject("success", USER_PROFILE_UPDATE_SUCCESS_MESSAGE);
			return model;
		}

		model.addObject("error", iUser.getValidityMessage());
		return model;

	}

}
