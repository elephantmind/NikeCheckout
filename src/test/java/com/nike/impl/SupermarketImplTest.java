package com.nike.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nike.dao.ProductDao;
import com.nike.dao.impl.ProductDaoImpl;
import com.nike.database.ProductDB;
import com.nike.service.Supermarket;
import com.nike.service.impl.SupermarketImpl;

/**
 * JUnits written in conjunction with Spring Framework to test
 * {@link Supermarket#checkout(String)} functionality
 * */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class SupermarketImplTest {

	@Configuration
	static class Config {

		@Bean
		public Supermarket supermarket() {
			Supermarket supermarket = new SupermarketImpl();
			return supermarket;
		}

		@Bean
		public ProductDB productDB() {
			ProductDB productDB = new ProductDB();
			return productDB;
		}

		@Bean
		public ProductDao productDao() {
			ProductDao productDao = new ProductDaoImpl();
			return productDao;
		}
	}

	@Autowired
	Supermarket supermarket;

	@Autowired
	ProductDB productDB;

	@Autowired
	ProductDao productDao;

	@Test
	public void testNullItemsList() {
		Throwable e = null;

		try {
			supermarket.checkout(null);
		} catch (Throwable ex) {
			e = ex;
		}
		Assert.assertTrue(e instanceof Exception);
	}

	@Test
	public void testInvalidItemsList() {
		Throwable e = null;

		try {
			supermarket.checkout("12345");
		} catch (Throwable ex) {
			e = ex;
		}
		Assert.assertTrue(e instanceof Exception);

	}

	@Test
	public void testInvalidItemsListWithValidItems() {
		Throwable e = null;

		try {
			supermarket.checkout("ABC12345");
		} catch (Throwable ex) {
			e = ex;
		}
		Assert.assertTrue(e instanceof Exception);

	}

	@Test
	public void testPriceProductListTwo() {
		Assert.assertEquals(200, supermarket.checkout("AABBCC"));
	}

	@Test
	public void testPriceProductListThree() {
		Assert.assertEquals(300, supermarket.checkout("AAABBBCCC"));
	}

	@Test
	public void testPriceProductListFour() {
		Assert.assertEquals(400, supermarket.checkout("AAAABBBBCCCC"));
	}

	@Test
	public void testPriceProductListFive() {
		Assert.assertEquals(240, supermarket.checkout("ABBACBBAB"));
	}

	@Test
	public void testPriceProductListMultiple() {
		Assert.assertEquals(420, supermarket.checkout("ABBBBACBBABCB"));
	}

	@Test
	public void testPriceOfProductA() {
		Assert.assertEquals(20, supermarket.checkout("A"));
	}

	@Test
	public void testPriceOfProductB() {
		Assert.assertEquals(50, supermarket.checkout("B"));
	}

	@Test
	public void testPriceOfProductC() {
		Assert.assertEquals(30, supermarket.checkout("C"));
	}

	@Test
	public void testPriceOfProductANegative() {
		Assert.assertNotEquals(50, supermarket.checkout("A"));
	}

	@Test
	public void testPriceOfProductBNegative() {
		Assert.assertNotEquals(30, supermarket.checkout("B"));
	}

	@Test
	public void testPriceOfProductCNegative() {
		Assert.assertNotEquals(20, supermarket.checkout("C"));
	}

	@Test
	public void testPriceProductListOne() {
		Assert.assertEquals(100, supermarket.checkout("ABC"));
	}

}
