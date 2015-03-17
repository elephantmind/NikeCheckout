package com.nike.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nike.dao.ProductDao;
import com.nike.database.ProductDB;
import com.nike.domain.Product;

/**
 * DAO implementation to interact with data layer. This class implements
 * ProductDao and all methods defined in it: findAll(): Find all the products in
 * database findProductByName: Find a product by its name findProductByPrice:
 * Find a product by its price
 * 
 * ***/
@Component
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ProductDB productDb;

	@Override
	public List<Product> findAll() {
		return productDb.findAll();
	}

	@Override
	public Product findProductByName(String productName) {
		return productDb.findProductByName(productName);
	}

	@Override
	public Product findProductByPrice(int price) {
		return productDb.findProductByPrice(price);
	}

	@Override
	public void addProduct(Product product) {
		if (product != null) {
			productDb.addProduct(product);
		}
	}

}
