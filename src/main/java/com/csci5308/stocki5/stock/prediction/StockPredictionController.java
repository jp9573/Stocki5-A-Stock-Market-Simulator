package com.csci5308.stocki5.stock.prediction;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StockPredictionController
{
	private static final String PREDICTION_LIST = "predictionList";
	private static final String STOCK_NAME = "stockName";

	StockAbstractFactory stockFactory = StockAbstractFactory.instance();
	IStockHistoryDb iStockHistoryDb = stockFactory.createStockHistoryDb();
	IStockPrediction iStockPrediction = stockFactory.createStockPrediction();

	@RequestMapping(value = { "/predict" }, method = RequestMethod.POST)
	public ModelAndView stocksPage(HttpServletRequest request, @RequestParam(value = STOCK_NAME, required = true) String stockName)
	{
		ModelAndView model = new ModelAndView();
		List<IStock> predictionList = iStockPrediction.predictStockValue(iStockHistoryDb, stockName);
		model.addObject(PREDICTION_LIST, predictionList);
		model.setViewName("prediction");
		return model;
	}
}
