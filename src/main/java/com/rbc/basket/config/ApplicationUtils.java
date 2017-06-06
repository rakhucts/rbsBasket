package com.rbc.basket.config;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.rbc.basket.error.BasketException;

public class ApplicationUtils {
	
	public static Map<String, Integer> fetchCustomerItems(Map<String, Double> shopBasket) throws BasketException {
		Map<String, Integer> userInputs = new LinkedHashMap<String, Integer>();
		try {
			Scanner cusomerScanner = new Scanner(System.in);
			boolean isCustomerActive = true;
			while (isCustomerActive) {
				fruitSelection(cusomerScanner, userInputs, shopBasket);
				isCustomerActive = nextAction(cusomerScanner);
			}
			if (userInputs.size() == 0) {
				throw new BasketException("Error: No valid user input. Please re-execute");
			}
		} catch (BasketException bException) {
			throw bException;
		} catch (Exception exception) {
			throw new BasketException("A runtime error happened. Exception message-" + exception.getMessage());
		}
		return userInputs;
	}
	
	private static void fruitSelection (Scanner cusomerScanner, Map<String, Integer> userInputs, Map<String, Double> shopBasket) throws BasketException{
		System.out.println("Please type the name of the fruit you want to buy from the list below:-");
		try {
			displayFruits(shopBasket);
			String fruit = cusomerScanner.next();
			fruit = fruit.toUpperCase();
			if (!shopBasket.keySet().contains(fruit)) {
				System.err.println("Error: Name of the fruit does not matched in the list. Try again ");
				fruitSelection(cusomerScanner, userInputs, shopBasket);
			} else {
				Integer retCount = fruitCount(fruit);
				if (userInputs.keySet().contains(fruit)) {
					Integer existVal = userInputs.get(fruit);
					userInputs.remove(fruit);
					userInputs.put(fruit, (retCount + existVal));
				} else {
					userInputs.put(fruit, retCount);
				}
			} 
		} catch (Exception e) {
			// Log error
			throw new BasketException("Generic Error in calculation: Please contact customer support");
		}
	}
	
	private static Integer fruitCount (String fruit) {
		System.out.println("Please type the count of " + fruit + " needed");
		Scanner cusomerScanner = new Scanner(System.in);
		Integer retCount = null;
		try {
			retCount = cusomerScanner.nextInt();
			return retCount;
		} catch (InputMismatchException e) {
			System.err.println("Error: Integer value expected. Please try again");
			//in.
		}
		if (retCount == null) {
			retCount = fruitCount(fruit);
		}
		return retCount;
	}
		
	public static Double calculate(Map<String, Integer> userInputs, Map<String, Double> itemMap) {
		Iterator<String> usrInpSetItrtor = userInputs.keySet().iterator();
		Double total = new Double(0);
		while (usrInpSetItrtor.hasNext()) {
			String fruit = usrInpSetItrtor.next();
			System.out.println(fruit + " - " + userInputs.get(fruit) + " * " + itemMap.get(fruit)  + " = £" + itemMap.get(fruit) * userInputs.get(fruit));
			total = total + (itemMap.get(fruit) * userInputs.get(fruit));
		}
		return total;
		
	}
	
	private static void displayFruits(Map<String, Double> shopBasket) {
		Set<String> fruits = shopBasket.keySet();
		for (String fruit : fruits) {
			System.out.print(fruit);
			System.out.print(" | ");
		}
		System.out.println();
	}
	
	private static boolean nextAction (Scanner in) {
		System.out.println("Press 'Y' to continue shopping else any key to CHECKOUT");
		String action = in.next();
		if (action.equalsIgnoreCase("Y")) {
			return true;
		} else {
			return false;
		}
	}	

}
