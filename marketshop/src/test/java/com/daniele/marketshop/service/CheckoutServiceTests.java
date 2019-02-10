package com.daniele.marketshop.service;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class CheckoutServiceTests extends TestCase{
	
	
	/**
	 * 
	 * @return the suite of tests being tested
	 */
	public static Test suite()
    {
        return new TestSuite( CheckoutServiceTests.class );
    }
	
	
	public void testCheckout() {
		final String CSV_FILE = "input/pricingRules.csv" ;
	    final String CSV_SPLIT_BY_PATTERN = "," ;
	    String [] configs = {CSV_FILE, CSV_SPLIT_BY_PATTERN};
		
		CheckoutServiceImpl service = new CheckoutServiceImpl(configs);
		/**
		 * +-----------------+
		 * | Pricing Rules   |
		 * +--+----+---------+
		 * |A |0.5 |3 x 1.30 |
		 * +--+----+---------+
		 * |B |0.3 |2 x 0.45 |
		 * +--+----+---------+
		 * |C |0.2 |         |
		 * +--+----+---------+
		 * |D |0.15|         |
		 * +--+----+---------+
		 */
		List<String> list = Arrays.asList("A","B","A","A","B");

		double expected = 2.20 ;

		double result = service.checkout(list);
		
		assertEquals(expected,result);
	}

}
