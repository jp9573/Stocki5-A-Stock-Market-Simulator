package com.csci5308.stocki5.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserForgotPasswordController {

    @Autowired
    UserDb userDb;

    @Autowired
    UserOtpDb userOtpDb;

    @Autowired
    UserForgotPassword userForgotPassword;


    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public ModelAndView userForgotPassword(){
        ModelAndView model = new ModelAndView();
        model.setViewName("forgotpassword");
        return model;
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public ModelAndView userForgotPassword(@RequestParam(value = "userCode", required = true) String userCode){
        ModelAndView model = new ModelAndView();
        boolean validUserCode = userForgotPassword.validateUserCode(userCode, userDb);
        if (validUserCode) {
            userForgotPassword.generateUserOtp(userCode, userOtpDb);
            model.addObject("userCode", userCode);
            model.addObject("success","Email with otp sent.");
            model.setViewName("verifyotp");
        }
        else{
            model.addObject("error", "Invalid Usercode");
            model.setViewName("forgotpassword");
        }
        return model;
    }

    @RequestMapping(value = "/verifyotp", method = RequestMethod.GET)
    public ModelAndView verifyOtp(){
        ModelAndView model = new ModelAndView();
        model.setViewName("verifyotp");
        return model;
    }

    @RequestMapping(value = "/verifyotp", method = RequestMethod.POST)
    public ModelAndView verifyOtp(@RequestParam(value = "userOtp", required = true) int otp,
                                  @RequestParam(value = "userCode", required = true) String userCode){
        ModelAndView model = new ModelAndView();
        model.addObject("userCode", userCode);
        String result = userForgotPassword.verifyOtp(userCode, otp, userOtpDb);
        if(result.equals("Valid")){
            model.addObject("success","Verified! Please reset your password.");
            model.setViewName("resetpassword");
        }
        else{
            model.addObject("error",result);
            model.setViewName("verifyotp");
        }
        return model;
    }

    @RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
    public  ModelAndView resetPassword(){
        ModelAndView model = new ModelAndView();
        model.setViewName("resetpassword");
        return model;
    }

    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public ModelAndView resetPassword(@RequestParam(value = "password", required = true) String password,
                                      @RequestParam(value = "confirmPassword", required = true) String confirmPassword,
                                      @RequestParam(value = "userCode",required = true) String userCode){
        ModelAndView model = new ModelAndView();
        String result = userForgotPassword.resetPassword(userCode, password, confirmPassword, userDb, userOtpDb);
        if(result.equals("Success")){
            model.addObject("success","Password reset successful.");
            model.setViewName("index");
        }else{
            model.addObject("error","Password reset successful.");
            model.setViewName("resetpassword");
        }
        return model;
    }
}
