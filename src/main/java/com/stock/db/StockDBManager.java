package com.stock.db;

import java.util.ArrayList;
import java.util.HashMap;

import com.stock.model.Stock;
import com.stock.model.Trade;

public interface StockDBManager {
		
	public boolean recordTrade(Trade trade) throws Exception;
	
	public ArrayList<Trade> getTrades();
	
	public Stock getStockBySymbol(String stockSymbol);
	
	public HashMap<String, Stock> getStocks();

}
