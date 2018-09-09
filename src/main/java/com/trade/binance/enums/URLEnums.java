package com.trade.binance.enums;

public enum URLEnums {
	PRICEURL("https://api.binance.com/api/v3/ticker/price","最近交易价格"),
	ACCOUNTURL("https://api.binance.com/api/v3/account","账户信息"),
	ORDERURL("https://api.binance.com/api/v3/order","下单请求");
	private String url;
	private String desc;
	
	private URLEnums(String url, String desc) {
		this.url = url;
		this.desc = desc;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
