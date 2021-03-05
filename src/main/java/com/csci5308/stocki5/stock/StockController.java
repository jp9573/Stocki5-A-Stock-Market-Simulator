package com.csci5308.stocki5.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StockController {

	@Autowired
	StockPriceAlgorithm stockPriceAlgorithm;
	
	@RequestMapping(value = { "/stocks" }, method = RequestMethod.GET)
	public ModelAndView welcomePage()
	{
		ModelAndView model = new ModelAndView();
		stockPriceAlgorithm.generateStockPrice();
		model.setViewName("stocks");
		return model;
	}
	
}
