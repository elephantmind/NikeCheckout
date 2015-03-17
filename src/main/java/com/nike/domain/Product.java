package com.nike.domain;

/**
 * Domain model to represent Product. Currently it interacts with ProductDB to
 * read/update instances. We can use JAXB binding and map them to a relational
 * DB via persistence layer.
 * ***/
public class Product extends AbstractProduct {

	private String name;
	private int price;
	private int checkoutQuantity = 0;
	private int specialOfferQuantity = 0;

	/**
	 * @return product name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the product name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return product price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return quantity of an item user wants to buy
	 */
	public int getCheckoutQuantity() {
		return checkoutQuantity;
	}

	/**
	 * @param checkoutQuantity
	 *            quantity of an item user wants to buy
	 * 
	 */
	public void setCheckoutQuantity(int checkoutQuantity) {
		this.checkoutQuantity = checkoutQuantity;
	}

	/**
	 * @return quantity to consider for price calculations
	 */
	public int getSpecialOfferQuantity() {
		return specialOfferQuantity;
	}

	/**
	 * @param specialOfferQuantity
	 *            quantity to consider for price calculations
	 */
	public void setSpecialOfferQuantity(int specialOfferQuantity) {
		this.specialOfferQuantity = specialOfferQuantity;
	}

}
