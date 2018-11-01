package com.trade.binance.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.trade.binance.serviceI.BinancePublicServiceI;
import com.trade.binance.serviceI.BinanceTradeServiceI;
import com.trade.exception.BusinessException;
import com.trade.utils.HttpUtils;

import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping(value = "/trade")
@Slf4j
public class BinanceTradeController {
	@Autowired
	BinanceTradeServiceI binanceTradeService;
	@Autowired
	BinancePublicServiceI binancePublicService;
	@RequestMapping(value = "/getAcInfo")
	public String getAcInfo(HttpServletRequest request) throws BusinessException {
		return binanceTradeService.getAccountInfo();
		
	}
	@RequestMapping(value = "/getPrice")
	public BigDecimal getPrice(HttpServletRequest request) throws BusinessException {
		String reqMsg = null;
		try {
			reqMsg = HttpUtils.getMsg(request);
			reqMsg = JSONObject.parseObject(reqMsg).getString("symbol");
		} catch (IOException e) {
			log.error("接收币值对转换异常",e);
		}
		return binancePublicService.getPrice(reqMsg);
		
	}
}
