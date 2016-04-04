/**
 * 
 */
package com.stock.model;

import com.stock.constants.StockType;

public class Stock {

	String stockSymbol = null;
	StockType stockType = null;;
	double lastDividend = 0.0;
	double fixedDividend = 0.0;
	double parValue = 0.0;
	double price = 0.0;
	
	/**
	 * @return the stockSymbol
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}
	/**
	 * @param stockSymbol the stockSymbol to set
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	/**
	 * @return the stockType
	 */
	public StockType getStockType() {
		return stockType;
	}
	/**
	 * @param stockType the stockType to set
	 */
	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}
	/**
	 * @return the lastDividend
	 */
	public double getLastDividend() {
		return lastDividend;
	}
	/**
	 * @param lastDividend the lastDividend to set
	 */
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}
	/**
	 * @return the fixedDividend
	 */
	public double getFixedDividend() {
		return fixedDividend;
	}
	/**
	 * @param fixedDividend the fixedDividend to set
	 */
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	/**
	 * @return the parValue
	 */
	public double getParValue() {
		return parValue;
	}
	/**
	 * @param parValue the parValue to set
	 */
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
	
	
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return the dividendYield
	 */
	public double getDividendYield() {
		double dividendYield = -1.0;
		if(price > 0.0){
			if( stockType==StockType.COMMON){
				dividendYield = lastDividend / price;
			}else{
				dividendYield = (fixedDividend * parValue ) / price;
			}
		}
		return dividendYield;
	}

	/**
	 * @return the peRatio
	 */
	public double getPeRatio() {
		double peRatio = -1.0;
		
		if(price > 0.0){
			peRatio = price / getDividendYield();	
		}
		
		return peRatio;
	}
	
	public Stock(String symbol, StockType type, Double lastDividend, Double fixedDividend, Double parValue) {
		this.setStockSymbol(stockSymbol);
		this.setStockType(stockType);
		this.setLastDividend(lastDividend);
		this.setFixedDividend(fixedDividend);
		this.setParValue(parValue);
	}

	
	public String toString() {
		return "Stock [symbol=" + stockSymbol + ", type=" + stockType + ", lastDividend="
				+ lastDividend + ", fixedDividend=" + fixedDividend
				+ ", parValue=" + parValue + "]";
	}

}
