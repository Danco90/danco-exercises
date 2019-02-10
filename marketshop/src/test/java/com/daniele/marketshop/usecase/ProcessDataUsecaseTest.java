package com.daniele.marketshop.usecase;

import java.util.Arrays;
import java.util.List;

import com.daniele.marketshop.model.PricingRules;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ProcessDataUsecaseTest extends TestCase{
	
	
	/**
	 * 
	 * @return the suite of tests being tested
	 */
	public static Test suite()
    {
        return new TestSuite( ProcessDataUsecaseTest.class );
    }
	

  
	public void testCheckoutItems() {
		
		PricingRules prA = new PricingRules("A", 0.50, "3 for 1.30");
		PricingRules prB = new PricingRules("B", 0.30, "2 for 0.45");
		PricingRules prC = new PricingRules("C", 0.20, "");
		PricingRules prD = new PricingRules("D", 0.15, "");
		
        List<PricingRules> pricingRules = Arrays.asList(prA,prB,prC,prD); 
     
		ProcessDataUsecase usecase = new ProcessDataUsecase(pricingRules);
		
		List<String> list = Arrays.asList("A","B","A","A","B");

		double expected = 1.75 ;

		double result = usecase.checkoutItems(list);
		
		assertEquals(expected,result);
	}
}
