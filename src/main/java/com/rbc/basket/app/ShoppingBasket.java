package com.rbc.basket.app;
import java.util.Map;

import com.rbc.basket.config.ApplicationHolder;
import com.rbc.basket.error.BasketException;

/**
 * Shopping Basket Interface : Main operations
 * @author AugustusR
 * @date 6-Jun-2017
 *
 */
public interface ShoppingBasket {
	
			
	// Loading the Fruit Shop items and price. 
	Map<String, Double> loadShop() throws BasketException;
	
	// Customer Interface handling to take items to the shopkeeper
	Map<String, Integer> customerBasket(Map<String, Double> shopBasket) throws BasketException;
	
	// Calculate the total amount from the customers Basket input and shopping basket
	Double calculateTotal(Map<String, Integer> userInputs, Map<String, Double> itemMap) throws BasketException;

}
