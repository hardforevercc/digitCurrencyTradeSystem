package com.trade.binance.serviceImpl;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.trade.binance.enums.URLEnums;
import com.trade.binance.serviceI.BinancePublicServiceI;
import com.trade.exception.BusinessException;
import com.trade.utils.HttpProxyClientUtils;

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
			String priceMsg = HttpProxyClientUtils.sendGet(priceUrl, null, isProxy);
			price = new BigDecimal(JSONObject.parseObject(priceMsg).getString("price"));
		}catch(Exception e) {
			throw new BusinessException("查询价格异常");
		}
			
		return price;
	}

}
