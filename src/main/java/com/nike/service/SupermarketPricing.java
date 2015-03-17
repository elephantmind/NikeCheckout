package com.nike.service;

//Adding this interface to for priceRule feature
public interface SupermarketPricing {

	public void priceRule(String productName, int price, int checkoutQuantity,
			int specialOfferQuantity);

}
