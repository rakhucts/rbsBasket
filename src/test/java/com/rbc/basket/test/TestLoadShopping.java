package com.rbc.basket.test;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.rbc.basket.app.ShoppingBasketImpl;
import com.rbc.basket.config.ApplicationHolder;
import com.rbc.basket.error.BasketException;

public class TestLoadShopping {

	ApplicationHolder holder = ApplicationHolder.getInstance();
	ShoppingBasketImpl shopImp = new ShoppingBasketImpl();
	Map<String, Double> testShop = null;
	Map<String, Integer> testBasket = null;
	
	@Before
	public  void loadConfig() {
		try {
			testShop = holder.loadProperties();
			
		} catch (BasketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testApple() {
		Double appleActVal = testShop.get("APPLE");
		Double expectedActVal = 0.2;
		assertEquals(expectedActVal, appleActVal); 
	}
	
	@Test
	public void testOranges() {
		Double orangeActVal = testShop.get("ORANGE");
		Double expectedActVal = 0.3;
		assertEquals(expectedActVal, orangeActVal); 
	}
	
	@Test
	public void testPeach() {
		Double peachActVal = testShop.get("PEACH");
		Double expectedActVal = 0.4;
		assertEquals(expectedActVal, peachActVal); 
	}
	
	@Test
	public void testBanana() {
		Double bananaActVal = testShop.get("BANANAS");
		Double expectedActVal = 0.5;
		assertEquals(expectedActVal, bananaActVal); 
	}
	
	@Test
	public void testLemon() {
		Double lemonActVal = testShop.get("LEMONS");
		Double expectedActVal = 0.1;
		assertEquals(expectedActVal, lemonActVal); 
	}
	
		

}
