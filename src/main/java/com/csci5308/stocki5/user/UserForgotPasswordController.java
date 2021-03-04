package com.csci5308.stocki5.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserForgotPasswordController {

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public ModelAndView userForgotPassword(){
        ModelAndView model = new ModelAndView();
        model.setViewName("forgotpassword");
        return model;
    }
}
