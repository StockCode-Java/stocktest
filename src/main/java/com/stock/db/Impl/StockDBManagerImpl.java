
package com.stock.db.Impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.stock.db.StockDBManager;
import com.stock.db.data.StockData;
import com.stock.model.Stock;
import com.stock.model.Trade;

public class StockDBManagerImpl implements StockDBManager{
	
	private Logger logger = Logger.getLogger(StockDBManagerImpl.class);
	
	private ArrayList<Trade> trades = new ArrayList<Trade>();
	
	private HashMap<String, Stock> stocks = new StockData().getStockTestData();
	
	

	/**
	 * @return the trades
	 */
	public ArrayList<Trade> getTrades() {
		return trades;
	}

	/**
	 * @param trades the trades to set
	 */
	public void setTrades(ArrayList<Trade> trades) {
		this.trades = trades;
	}

	/**
	 * @return the stocks
	 */
	public HashMap<String, Stock> getStocks() {
		return stocks;
	}

	/**
	 * @param stocks the stocks to set
	 */
	public void setStocks(HashMap<String, Stock> stocks) {
		this.stocks = stocks;
	}
	
	/**
	 * @return the stocks object for the given stocksymbol
	 */
	public Stock getStockBySymbol(String stockSymbol){
		Stock stock = null;
		try{
			if(stockSymbol!=null){
				stock = stocks.get(stockSymbol);
			}
		}catch(Exception exception){
			logger.error("Error occurred while getting the stock object for the stock symbol: "+stockSymbol+".", exception);
		}
		return stock;
	}

	public boolean recordTrade(Trade trade) throws Exception{
		boolean result = false;
		try{
			result = trades.add(trade);
		}catch(Exception exception){
			throw new Exception("Error occurred while recording a trade", exception);
		}
		return result;
	}
}
