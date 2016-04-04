package com.stock.db.data;

import java.util.HashMap;

import com.stock.model.Stock;
import com.stock.constants.StockType;


public class StockData {
	
	public HashMap getStockTestData() {
		
		 HashMap<String, Stock> stockTestData = new HashMap<String, Stock>();
		 
		 stockTestData.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
	     stockTestData.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
	     stockTestData.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
	     stockTestData.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
	     stockTestData.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
	     return stockTestData;
	}

}
