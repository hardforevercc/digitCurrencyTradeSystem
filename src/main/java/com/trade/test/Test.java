//package com.trade.test;
//
//import java.io.UnsupportedEncodingException;
//import java.math.BigDecimal;
//import java.net.SocketTimeoutException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.conn.ConnectTimeoutException;
//
//import com.alibaba.fastjson.JSONObject;
//import com.binance.trade.utils.HttpClientUtil;
//import com.binance.trade.utils.HttpProxyClientUtils;
//import com.binance.trade.utils.SignatureUtils;
//
//public class Test {
//
//	public static void main(String[] args) {
//		String timeObj = null;
//		try {
//			timeObj = HttpProxyClientUtils.sendGet("https://api.binance.com/api/v1/time", "", true);
//			//HttpClientUtil.get("https://api.binance.com/api/v1/ping", "utf-8", HttpClientUtil.CONNTIMEOUT, HttpClientUtil.READTIMEOUT);
//			//timeObj = HttpClientUtil.get("https://api.binance.com/api/v1/time", "utf-8", HttpClientUtil.CONNTIMEOUT, HttpClientUtil.READTIMEOUT);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		if(StringUtils.isNotBlank(timeObj)) {
//			timeObj = JSONObject.parseObject(timeObj).getString("serverTime");
//		}
//		System.out.println(timeObj);
//		String accountUrl = "https://api.binance.com/api/v3/account";
//		
//		
//		Map<String,String> map = new HashMap<String,String>();
//		String secretKey = "dhBqV9dgobHNUcMfrceVbKiFw7el7oTcb5phx2cpXBjMYXPZrnPCpYJ0VCn2QmkI";
//		//map.put("api_key", "sSFs18IGntXcK0gkEQ7aQrsjGGOCBk379F3m02VNTaHxYcoFj4QOEGFBZDJSqcGA");
//		String timestap = System.currentTimeMillis()+"";
//		map.put("recvWindow", "5000");
//		map.put("timestamp", timestap);///api/v1/time
//		String msg = SignatureUtils.enHmacSHA256(map, secretKey);
//		accountUrl += "?recvWindow=5000&timestamp="+timestap+"&signature="+msg;
//		System.out.println(msg);
//		String acount = null;
//		try {
//			acount = HttpProxyClientUtils.sendGet(accountUrl, "", true);//.get(accountUrl, "utf-8", HttpClientUtil.CONNTIMEOUT, HttpClientUtil.READTIMEOUT);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		System.out.println(acount);
//		String priceUrl = "https://api.binance.com/api/v3/ticker/price";
//		priceUrl += "?symbol=ADAUSDT";
//		String priceMsg = HttpProxyClientUtils.sendGet(priceUrl, null, true);
//		System.out.println(priceMsg);
//	}
//	public static String encrypt() {
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("api_key", "sSFs18IGntXcK0gkEQ7aQrsjGGOCBk379F3m02VNTaHxYcoFj4QOEGFBZDJSqcGA");
////		map.put("symbol","btc_usdt");
////		map.put("type", "buy");
////		map.put("price", "680");
////		map.put("amount", "1.0");
//		String sortStr = SignatureUtils.signBody(map);
//		System.out.println(sortStr);
//		String secret = "dhBqV9dgobHNUcMfrceVbKiFw7el7oTcb5phx2cpXBjMYXPZrnPCpYJ0VCn2QmkI";
//		String finalStr = sortStr +secret;
//		System.out.println(finalStr);
//		System.out.println(getMD5Str(finalStr));
//		return getMD5Str(finalStr);
//	}
//	
//	private static String getMD5Str(String str)  
//    {  
//        MessageDigest messageDigest = null;  
//        try  
//        {  
//            messageDigest = MessageDigest.getInstance("MD5");  
//            messageDigest.reset();  
//            messageDigest.update(str.getBytes("UTF-8"));  
//        } catch (NoSuchAlgorithmException e)  
//        {  
//            System.out.println("NoSuchAlgorithmException caught!");  
//            System.exit(-1);  
//        } catch (UnsupportedEncodingException e)  
//        {  
//            e.printStackTrace();  
//        }  
//  
//        byte[] byteArray = messageDigest.digest();  
//  
//        StringBuffer md5StrBuff = new StringBuffer();  
//  
//        for (int i = 0; i < byteArray.length; i++)  
//        {  
//            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
//                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
//            else  
//                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
//        }  
//        return md5StrBuff.toString().toUpperCase();  
//    }  
//	
//
//}
