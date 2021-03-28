package com.csci5308.stocki5.stock.prediction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.history.StockHistoryDb;

@Controller
public class StockPredictionController
{

	@Autowired
	StockHistoryDb stockHistoryDb;

	@Autowired
	IStockPrediction iStockPrediction;

	@RequestMapping(value = { "/predict" }, method = RequestMethod.POST)
	public ModelAndView stocksPage(HttpServletRequest request,
			@RequestParam(value = "stockName", required = true) String stockName)
	{
		ModelAndView model = new ModelAndView();
		List<Stock> predictionList = iStockPrediction.predictStockValue(stockHistoryDb, stockName);
		model.addObject("predictionList", predictionList);
		model.setViewName("prediction");
		return model;
	}
}
