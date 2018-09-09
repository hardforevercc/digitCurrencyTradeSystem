package com.trade.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessException extends Exception{
	
	public BusinessException(String str) {
		log.error(str);
	}
	
	public BusinessException(String str,Exception e) {
		log.error(str,e);
	}
}
