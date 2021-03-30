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
import com.csci5308.stocki5.stock.db.StockDbGainersLosers;
import com.csci5308.stocki5.user.UserDb;

@Controller
public class StockFetchController
{
	@Autowired
	IStockFetch iStockFetch;

	@Autowired
	StockDbGainersLosers stockDbGainersLosers;

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
		List<Stock> topGainersStocks = iStockFetch.fetchTopGainerStocks(stockDbGainersLosers, userDb, principal.getName());
		List<Stock> topLosersStocks = iStockFetch.fetchTopLoserStocks(stockDbGainersLosers, userDb, principal.getName());
		model.addObject("stocks", stocks);
		model.addObject("gainers", topGainersStocks);
		model.addObject("losers", topLosersStocks);
		model.setViewName("stocks");
		return model;
	}
}
