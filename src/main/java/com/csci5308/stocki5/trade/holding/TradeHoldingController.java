package com.csci5308.stocki5.trade.holding;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockFactory;
import com.csci5308.stocki5.trade.db.TradeDb;

@Controller
public class TradeHoldingController
{
	public static final String ORDERS = "orders";

	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	StockAbstractFactory stockFactory = StockFactory.instance();

	ITradeHolding iTradeHolding = tradeFactory.createTradeHolding();
	ITradeDb tradeDb = tradeFactory.createTradeDb();
	IStockDb iStockDb = stockFactory.createStockDb();

	@RequestMapping(value = { "/holdings" }, method = RequestMethod.GET)
	public ModelAndView getHoldings(HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		List<IHolding> orders = iTradeHolding.fetchUserHoldings(principal.getName(), tradeDb, iStockDb);
		model.addObject(ORDERS, orders);
		model.setViewName("holdings");

		return model;
	}
}
