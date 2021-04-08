package com.csci5308.stocki5.user.funds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;

@Controller
public class UserFundsController
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
	private static final String FUNDS_UPDATE_SUCCESS_MESSAGE = "User funds added successfully.";
	private static final String FUNDS_UPDATE_ERROR_MESSAGE = "Error adding user Funds. Current balance should be less than 10000.";
	private static final String SUCCESSFUL_FUNDS = "successFunds";
	private static final String ERROR_FUNDS = "errorFunds";

	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserFunds iUserFunds = userFactory.createUserFunds();
	IUserDb iUserDb = userFactory.createUserDb();

	@RequestMapping(value = "/resetFunds", method = RequestMethod.POST)
	public ModelAndView resetFunds(@RequestParam(value = USER_CODE, required = true) String userCode)
	{

		IUser iUser = iUserDb.getUser(userCode);

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

		boolean isUserFundsUpdated = iUserFunds.resetFunds(iUser, iUserDb);
		model.addObject(FUNDS, iUser.getFunds());
		if (isUserFundsUpdated)
		{
			model.addObject(SUCCESSFUL_FUNDS, FUNDS_UPDATE_SUCCESS_MESSAGE);
		} else
		{
			model.addObject(ERROR_FUNDS, FUNDS_UPDATE_ERROR_MESSAGE);
		}

		model.setViewName("profile");
		return model;
	}
}
