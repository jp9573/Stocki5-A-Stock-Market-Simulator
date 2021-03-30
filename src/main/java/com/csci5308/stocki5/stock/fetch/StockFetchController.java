package com.csci5308.stocki5.stock.fetch;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.StockDb;
import com.csci5308.stocki5.stock.db.StockDbHighestLowest;
import com.csci5308.stocki5.user.UserDb;

@Controller
public class StockFetchController
{

	@Autowired
	IStockFetch iStockFetch;

	@Autowired
	StockDbHighestLowest stockDbHighestLowest;

	@Autowired
	StockDb stockDb;

	@Autowired
	UserDb userDb;

	@RequestMapping(value = { "/stocks" }, method = RequestMethod.GET)
	public ModelAndView stocksPage(HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();
		List<Stock> stocks = iStockFetch.fetchUserStocks(stockDb, userDb, principal.getName());
		List<Stock> top5GainersStocks = iStockFetch.fetchTop5GainerStocks(stockDbHighestLowest, userDb,
				principal.getName());
		List<Stock> top5LosersStocks = iStockFetch.fetchTop5LoserStocks(stockDbHighestLowest, userDb,
				principal.getName());
		model.addObject("stocks", stocks);
		model.addObject("gainers", top5GainersStocks);
		model.addObject("losers", top5LosersStocks);
		model.setViewName("stocks");
		return model;
	}

}
