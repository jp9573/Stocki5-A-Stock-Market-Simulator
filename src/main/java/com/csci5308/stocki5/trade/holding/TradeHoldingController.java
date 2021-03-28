package com.csci5308.stocki5.trade.holding;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.stock.StockDb;
import com.csci5308.stocki5.trade.TradeDb;

@Controller
public class TradeHoldingController
{
	@Autowired
	ITradeHolding iTradeHolding;
	
	@Autowired
	StockDb stockDb;

	@Autowired
	TradeDb tradeDb;

	@RequestMapping(value = { "/holdings" }, method = RequestMethod.GET)
	public ModelAndView getHoldings(HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		List<Holding> orders = iTradeHolding.fetchUserHoldings(principal.getName(), tradeDb, stockDb);
		model.addObject("orders", orders);
		model.setViewName("holdings");

		return model;
	}
}
