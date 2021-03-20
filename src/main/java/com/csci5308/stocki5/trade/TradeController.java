package com.csci5308.stocki5.trade;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.StockDb;
import com.csci5308.stocki5.stock.StockFetch;
import com.csci5308.stocki5.user.UserDb;

@Controller
public class TradeController {

	@Autowired
	StockFetch stockFetch;
	
	@Autowired
	TradeFetch tradeFetch;

	@Autowired
	TradeBuy tradeBuy;

	@Autowired
	StockDb stockDb;

	@Autowired
	UserDb userDb;

	@Autowired
	TradeDb tradeDb;

	@RequestMapping(value = { "/orders" }, method = RequestMethod.GET)
	public ModelAndView welcomePage(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();
		
		List<Trade> orders = tradeFetch.fetchUserOrders(principal.getName(), tradeDb);
		model.addObject("orders", orders);
		model.setViewName("orders");
		
		return model;
	}

	@RequestMapping(value = { "/holdings" }, method = RequestMethod.GET)
	public ModelAndView getHoldings(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		List<Trade> orders = tradeFetch.fetchUserOrders(principal.getName(), tradeDb);
		model.addObject("orders", orders);
		model.setViewName("orders");

		return model;
	}

	@RequestMapping(value = "/buystock", method = RequestMethod.POST)
	public ModelAndView buyStock(HttpServletRequest request, @RequestParam(value = "buystockid") int stockId,
			@RequestParam(value = "quantity") int quantity) {
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		boolean isBought = tradeBuy.buyStock(principal.getName(), stockId, quantity, stockDb, userDb, tradeDb);
		if (isBought) {
			List<Trade> orders = tradeFetch.fetchUserOrders(principal.getName(), tradeDb);
			model.addObject("orders", orders);
			model.setViewName("orders");
			return model;
		}

		List<Stock> stocks = stockFetch.fetchUserStocks(stockDb, userDb, principal.getName());
		List<Stock> top5GainersStocks = stockFetch.fetchTop5GainerStocks(stockDb, userDb, principal.getName());
		List<Stock> top5LosersStocks = stockFetch.fetchTop5LoserStocks(stockDb, userDb, principal.getName());
		
		model.addObject("stocks", stocks);
		model.addObject("gainers", top5GainersStocks);
		model.addObject("losers", top5LosersStocks);
		model.addObject("error", "Insufficient funds");
		model.setViewName("stocks");
		return model;
	}

}
