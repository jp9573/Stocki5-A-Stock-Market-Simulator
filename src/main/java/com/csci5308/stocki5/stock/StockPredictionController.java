package com.csci5308.stocki5.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class StockPredictionController {

    @Autowired
    StockDb stockDb;

    @Autowired
    StockPrediction stockPrediction;

    @RequestMapping(value = {"/predict"}, method = RequestMethod.POST)
    public ModelAndView stocksPage(HttpServletRequest request,
                                   @RequestParam(value = "stockName", required = true) String stockName) {
        Principal principal = request.getUserPrincipal();
        ModelAndView model = new ModelAndView();
        List<Stock> predictionList = stockPrediction.predictStockValue(stockDb, stockName);
        model.addObject("predictionList", predictionList);
        model.setViewName("prediction");
        return model;
    }
}
