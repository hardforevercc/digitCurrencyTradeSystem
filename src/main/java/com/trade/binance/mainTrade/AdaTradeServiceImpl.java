package com.trade.binance.mainTrade;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trade.binance.serviceI.BinancePublicServiceI;
import com.trade.binance.serviceI.BinanceTradeServiceI;
import com.trade.exception.BusinessException;
import com.trade.utils.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service("adaTradeService")
@Slf4j
public class AdaTradeServiceImpl {
	private BigDecimal lastPrice = null;
	private BigDecimal currPrice = null;
	private static final String SYMBOL = "ADAUSDT";
	@Autowired BinancePublicServiceI publicService;
	@Autowired BinanceTradeServiceI tradeService;
	public void run() {
		
	}	
	public void execute() {
		log.info("trade start:"+CommonUtils.getTime());
		//查询当前币值对市场价格
		try {
			currPrice = publicService.getPrice(SYMBOL);			
		} catch (BusinessException e) {
			log.error("查询"+SYMBOL+"价格异常",e);
		}
		//查询当前持仓价格
		
		//比较当前持仓价格和市场价格差额
		
		//
	}
}
