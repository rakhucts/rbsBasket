package com.rbc.basket.app;

import java.util.Map;

import com.rbc.basket.config.ApplicationHolder;
import com.rbc.basket.config.ApplicationUtils;
import com.rbc.basket.error.BasketException;

public class ShoppingBasketImpl implements ShoppingBasket {
	
	private ApplicationHolder holder = ApplicationHolder.getInstance();

	@Override
	public Map<String, Double> loadShop() throws BasketException {
		return holder.loadProperties();
	}

	@Override
	public Map<String, Integer> customerBasket(Map<String, Double> shopBasket) throws BasketException {
		return ApplicationUtils.fetchCustomerItems(shopBasket);
	}

	@Override
	public Double calculateTotal(Map<String, Integer> userInputs,
			Map<String, Double> itemMap) throws BasketException {
		return ApplicationUtils.calculate(userInputs, itemMap);
	}

}
