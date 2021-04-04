package com.csci5308.stocki5.trade.holding;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockFactory;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class TradeHoldingController
{
	public static final String ORDERS = "orders";

	TradeAbstractFactory tradeFactory = TradeAbstractFactory.instance();
	StockAbstractFactory stockFactory = StockAbstractFactory.instance();

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
