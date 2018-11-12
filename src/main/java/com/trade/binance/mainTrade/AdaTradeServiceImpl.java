package com.trade.binance.mainTrade;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trade.binance.bean.AccInfoBean;
import com.trade.binance.serviceI.BinancePublicServiceI;
import com.trade.binance.serviceI.BinanceTradeServiceI;
import com.trade.exception.BusinessException;
import com.trade.utils.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service("adaTradeService")
@Slf4j
public class AdaTradeServiceImpl {
	private static final String SYMBOL = "ADAUSDT";
	private static final BigDecimal ALLUSDT = new BigDecimal("100");
	private BigDecimal lastPrice = null;
	private BigDecimal currPrice = null;
	private static BigDecimal holdPercent = null;
	private static BigDecimal earnUsdt = null;
	AccInfoBean accInfo = null;
	@Autowired BinancePublicServiceI publicService;
	@Autowired BinanceTradeServiceI tradeService;
	public void run() {
		
	}	
	public void execute() {
		log.info("trade start:"+CommonUtils.getTime());
		//查询当前币值对市场价格
		try {
			currPrice = publicService.getPrice(SYMBOL);			
		} catch (BusinessException e) {
			log.error("查询"+SYMBOL+"价格异常",e);
			return;
		}
		//获取当前持仓价格
		
		//查询当前持仓情况
		try {
			accInfo = tradeService.getAccountInfo();
			holdPercent = caclAdaPercent(accInfo,currPrice);
			log.info(String.format("当前持仓百分比为:%f%", holdPercent.multiply(new BigDecimal("100"))));
			earnUsdt= caclIsWinner(accInfo);
			log.info(String.format("当前赚取USDT金额为:%f", earnUsdt));
			
		} catch (BusinessException e) {
			log.error("查询当前持仓价格异常",e);
			return;
		}
		//比较当前持仓价格和市场价格差额
		if(holdPercent.equals(BigDecimal.ZERO)) {
			log.info("当前持仓ada为0，将执行10%usdt量买入");
			
		}
	}
	/**
	 * 计算当前ada持仓比
	 * @param accInfo
	 * @return
	 */
	private BigDecimal caclAdaPercent(AccInfoBean accInfo,BigDecimal currPrice) {
		BigDecimal holdAda = accInfo.getAdaFree().add(accInfo.getAdaLock()).multiply(currPrice);
		BigDecimal holdUsdt = accInfo.getUsdtFree().add(accInfo.getUsdtLock());
		holdAda.divide(holdAda.add(holdUsdt), BigDecimal.ROUND_UP);
		return holdAda.divide(holdAda.add(holdUsdt), BigDecimal.ROUND_UP);
		
	}
	/**
	 * 计算当前盈亏
	 * @param accInfo
	 * @return
	 */
	private BigDecimal caclIsWinner(AccInfoBean accInfo) {
		BigDecimal holdAda = accInfo.getAdaFree().add(accInfo.getAdaLock()).multiply(currPrice);
		BigDecimal holdUsdt = accInfo.getUsdtFree().add(accInfo.getUsdtLock());
		holdAda.add(holdUsdt).subtract(ALLUSDT);
		return holdAda.add(holdUsdt).subtract(ALLUSDT);
		
	}
	private boolean exeOrder(String Flag,BigDecimal percent) {
		
		return false;
		
	}
}
