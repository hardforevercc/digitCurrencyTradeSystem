package com.trade.binance.serviceI;

import java.math.BigDecimal;

import com.trade.exception.BusinessException;

public interface BinancePublicServiceI {
	/**
	 * 获取币种价格
	 * @param symbol
	 * @return
	 * @throws BusinessException 
	 */
	public BigDecimal getPrice(String symbol) throws BusinessException;
	
	public String getTimestamp()  throws BusinessException;
}
