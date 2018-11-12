package com.trade.binance.bean;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccInfoBean {
	private BigDecimal adaFree;
	private BigDecimal usdtFree;
	private BigDecimal adaLock;
	private BigDecimal usdtLock;
}
