package com.nike.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nike.domain.Product;

/**
 * ProductDB represents in memory database. Initializes products A, B and C when
 * instance of ProductDB is created
 */
@Component
public class ProductDB {

	private List<Product> list = null;

	public ProductDB() {
		init();
	}

	private void init() {

		list = new ArrayList<Product>();

		Product p = new Product();

		p.setName("A");
		p.setPrice(20);
		list.add(p);

		p = new Product();
		p.setName("B");
		p.setPrice(50);
		p.setCheckoutQuantity(5);
		p.setSpecialOfferQuantity(3);
		list.add(p);

		p = new Product();
		p.setName("C");
		p.setPrice(30);
		list.add(p);

	}

	public List<Product> findAll() {
		return list;
	}

	public Product findProductByName(String productName) {
		if (productName != null) {
			for (Product p : list) {
				if (p.getName().equals(productName)) {
					return p;
				}
			}
		}
		return null;
	}

	public Product findProductByPrice(int price) {
		for (Product p : list) {
			if (p.getPrice() == price) {
				return p;
			}
		}
		return null;
	}

	public void addProduct(Product product) {
		list.add(product);
	}

}
