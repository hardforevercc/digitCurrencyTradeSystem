package com.trade.binance.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.trade.binance.bean.OrderReqBean;
import com.trade.binance.enums.URLEnums;
import com.trade.binance.serviceI.BinanceTradeServiceI;
import com.trade.exception.BusinessException;
import com.trade.utils.HttpProxyClientUtils;
import com.trade.utils.SignatureUtils;

import lombok.extern.slf4j.Slf4j;
@Service("binanceTradeService")
@Slf4j
public class BinanceTradeServiceImpl implements BinanceTradeServiceI {
	private static final String SECRET = "dhBqV9dgobHNUcMfrceVbKiFw7el7oTcb5phx2cpXBjMYXPZrnPCpYJ0VCn2QmkI";
	private static boolean isProxy = true;
	@Override
	public String postNewOrder(OrderReqBean reqstBean) throws BusinessException {
		Map<String, Object> reqMap = JSONObject.parseObject(JSONObject.toJSONString(reqstBean));
		String secretMsg = SignatureUtils.enHmacSHA256(reqMap, SECRET);
		String respMsg = null;
		try {
			respMsg = HttpProxyClientUtils.sendPost(URLEnums.ORDERURL.getUrl(), secretMsg, isProxy);
		}catch(Exception e) {
			throw new BusinessException(URLEnums.ORDERURL.getDesc()+"异常",e);
		}
		JSONObject respObj = JSONObject.parseObject(respMsg);
		return respMsg;
	}
	@Override
	public String getAccountInfo() throws BusinessException {
		Map<String,Object> reqMap = new HashMap<String,Object>();
		reqMap.put("recvWindow", 5000);
		reqMap.put("timestamp", System.currentTimeMillis());
		String secretMsg = SignatureUtils.enHmacSHA256(reqMap, SECRET);
		String respMsg = null;
		Map<String,Object> map = null;
		try {
			log.info(URLEnums.ACCOUNTURL.getDesc()+"请求信息:"+JSONObject.toJSONString(reqMap));
			respMsg = HttpProxyClientUtils.sendGet(URLEnums.ACCOUNTURL.getUrl(), secretMsg, isProxy);
			log.info(URLEnums.ACCOUNTURL.getDesc()+"响应信息:"+respMsg);
			JSONArray respArray = JSONObject.parseObject(respMsg).getJSONArray("balances");			
			JSONObject currency = null;
			map = new HashMap<String,Object>();
			for(int i = 0;i < respArray.size();i++) {
				currency = respArray.getJSONObject(i);
				if("USDT".equals(currency.getString("asset"))) {
					map.put("usdtFree", currency.getBigDecimal("free"));
					map.put("usdtLock",currency.getBigDecimal("locked"));
				}
				if("ADA".equals(currency.getString("asset"))) {
					map.put("adaFree", currency.getBigDecimal("free"));
					map.put("adatLock",currency.getBigDecimal("locked"));
				}
			}
		}catch(Exception e) {
			throw new BusinessException(URLEnums.ACCOUNTURL.getDesc()+"异常",e);
		}
		log.info("账户余额:"+JSONObject.toJSONString(map));
		return JSONObject.toJSONString(map);
	}

}
