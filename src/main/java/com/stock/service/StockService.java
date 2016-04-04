
package com.stock.service;

import com.stock.model.Trade;


public interface StockService {
	
	public boolean recordTrade(Trade trade) throws Exception;
	public double calculatePERatio(String stockSymbol) throws Exception;
	public double calculateStockPrice(String stockSymbol) throws Exception;
	public double calculateDividendYield(String stockSymbol) throws Exception;
	public double calculateGBCEAllShareIndex() throws Exception;
}
