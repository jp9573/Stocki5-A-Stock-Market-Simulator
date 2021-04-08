package com.csci5308.stocki5.stock.fetch;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;

@Controller
public class StockFetchController
{
	private static final String STOCKS = "stocks";
	private static final String GAINERS = "gainers";
	private static final String LOSERS = "losers";

	StockAbstractFactory stockFactory = StockAbstractFactory.instance();
	UserAbstractFactory userFactory = UserAbstractFactory.instance();

	IStockFetch iStockFetch = stockFactory.createStockFetch();
	IStockDbGainersLosers iStockDbGainersLosers = stockFactory.createStockDbGainersLosers();
	IStockDb iStockDb = stockFactory.createStockDb();
	IUserDb userDb = userFactory.createUserDb();

	@RequestMapping(value = { "/stocks" }, method = RequestMethod.GET)
	public ModelAndView stocksPage(HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();
		
		List<IStock> iStocks = iStockFetch.fetchUserStocks(iStockDb, userDb, principal.getName());
		List<IStock> topGainersStocks = iStockFetch.fetchTopGainerStocks(iStockDbGainersLosers, userDb, principal.getName());
		List<IStock> topLosersStocks = iStockFetch.fetchTopLoserStocks(iStockDbGainersLosers, userDb, principal.getName());
		
		model.addObject(STOCKS, iStocks);
		model.addObject(GAINERS, topGainersStocks);
		model.addObject(LOSERS, topLosersStocks);
		model.setViewName("stocks");
		
		return model;
	}
}
