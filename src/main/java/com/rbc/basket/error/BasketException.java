package com.rbc.basket.error;

public class BasketException extends Exception {

	public BasketException(String message) {
        super(message);
    }
	
	public BasketException(String message, Exception exp) {
        super(message, exp);
    }
}
