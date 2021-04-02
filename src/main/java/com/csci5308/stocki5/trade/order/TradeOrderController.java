package com.csci5308.stocki5.trade.order;

import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.db.TradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class TradeOrderController
{
	public static final String ORDERS = "orders";

	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	ITradeOrder iTradeOrder = tradeFactory.createTradeOrder();
	ITradeDb tradeDb = tradeFactory.createTradeDb();
	
	@RequestMapping(value = { "/orders" }, method = RequestMethod.GET)
	public ModelAndView welcomePage(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		List<ITrade> orders = iTradeOrder.fetchUserOrders(principal.getName(), tradeDb);
		model.addObject(ORDERS, orders);
		model.setViewName("orders");

		return model;
	}
}
