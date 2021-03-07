package com.csci5308.stocki5.stock;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.user.UserDb;

@Controller
public class StockController {

    @Autowired
    StockFetch stockFetch;

    @Autowired
    StockDb stockDb;

    @Autowired
    UserDb userDb;

    @RequestMapping(value = {"/stocks"}, method = RequestMethod.GET)
    public ModelAndView stocksPage(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        ModelAndView model = new ModelAndView();
        List<Stock> stocks = stockFetch.fetchUserStocks(stockDb, userDb, principal.getName());
        List<Stock> top5GainersStocks = stockFetch.fetchTop5GainerStocks(stockDb, userDb, principal.getName());
        List<Stock> top5LosersStocks = stockFetch.fetchTop5LoserStocks(stockDb, userDb, principal.getName());
        model.addObject("stocks", stocks);
        model.addObject("gainers", top5GainersStocks);
        model.addObject("losers", top5LosersStocks);
        model.setViewName("stocks");
        return model;
    }

}
