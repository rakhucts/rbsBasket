package com.rbc.basket.test;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.rbc.basket.app.ShoppingBasketImpl;
import com.rbc.basket.config.ApplicationHolder;
import com.rbc.basket.error.BasketException;

public class TestBasketCalculator {

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
	public void testAll() {
		//fail("Not yet implemented");
		testBasket = new TreeMap<String, Integer>();
		testBasket.put("APPLE", 2);
		testBasket.put("PEACH", 3);
		testBasket.put("LEMONS", 4);
		testBasket.put("BANANAS", 7);
		testBasket.put("ORANGE", 4);
		try {
			Double actVal = shopImp.calculateTotal(testBasket, testShop);
			Double expVal = 6.7;
			assertEquals(expVal, actVal); 
		} catch (BasketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}
		@Test
		public void testOneApple() {
			//fail("Not yet implemented");
			testBasket = new TreeMap<String, Integer>();
			testBasket.put("APPLE", 2);
			try {
				Double actVal = shopImp.calculateTotal(testBasket, testShop);
				Double expVal = .4;
				assertEquals(expVal, actVal); 
			} catch (BasketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void testOnePeach() {
			//fail("Not yet implemented");
			testBasket = new TreeMap<String, Integer>();
			testBasket.put("PEACH", 3);
			try {
				Double actVal = shopImp.calculateTotal(testBasket, testShop);
				System.out.println(")))))))))" + actVal);
				Double expVal = new Double(3 * .4);
				assertEquals(expVal, actVal); 
			} catch (BasketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void testTwoFruits() {
			testBasket = new TreeMap<String, Integer>();
			testBasket.put("PEACH", 11);
			testBasket.put("ORANGE", 21);
			try {
				Double actVal = shopImp.calculateTotal(testBasket, testShop);
				Double expVal = 10.7;
				assertEquals(expVal, actVal); 
			} catch (BasketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void testThreeFruits() {
			testBasket = new TreeMap<String, Integer>();
			testBasket.put("BANANAS", 444);
			testBasket.put("LEMONS", 2221);
			testBasket.put("PEACH", 9221);
			try {
				Double actVal = shopImp.calculateTotal(testBasket, testShop);
				Double expVal = 4132.5;
				assertEquals(expVal, actVal); 
			} catch (BasketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
