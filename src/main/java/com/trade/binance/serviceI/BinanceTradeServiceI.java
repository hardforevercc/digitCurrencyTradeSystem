package com.trade.binance.serviceI;

import java.math.BigDecimal;

import com.trade.binance.bean.OrderReqBean;
import com.trade.exception.BusinessException;

public interface BinanceTradeServiceI {
	/**
	 * 创建新的订单
	 * @param reqstBean
	 * @return
	 * @throws BusinessException 
	 */
	public String postNewOrder(OrderReqBean reqstBean) throws BusinessException;
	
	public String getAccountInfo() throws BusinessException;
}
