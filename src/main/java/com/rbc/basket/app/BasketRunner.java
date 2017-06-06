package com.rbc.basket.app;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.rbc.basket.config.ApplicationHolder;
import com.rbc.basket.error.BasketException;

public class BasketRunner {

	static ApplicationHolder holder = ApplicationHolder.getInstance();
	
	public static void main(String[] args) {
		BasketRunner basketRunner = new BasketRunner();
		basketRunner.runProgram();
	}
	
	public void runProgram() {
		System.out.println("Fruit Basket Shopping Started");
		ShoppingBasketImpl shoppingCart = new ShoppingBasketImpl(); 
		try {
			Map<String, Double> shopBasket = shoppingCart.loadShop();
			System.out.println("Configuration file successfully loaded.");
			System.out.println("Summary of items in the shop:: " + shopBasket);
			System.out.println("********************************************");
			System.out.println();
			
			Map<String, Integer> customerBasket = shoppingCart.customerBasket(shopBasket);
			System.out.println("Shopping Summary: " + customerBasket);
			System.out.println("************************************");
			System.out.println("Total cost of the fruit shopping is - £" + shoppingCart.calculateTotal(customerBasket, shopBasket));
		} catch (BasketException bException) {
			System.err.println(bException.getMessage());
		}  catch (Exception exception) {
			// Log error
			System.err.println("Generic Error : Please contact shop keepers technical team");
		}
	
		System.out.println("Fruit Basket Shopping Ended. Thank you !!");

	}
	
	
	
	

}
