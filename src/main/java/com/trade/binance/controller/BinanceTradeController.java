package com.trade.binance.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trade.binance.serviceI.BinanceTradeServiceI;
import com.trade.exception.BusinessException;
@RestController
@RequestMapping(value = "/trade")
public class BinanceTradeController {
	@Autowired
	BinanceTradeServiceI binanceTradeService;
	@RequestMapping(value = "/getAcInfo")
	public String getAcInfo(HttpServletRequest request) throws BusinessException {
		return binanceTradeService.getAccountInfo();
		
	}
}
