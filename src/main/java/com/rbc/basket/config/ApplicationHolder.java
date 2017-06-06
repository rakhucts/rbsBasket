package com.rbc.basket.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import com.rbc.basket.error.BasketException;

/**
 * This Class loads the shop items into the application for 
 * @author AugustusR
 *
 */
public final class ApplicationHolder
{
	/**
	* The constructor could be made private
	* to prevent others from instantiating this class.
	* But this would also make it impossible to
	* create instances of Singleton subclasses.
	*/
	private ApplicationHolder() { }

	private static Properties properties = null;
	
	/**
	 * Method to loads the Properties in this ApplicationHolder.
	 * @param properties
	 */
	public Map<String, Double> loadProperties() throws BasketException {
		properties = new Properties();
		Map<String, Double> itemMap =  new TreeMap<String, Double>();
		try {
			String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "config.properties";
			properties.load(new FileReader(new File(path)));
			loadItems(itemMap);
			if (itemMap.size() == 0) {
				throw new BasketException("Error: No items loaded to the applicaiton from configuration.");
			}
		} catch (BasketException basketException) {
			throw basketException;
		} catch (IOException ioException) {
			throw new BasketException("Exception while loading property file. Exception message - " + ioException.getMessage());
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new BasketException("Generic Exception while loading property file. Exception message - " + exp.getMessage());
		}
		return itemMap;
	}
	
	private void loadItems(Map<String, Double> itemMap) throws BasketException {
		try {
			String items = properties.getProperty("ITEMS");
			if (items !=null && items.length() > 0) {
				String itemArray[] = items.split("\\|");
				if (itemArray != null && itemArray.length > 0) {
					for (String arr: itemArray) {
						if (arr!=null && arr.trim().length() > 0) {
							itemMap.put(arr.toUpperCase(), loadValue(arr));
						}
					}
				} else {
					throw new BasketException("Configuration Loading error. Items are not mapped correctly");
				}
			} else {
				throw new BasketException("Configuration Loading error. Items are not found in config file");
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new BasketException("");
		}
	}
	
	private static Double loadValue(String item) throws BasketException {
		String value = properties.getProperty(item);
		Double dValue = null;
		if (value != null && value.length() > 0) {
			try {
				dValue = Double.parseDouble(value);
			} catch (NumberFormatException nExp) {
				throw new BasketException(item + "- item contains non numeric value -" + value);
			}
		} else {
			throw new BasketException(item + "- item contains NULL or EMPTY value from property value. PLease correct-" + value);
		}
		return dValue;
		
	}

	/**
	* A handle to the unique Singleton instance.
	*/
	private static ApplicationHolder instance = null;

	/**
	* Method used to access the instance of this class.
	* @return The unique instance of this class.
	*/
 	public static synchronized ApplicationHolder getInstance() 
	{	
		
	 if (null == instance) {
    		instance = new ApplicationHolder();
	 }
 		return instance;
	}
	
	/**
	* Retrieves the ApplicationHolder data from the properties
	*/
	public String getProperty(String key) 
	{
		String getKey;   								      //A method should have only one return statement
		if(key == null){
			getKey=key;
		}
		else{
			getKey=properties.getProperty(key);
		}
		return getKey;
	
	}

}