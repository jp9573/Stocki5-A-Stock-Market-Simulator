package com.csci5308.stocki5.trade.order;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeDb;

@Controller
public class TradeOrderController
{
	@Autowired
	ITradeOrder iTradeOrder;
	
	@Autowired
	TradeDb tradeDb;
	
	@RequestMapping(value = { "/orders" }, method = RequestMethod.GET)
	public ModelAndView welcomePage(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		List<Trade> orders = iTradeOrder.fetchUserOrders(principal.getName(), tradeDb);
		model.addObject("orders", orders);
		model.setViewName("orders");

		return model;
	}
}
