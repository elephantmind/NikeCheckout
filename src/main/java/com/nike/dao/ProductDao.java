package com.nike.dao;

import java.util.List;

import com.nike.domain.Product;

/**
 * Interface to define Product DAO, capable to interacting with Data Source
 * 
 * */
public interface ProductDao {

	public List<Product> findAll();

	public Product findProductByName(String productName);

	public Product findProductByPrice(int price);

	public void addProduct(Product product);

}
