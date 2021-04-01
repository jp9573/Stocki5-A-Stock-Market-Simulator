package com.csci5308.stocki5.trade.buy;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.StockDb;
import com.csci5308.stocki5.stock.db.StockDbGainersLosers;
import com.csci5308.stocki5.stock.fetch.IStockFetch;
import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.db.TradeDb;
import com.csci5308.stocki5.trade.order.ITradeOrder;
import com.csci5308.stocki5.user.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	ITradeOrder iTradeOrder;

	@Autowired
	IStockFetch iStockFetch;

	@Autowired
	ITradeBuy iTradeBuy;
	
	@Autowired
	StockDbGainersLosers stockDbHighestLowest;

	@Autowired
	StockDb stockDb;

	@Autowired
	UserDb userDb;

	@Autowired
	TradeDb tradeDb;

	@RequestMapping(value = "/buystock", method = RequestMethod.POST)
	public ModelAndView buyStock(HttpServletRequest request,
								 @RequestParam(value = BUY_STOCK_ID) int stockId,
								 @RequestParam(value = QUANTITY) int quantity)
	{
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		boolean isBought = iTradeBuy.buyStock(principal.getName(), stockId, quantity, stockDb, userDb, tradeDb);
		if (isBought)
		{
			List<Trade> orders = iTradeOrder.fetchUserOrders(principal.getName(), tradeDb);
			model.addObject(ORDERS, orders);
			model.setViewName("orders");
			return model;
		}

		List<Stock> stocks = iStockFetch.fetchUserStocks(stockDb, userDb, principal.getName());
		List<Stock> top5GainersStocks = iStockFetch.fetchTopGainerStocks(stockDbHighestLowest, userDb, principal.getName());
		List<Stock> top5LosersStocks = iStockFetch.fetchTopLoserStocks(stockDbHighestLowest, userDb, principal.getName());

		model.addObject(STOCKS, stocks);
		model.addObject(GAINERS, top5GainersStocks);
		model.addObject(LOSERS, top5LosersStocks);
		model.addObject("error", INSUFFICIENT_FUNDS_ERROR_MESSAGE);
		model.setViewName("stocks");
		return model;
	}

	@RequestMapping(value = "/setbuystock", method = RequestMethod.POST)
	public ModelAndView setBuyStock(HttpServletRequest request,
									@RequestParam(value = SET_BUY_STOCK_ID) int stockId,
									@RequestParam(value = SET_QUANTITY) int quantity,
									@RequestParam(value = SET_BUY_PRICE) float buyPrice)
	{
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView();

		boolean isBought = iTradeBuy.setBuyPrice(principal.getName(), stockId, quantity, buyPrice, stockDb, userDb,
				tradeDb);
		if (isBought)
		{
			List<Trade> orders = iTradeOrder.fetchUserOrders(principal.getName(), tradeDb);
			model.addObject(ORDERS, orders);
			model.setViewName("orders");
			return model;
		}

		List<Stock> stocks = iStockFetch.fetchUserStocks(stockDb, userDb, principal.getName());
		List<Stock> top5GainersStocks = iStockFetch.fetchTopGainerStocks(stockDbHighestLowest, userDb, principal.getName());
		List<Stock> top5LosersStocks = iStockFetch.fetchTopLoserStocks(stockDbHighestLowest, userDb, principal.getName());

		model.addObject(STOCKS, stocks);
		model.addObject(GAINERS, top5GainersStocks);
		model.addObject(LOSERS, top5LosersStocks);
		model.addObject("error", INSUFFICIENT_FUNDS_ERROR_MESSAGE);
		model.setViewName("stocks");
		return model;
	}
}
