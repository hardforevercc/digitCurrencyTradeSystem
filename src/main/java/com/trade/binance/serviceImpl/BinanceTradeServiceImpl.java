package com.trade.binance.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.trade.binance.bean.AccInfoBean;
import com.trade.binance.bean.OrderReqBean;
import com.trade.binance.enums.URLEnums;
import com.trade.binance.serviceI.BinancePublicServiceI;
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
	private static AccInfoBean accInfo = null;
	@Autowired
	BinancePublicServiceI publicService;
	@Override
	public String postNewOrder(OrderReqBean reqstBean) throws BusinessException {
		
		reqstBean.setTimestamp(publicService.getTimestamp());		
		reqstBean.setRecvWindow(5000L);
		log.info(JSONObject.toJSONString(reqstBean));
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
	public AccInfoBean getAccountInfo() throws BusinessException {
		Map<String,Object> reqMap = new HashMap<String,Object>();
		reqMap.put("recvWindow", 5000);
		reqMap.put("timestamp", publicService.getTimestamp());
		String secretMsg = SignatureUtils.enHmacSHA256(reqMap, SECRET);
		String respMsg = null;
		accInfo = new AccInfoBean();
		try {
			log.info(URLEnums.ACCOUNTURL.getDesc()+"请求信息:"+JSONObject.toJSONString(reqMap));
			respMsg = HttpProxyClientUtils.sendGet(URLEnums.ACCOUNTURL.getUrl(), secretMsg, isProxy);
			log.info(URLEnums.ACCOUNTURL.getDesc()+"响应信息:"+respMsg);
			JSONArray respArray = JSONObject.parseObject(respMsg).getJSONArray("balances");			
			JSONObject currency = null;
			
			for(int i = 0;i < respArray.size();i++) {
				currency = respArray.getJSONObject(i);
				if("USDT".equals(currency.getString("asset"))) {
					accInfo.setUsdtFree(currency.getBigDecimal("free"));
					accInfo.setUsdtLock(currency.getBigDecimal("locked"));
				}
				if("ADA".equals(currency.getString("asset"))) {
					accInfo.setAdaFree(currency.getBigDecimal("free"));
					accInfo.setAdaLock(currency.getBigDecimal("locked"));
				}
			}
		}catch(Exception e) {
			throw new BusinessException(URLEnums.ACCOUNTURL.getDesc()+"异常",e);
		}
		log.info("账户余额:"+JSONObject.toJSONString(accInfo));
		return accInfo;
	}

}
