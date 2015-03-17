package com.nike.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.nike.dao.ProductDao;
import com.nike.domain.Product;
import com.nike.exception.InvalidProductException;
import com.nike.exception.InvalidProductQuantityException;
import com.nike.service.Supermarket;

/**
 * This class implements Supermarket interface and adds checkout functionality
 * to Nike Checkout system. Calling Supermarket.checkout() returns total for
 * that order as an integer
 * **/
@Service
@ComponentScan
public class SupermarketImpl implements Supermarket {

	@Autowired
	ProductDao productDao;

	@Override
	public void priceRule(String productName, int price, int checkoutQuantity,
			int specialOfferQuantity) {
		Product p = new Product();
		p.setName(productName);
		p.setPrice(price);
		p.setCheckoutQuantity(checkoutQuantity);
		p.setSpecialOfferQuantity(specialOfferQuantity);

		productDao.addProduct(p);

	}

	@Override
	public int checkout(String items) {

		// Check if items is not instantiated before calling checkout()
		if (null == items || items.equals("")) {
			try {
				throw new Exception("No items to calculate price");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Check client abuse with invalid characters including digits and
		// special characters
		if (!isValidItemsList(items)) {
			try {
				throw new Exception("Invalid items included in list");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// everything is good, go ahead and calculate price
		return calculatePrice(items);
	}

	private int calculatePrice(String items) {

		int total = 0;

		String[] arr = items.split("(?!^)");

		// Map to maintain <Product, ProductCount>, these shall be wrappers
		// around Character and Integer classes in real world modeling
		Map<String, Integer> itemsMap = new HashMap<String, Integer>();

		for (String s : arr) {
			if (itemsMap.containsKey(s)) {
				itemsMap.put(s, itemsMap.get(s) + 1);
			} else {
				itemsMap.put(s, 1);
			}
		}

		Iterator<Entry<String, Integer>> iterator = itemsMap.entrySet()
				.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) iterator
					.next();
			String productName = pair.getKey();
			Integer quantity = pair.getValue();

			total = total + getIndividualProductTotal(productName, quantity);

		}

		// All is well, returning net amount
		return total;
	}

	private int getIndividualProductTotal(String productName, int quantity) {
		int total = 0;

		Product p = productDao.findProductByName(productName);
		if (p == null) {
			throw new InvalidProductException();
		}

		if (quantity == 0) {
			throw new InvalidProductQuantityException();
		}

		if (p.getCheckoutQuantity() > 0 && p.getSpecialOfferQuantity() > 0) {
			int offerPrice = (quantity / p.getCheckoutQuantity())
					* (p.getSpecialOfferQuantity() * p.getPrice());
			int originalPrice = p.getPrice()
					* (quantity % p.getCheckoutQuantity());
			total = originalPrice + offerPrice;
		} else {
			total = quantity * p.getPrice();
		}
		return total;
	}

	// This method does the validation for items list passed during checkout
	private boolean isValidItemsList(String items) {
		char[] arr = items.toCharArray();

		for (char c : arr) {
			if (Character.isLetterOrDigit(c)) {
				if (Character.isDigit(c)) {
					return false;
				}
			} else {
				return false;
			}
		}

		return true;
	}

}
