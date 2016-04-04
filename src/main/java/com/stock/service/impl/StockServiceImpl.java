
package com.stock.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.log4j.Logger;


import com.stock.db.StockDBManager;
import com.stock.db.Impl.StockDBManagerImpl;
import com.stock.model.Stock;
import com.stock.model.Trade;
import com.stock.service.StockService;

/**
 * @author WANIYO
 *
 */
/**
 * @author WANIYO
 *
 */
/**
 * @author WANIYO
 *
 */
public class StockServiceImpl implements StockService {

	private Logger logger = Logger.getLogger(StockServiceImpl.class);
	
	private StockDBManager stocksDBManager = new StockDBManagerImpl();


	/**
	 * 
	 * @param stocksEntityManager
	 */
//	public void setStocksEntityManager(StockDBManager stocksDBManager) {
//		this.stocksDBManager = stocksDBManager;
//	}
	
	
	/* (non-Javadoc)
	 * @see com.stock.service.StockService#calculateDividendYield(java.lang.String)
	 */
	public double calculateDividendYield(String stockSymbol) throws Exception {
		
		double dividendYield = -1.0;
		
		Stock stock = stocksDBManager.getStockBySymbol(stockSymbol);
		
		if(stock==null){
			throw new Exception("The stock symbol ["+stockSymbol+"] is not found. Please enter the correct stock symbol");
		}

		// Price with value zero does not make any sense and could produce a zero division
//		if(stock.getPrice() <= 0.0){
//			throw new Exception("The price for the stock ["+stockSymbol+"] should be greater than zero (0).");
//		}
//		
		dividendYield = stock.getDividendYield();

		logger.debug("Dividend Yield calculated: "+dividendYield);
		return dividendYield;
	}

	
	/* (non-Javadoc)
	 * @see com.stock.service.StockService#calculatePERatio(java.lang.String)
	 */
	public double calculatePERatio(String stockSymbol) throws Exception {
		double peRatio = -1.0;
		try{
			logger.debug("Calculating P/E Ratio for the stock symbol: "+stockSymbol);
			Stock stock = stocksDBManager.getStockBySymbol(stockSymbol);

			// If the stock is not supported the a exception is raised
			if(stock==null){
				throw new Exception("The stock symbol ["+stockSymbol+"] is not supported by the Super Simple Stock system.");
			}

			// Ticker price with value zero does not make any sense and could produce a zero division
			if(stock.getPrice() <= 0.0){
				throw new Exception("The ticker price for the stock ["+stockSymbol+"] should be greater than zero (0).");
			}

			peRatio = stock.getPeRatio();

			logger.debug(" P/E Ratiocalculated: "+peRatio);

		}catch(Exception exception){
			logger.error("Error calculating P/E Ratio for the stock symbol: "+stockSymbol+".", exception);
			throw new Exception("Error calculating P/E Ratio for the stock symbol: "+stockSymbol+".", exception);
		}
		return peRatio;
	}



	/* (non-Javadoc)
	 * @see com.stock.service.StockService#calculateGBCEAllShareIndex()
	 */
	public double calculateGBCEAllShareIndex() throws Exception {
		
		HashMap<String, Stock> stocks = stocksDBManager.getStocks();
		double [] allStockPricesArray = null; 
		ArrayList<Double> stockPrices = new ArrayList<Double>();
		for(String stockSymbol: stocks.keySet() ){
			double stockPrice  = stocks.get(stockSymbol).getPrice();	
			if(stockPrice > 0){
				stockPrices.add(stockPrice);
			}
		}		
		
		if(stockPrices.size()>=1){
			
			for(int i=0; i<=(stockPrices.size()-1); i++){
				allStockPricesArray [i] = stockPrices.get(i).doubleValue();
				
			}
		}
		// Calculates the GBCE All Share Index
		return StatUtils.geometricMean(allStockPricesArray);
	}

	

	/* (non-Javadoc)
	 * @see com.stock.service.StockService#calculateStockPrice(java.lang.String)
	 */
	public double calculateStockPrice(String stockSymbol) throws Exception{
		double stockPrice = 0.0;

		try{
			logger.debug("Calculating Stock Price for the stock symbol: "+stockSymbol);
			Stock stock = stocksDBManager.getStockBySymbol(stockSymbol);

			// If the stock is not supported the a exception is raised
			if(stock==null){
				throw new Exception("The stock symbol ["+stockSymbol+"] is not supported by the Super Simple Stock system.");
			}

			stockPrice = calculateStockPriceinRange(stockSymbol, 15);

			logger.debug(" Stock Price calculated: "+stockPrice);


		}catch(Exception exception){
			logger.error("Error calculating P/E Ratio for the stock symbol: "+stockSymbol+".", exception);
			throw new Exception("Error calculating P/E Ratio for the stock symbol: "+stockSymbol+".", exception);

		}


		return stockPrice;
	}
	
	
	/**
	 * @param stockSymbol
	 * @param minutesRange
	 * @return
	 * @throws Exception
	 */
	private double calculateStockPriceinRange(String stockSymbol, int minutesRange) throws Exception{
		double stockPrice = 0.0;

		
		ArrayList<Trade> trades = stocksDBManager.getTrades();

		// Calculate the summation
		double shareQuantityAcum = 0.0;
		double tradePriceAcum = 0.0;
		for(Trade trade : trades){
			// Calculate the summation of Trade Price x Quantity
			tradePriceAcum += (trade.getTradePrice() * trade.getSharesQuantity());
			// Acumulate Quantity
			shareQuantityAcum += trade.getSharesQuantity();
		}

		// calculate the stock price
		if(shareQuantityAcum > 0.0){
			stockPrice = tradePriceAcum / shareQuantityAcum;	
		}


		return stockPrice;
	}

	
	
	/* (non-Javadoc)
	 * @see com.stock.service.StockService#recordTrade(com.stock.model.Trade)
	 */
	public boolean recordTrade(Trade trade) throws Exception{
		boolean recordResult = false;
		try{
			logger.debug("Begin recordTrade with trade object: ");
			logger.debug(trade);

			// trade should be an object
			if(trade==null){
				throw new Exception("Trade object to record should be a valid object and it's null.");
			}

			// stock should be an object
			if(trade.getStock()==null){
				throw new Exception("A trade should be associated with a stock and the stock for the trade is null.");
			}

			// shares quantity should be greater than zero
			if(trade.getSharesQuantity()<=0){
				throw new Exception("Shares quantity in the trade to record should be greater than cero.");
			}

			// shares price should be greater than zero
			if(trade.getTradePrice()<=0.0){
				throw new Exception("Shares price in the trade to record should be greater than cero.");
			}

			recordResult = stocksDBManager.recordTrade(trade);

			// Update the ticker price for the stock
			if(recordResult){
				trade.getStock().setPrice(trade.getTradePrice());
			}


		}catch(Exception exception){
			logger.error("Error when trying to record a trade.", exception);
			throw new Exception("Error when trying to record a trade.", exception);
		}
		return recordResult;
	}
}
