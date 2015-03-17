package com.nike.dao;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nike.dao.impl.ProductDaoImpl;
import com.nike.database.ProductDB;

/**
 * JUnit written with Spring to test {@link ProductDao#findAll()}
 * {@link ProductDao#findProductByName(String)} and
 * {@link ProductDao#findProductByPrice(int)} functionality
 * 
 * **/
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductDaoTest extends TestCase {

	@Configuration
	static class Config {

		@Bean
		public ProductDao productDao() {
			ProductDao productDao = new ProductDaoImpl();
			return productDao;
		}

		@Bean
		public ProductDB productDB() {
			ProductDB productDB = new ProductDB();
			return productDB;
		}
	}

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductDB productDB;

	@Test
	public void testFindAll() {
		Assert.assertNotNull(productDao.findAll());
	}

	@Test
	public void testIndividualProductPrice() {
		Assert.assertEquals(20, productDao.findProductByName("A").getPrice());
		Assert.assertEquals(50, productDao.findProductByName("B").getPrice());
		Assert.assertEquals(30, productDao.findProductByName("C").getPrice());
	}

	@Test
	public void testIndividualProductName() {
		Assert.assertEquals("A", productDao.findProductByPrice(20).getName());
		Assert.assertEquals("B", productDao.findProductByPrice(50).getName());
		Assert.assertEquals("C", productDao.findProductByPrice(30).getName());
	}

}
