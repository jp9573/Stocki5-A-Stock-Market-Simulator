package com.csci5308.stocki5.stock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class StockPredictionController {

    @RequestMapping(value = {"/prediction"}, method = RequestMethod.GET)
    public ModelAndView stocksPage(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        ModelAndView model = new ModelAndView();
        model.setViewName("prediction");
        return model;
    }
}
