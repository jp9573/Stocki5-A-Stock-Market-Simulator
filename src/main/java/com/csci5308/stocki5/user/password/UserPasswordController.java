package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.csci5308.stocki5.email.Email;

@Controller
public class UserPasswordController
{

	@Autowired
	UserDb userDb;

	@Autowired
	IUserOtpDb userOtpDb;

	@Autowired
	IUserOtp userOtp;

	@Autowired
	IUserForgotPassword userForgotPassword;

	@Autowired
	IUserChangePassword userChangePassword;

	@Autowired
	Email email;

	private ModelAndView getUserModel(User user)
	{
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

		return model;
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public ModelAndView userForgotPassword()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("forgotpassword");
		return model;
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public ModelAndView userForgotPassword(@RequestParam(value = "userCode", required = true) String userCode)
	{
		ModelAndView model = new ModelAndView();
		boolean validUserCode = userForgotPassword.validateUserCode(userCode, userDb);
		if (validUserCode)
		{
			userForgotPassword.generateUserOtp(userCode, userOtp, userOtpDb, userDb, email);
			model.addObject("userCode", userCode);
			model.addObject("success", "OTP sent to registered email.");
			model.setViewName("verifyotp");
		} else
		{
			model.addObject("error", "Invalid Usercode");
			model.setViewName("forgotpassword");
		}
		return model;
	}

	@RequestMapping(value = "/verifyotp", method = RequestMethod.GET)
	public ModelAndView verifyOtp()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("verifyotp");
		return model;
	}

	@RequestMapping(value = "/verifyotp", method = RequestMethod.POST)
	public ModelAndView verifyOtp(@RequestParam(value = "userOtp", required = true) int otp,
			@RequestParam(value = "userCode", required = true) String userCode)
	{
		ModelAndView model = new ModelAndView();
		model.addObject("userCode", userCode);
		boolean isValidOtp = userForgotPassword.verifyOtp(userCode, otp, userOtpDb);
		if (isValidOtp)
		{
			model.addObject("success", "Verified! Please reset your password.");
			model.setViewName("resetpassword");
		} else
		{
			model.addObject("error", userForgotPassword.getOtpValidityMessage());
			model.setViewName("verifyotp");
		}
		return model;
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public ModelAndView resetPassword()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("resetpassword");
		return model;
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public ModelAndView resetPassword(@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "confirmPassword", required = true) String confirmPassword,
			@RequestParam(value = "userCode", required = true) String userCode)
	{
		ModelAndView model = new ModelAndView();
		boolean isResset = userForgotPassword.resetPassword(userCode, password, confirmPassword, userDb, userOtpDb, userChangePassword);
		if (isResset)
		{
			model.addObject("success", "Password reset successful.");
			model.setViewName("index");
		} else
		{
			model.addObject("error", userForgotPassword.getPasswordValidityMessage());
			model.setViewName("resetpassword");
		}
		return model;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@RequestParam(value = "userCode", required = true) String userCode,
			@RequestParam(value = "currentPassword", required = true) String currentPassword,
			@RequestParam(value = "newPassword", required = true) String newPassword,
			@RequestParam(value = "confirmNewPassword", required = true) String confirmNewPassword)
	{

		User user = userDb.getUser(userCode);
		ModelAndView model = getUserModel(user);
		boolean currentPasswordIsValid = userChangePassword.validateCurrentPassword(user, currentPassword);
		if (currentPasswordIsValid)
		{
			boolean isChanged = userChangePassword.changePassword(user, newPassword, confirmNewPassword, userDb);
			if (isChanged)
			{
				model.addObject("successChangePassword", "Password changed.");
			} else
			{
				model.addObject("errorChangePassword", userChangePassword.getPasswordValidityMessage());
			}
		} else
		{
			model.addObject("errorChangePassword", "Invalid Current Password");
		}
		model.setViewName("profile");
		return model;
	}
}
