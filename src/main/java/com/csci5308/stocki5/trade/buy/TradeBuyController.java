package com.csci5308.stocki5.trade.buy;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockFactory;
import com.csci5308.stocki5.stock.fetch.IStockFetch;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import com.csci5308.stocki5.trade.order.ITradeOrder;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.factory.UserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class TradeBuyController
{
	public static final String BUY_STOCK_ID = "buystockid";
	public static final String QUANTITY = "quantity";
	public static final String ORDERS = "orders";
	public static final String STOCKS = "stocks";
	public static final String GAINERS = "gainers";
	public static final String LOSERS = "losers";
	public static final String SET_BUY_STOCK_ID = "setbuystockid";
	public static final String SET_QUANTITY = "setquantity";
	public static final String SET_BUY_PRICE = "setbuyprice";
	public static final String INSUFFICIENT_FUNDS_ERROR_MESSAGE = "Insufficient funds";

	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	StockAbstractFactory stockFactory = StockFactory.instance();
	UserAbstractFactory userFactory = UserFactory.instance();

	ITradeOrder iTradeOrder = tradeFactory.createTradeOrder();
	ITradeBuy iTradeBuy = tradeFactory.createTradeBuy();
	IStockDbGainersLosers iStockDbGainersLosers = stockFactory.createStockDbGainersLosers();
	IStockFetch iStockFetch = stockFactory.createStockFetch();
	IStockDb iStockDb = stockFactory.createStockDb();
	ITradeDb tradeDb = tradeFactory.createTradeDb();
	IUserDb userDb = userFactory.createUserDb();


	@RequestMapping(value = "/buystock", method = RequestMethod.POST)
	public ModelAndView buyStock(HttpServletRequest request, @RequestParam(value = BUY_STOCK_ID) int stockId, @RequestParam(value = QUANTITY) int quantity)
	{
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		boolean isBought = iTradeBuy.buyStock(principal.getName(), stockId, quantity, iStockDb, userDb, tradeDb);
		if (isBought)
		{
			List<ITrade> orders = iTradeOrder.fetchUserOrders(principal.getName(), tradeDb);
			model.addObject(ORDERS, orders);
			model.setViewName("orders");
			return model;
		}

		List<IStock> stocks = iStockFetch.fetchUserStocks(iStockDb, userDb, principal.getName());
		List<IStock> top5GainersStocks = iStockFetch.fetchTopGainerStocks(iStockDbGainersLosers, userDb, principal.getName());
		List<IStock> top5LosersStocks = iStockFetch.fetchTopLoserStocks(iStockDbGainersLosers, userDb, principal.getName());

		model.addObject(STOCKS, stocks);
		model.addObject(GAINERS, top5GainersStocks);
		model.addObject(LOSERS, top5LosersStocks);
		model.addObject("error", INSUFFICIENT_FUNDS_ERROR_MESSAGE);
		model.setViewName("stocks");
		return model;
	}

	@RequestMapping(value = "/setbuystock", method = RequestMethod.POST)
	public ModelAndView setBuyStock(HttpServletRequest request, @RequestParam(value = SET_BUY_STOCK_ID) int stockId, @RequestParam(value = SET_QUANTITY) int quantity, @RequestParam(value = SET_BUY_PRICE) float buyPrice)
	{
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		boolean isBought = iTradeBuy.setBuyPrice(principal.getName(), stockId, quantity, buyPrice, iStockDb, userDb, tradeDb);
		if (isBought)
		{
			List<ITrade> orders = iTradeOrder.fetchUserOrders(principal.getName(), tradeDb);
			model.addObject(ORDERS, orders);
			model.setViewName("orders");
			return model;
		}

		List<IStock> stocks = iStockFetch.fetchUserStocks(iStockDb, userDb, principal.getName());
		List<IStock> top5GainersStocks = iStockFetch.fetchTopGainerStocks(iStockDbGainersLosers, userDb, principal.getName());
		List<IStock> top5LosersStocks = iStockFetch.fetchTopLoserStocks(iStockDbGainersLosers, userDb, principal.getName());

		model.addObject(STOCKS, stocks);
		model.addObject(GAINERS, top5GainersStocks);
		model.addObject(LOSERS, top5LosersStocks);
		model.addObject("error", INSUFFICIENT_FUNDS_ERROR_MESSAGE);
		model.setViewName("stocks");
		return model;
	}
}
