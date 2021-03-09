package com.csci5308.stocki5.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserForgotCodeController {
    @Autowired
    UserDb userDb;

    @Autowired
    UserForgotCode userForgotCode;

    @RequestMapping(value = "/forgotusercode", method = RequestMethod.POST)
    public ModelAndView userForgotCode(@RequestParam(value = "emailId", required = true) String email,
                                       @RequestParam(value = "dob", required = true) String dob){
        ModelAndView model = new ModelAndView();
        String userCode = null;
        model.setViewName("forgotuser");
        userCode = userForgotCode.getUserCode(email, dob, userDb);
        if (userCode == null) {
            model.addObject("error", "Invalid email or date of birth.");
            return model;
        } else {
            model.addObject("success", "Your UserCode  is - "+userCode);
        }
        return model;
    }

}
