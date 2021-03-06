package com.csci5308.stocki5.stock;

import java.security.Principal;
import java.util.ArrayList;
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

	@RequestMapping(value = { "/stocks" }, method = RequestMethod.GET)
	public ModelAndView stocksPage(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();
//		List<Stock> stocks = stockFetch.fetchUserStocks(stockDb, userDb, principal.getName());
		List<Stock> stocks = new ArrayList<Stock>();
		Stock stock1 = new Stock();
		stock1.setSymbol("ABC");
		stock1.setSegment("ISE");
		stock1.setHigh(31.24f);
		stock1.setLow(10.56f);
		stock1.setPrice(28.28f);
		stock1.setPreviousClose(25.34f);
		stock1.setPercentIncreaseDecrease(9.34f);
		
		Stock stock2 = new Stock();
		stock2.setSymbol("PQR");
		stock2.setSegment("FOREX");
		stock2.setHigh(33.24f);
		stock2.setLow(10.56f);
		stock2.setPrice(28.28f);
		stock2.setPreviousClose(31.34f);
		stock2.setPercentIncreaseDecrease(-9.34f);
		
		stocks.add(stock1);
		stocks.add(stock2);
		
		model.addObject("stocks", stocks);
		model.setViewName("stocks");
		return model;
	}

}
