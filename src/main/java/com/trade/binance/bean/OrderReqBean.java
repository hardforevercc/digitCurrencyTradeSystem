package com.trade.binance.bean;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class OrderReqBean {
	private String symbol;
	private String side;
	private String type;
	private BigDecimal quantity;
	private BigDecimal price;
	private String newClientOrderId;
	private String timestamp;
	private long recvWindow;
	private String timeInForce;
}
