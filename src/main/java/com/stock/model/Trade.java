
package com.stock.model;

import java.util.Date;

import com.stock.constants.TradeType;


public class Trade {
	
	private Date tradeDateTime = null;
	private Stock stock = null;
	private TradeType tradeType = TradeType.BUY;
	private int sharesQuantity = 0;
	private double tradePrice = 0.0;
	
	/**
	 * @return the tradeDateTime
	 */
	public Date getTradeDateTime() {
		return tradeDateTime;
	}
	/**
	 * @param tradeDateTime the tradeDateTime to set
	 */
	public void setTradeDateTime(Date tradeDateTime) {
		this.tradeDateTime = tradeDateTime;
	}
	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	/**
	 * @return the tradeType
	 */
	public TradeType getTradeType() {
		return tradeType;
	}
	/**
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * @return the sharesQuantity
	 */
	public int getSharesQuantity() {
		return sharesQuantity;
	}
	/**
	 * @param sharesQuantity the sharesQuantity to set
	 */
	public void setSharesQuantity(int sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}
	/**
	 * @return the tradePrice
	 */
	public double getTradePrice() {
		return tradePrice;
	}
	/**
	 * @param tradePrice the tradePrice to set
	 */
	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}


}
