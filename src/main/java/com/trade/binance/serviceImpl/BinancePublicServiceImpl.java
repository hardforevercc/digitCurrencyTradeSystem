package com.trade.binance.serviceImpl;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.trade.binance.enums.URLEnums;
import com.trade.binance.serviceI.BinancePublicServiceI;
import com.trade.exception.BusinessException;
import com.trade.utils.HttpProxyClientUtils;

import lombok.extern.slf4j.Slf4j;
@Service("binancePublicService")
@Slf4j
public class BinancePublicServiceImpl implements BinancePublicServiceI {
	private static boolean isProxy = true;
	@Override
	public BigDecimal getPrice(String symbol) throws BusinessException {
		if(StringUtils.isBlank(symbol)) {
			throw new BusinessException("交易币值对为空");
		}
		String priceUrl = URLEnums.PRICEURL.getUrl()+"?symbol="+symbol;
		BigDecimal price = null;
		try {
			log.info("查询价格URL:"+priceUrl);
			String priceMsg = HttpProxyClientUtils.sendGet(priceUrl, null, isProxy);
			log.info("查询价格信息:"+priceMsg);
			price = new BigDecimal(JSONObject.parseObject(priceMsg).getString("price"));
		}catch(Exception e) {
			throw new BusinessException("查询价格异常",e);
		}
			
		return price;
	}
	@Override
	public String getTimestamp() throws BusinessException {
		String timestamp = null;
		try {
			String timeMsg = HttpProxyClientUtils.sendGet(URLEnums.TIMEURL.getUrl(), null, isProxy);
			timestamp = JSONObject.parseObject(timeMsg).get("serverTime").toString();
		}catch(Exception e) {
			throw new BusinessException("查询价格异常",e);
		}
		return timestamp;
	}

}
