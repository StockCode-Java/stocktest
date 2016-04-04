package com.stock.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.stock.db.StockDBManager;
import com.stock.db.Impl.StockDBManagerImpl;
import com.stock.model.Stock;
import com.stock.model.Trade;
import com.stock.constants.StockType;
import com.stock.constants.TradeType;
import com.stock.service.impl.StockServiceImpl;
import com.stock.util.DateUtil;

/**

 */
public class StockTest {

	StockServiceImpl stockServiceImpl = new StockServiceImpl();
	private StockDBManager stocksDBManager = new StockDBManagerImpl();
	String[] stockSymbols = { "TEA", "POP", "ALE", "GIN", "JOE" };

	Logger logger = Logger.getLogger(StockTest.class);




	@Test
	public void calculateDividendYieldTest() {
		logger.info("Start  calculateDividendYieldTest ...");

		// String[] stockSymbols = { "POP", "ALE", "GIN", "JOE" };
		try {

			System.out.println("++++" + stocksDBManager.getTrades());
			for (String stockSymbol : stockSymbols) {
				double dividendYield;
				dividendYield = stockServiceImpl
						.calculateDividendYield(stockSymbol);
				logger.info(stockSymbol + " - DividendYield calculated: "
						+ dividendYield);
				System.out.println(stockSymbol
						+ " - DividendYield calculated: " + dividendYield);
				Assert.assertTrue(dividendYield >= 0.0);
			}
		} catch (Exception e) {
			logger.error(e);
			Assert.assertTrue(false);
		}

		logger.info("Finish calculateDividendYieldTest ...OK");
	}

	@Test
	public void calculatePERatioTest() {
		logger.info("Start  calculatePERatioTest ...");

		try {
			for (String stockSymbol : stockSymbols) {
				double peRatio = stockServiceImpl.calculatePERatio(stockSymbol);
				logger.info(stockSymbol + " - P/E Ratio calculated: " + peRatio);
				Assert.assertTrue(peRatio >= 0.0);
			}
		} catch (Exception e) {
			logger.error(e);
			Assert.assertTrue(false);
		}

		logger.info("Finish calculatePERatioTest ...OK");
	}

	/**
	 * 
	 */
	@Test
	public void calculateStockPriceTest() {

		try {

			for (String stockSymbol : stockSymbols) {
				double stockPrice = stockServiceImpl
						.calculateStockPrice(stockSymbol);
				logger.info(stockSymbol + " - Stock Price calculated: "
						+ stockPrice);
				Assert.assertTrue(stockPrice >= 0.0);
			}

		} catch (Exception e) {
			logger.error(e);
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void recordATradeTest() {

		try {
			// Insert many trades in the stock system

			Stock stock_1 = new Stock("XYZ", StockType.COMMON, 13.0, 0.0, 30.0);

			Trade trade_1 = new Trade();
			trade_1.setStock(stock_1);
			trade_1.setSharesQuantity(10);
			trade_1.setTradeDateTime(DateUtil.getCurrentDateTime());
			trade_1.setTradePrice(30);
			trade_1.setTradeType(TradeType.BUY);

			boolean result = stocksDBManager.recordTrade(trade_1);
			Assert.assertTrue(result);

		} catch(Exception e) {
			logger.error(e);
			Assert.assertTrue(false);
		}

		logger.info("Finish recordATradeTest ...OK");

	}

	/**
	 * 
	 */
	@Test
	public void calculateGBCEAllShareIndexTest() {

		try {
			double GBCEAllShareIndex = stockServiceImpl.calculateGBCEAllShareIndex();
			logger.info("GBCE All Share Index: "+GBCEAllShareIndex);
			Assert.assertTrue(GBCEAllShareIndex > 0.0);
		} catch (Exception e) {
			logger.error(e);
			Assert.assertTrue(false);
		}
	}
}