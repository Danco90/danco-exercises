package com.daniele.marketshop.usecase;

import java.util.Arrays;
import java.util.List;

import com.daniele.marketshop.model.PricingRules;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CSVReaderUsecaseTest extends TestCase{
	
	/**
	 * 
	 * @return the suite of tests being tested
	 */
	public static Test suite()
    {
        return new TestSuite( CSVReaderUsecaseTest.class );
    }

	public void testLoadPricingRules() {
		 
        final String CSV_FILE = "input/pricingRules.csv" ;
		final String CSV_SPLIT_BY_PATTERN = "," ;
		String [] configs = {CSV_FILE, CSV_SPLIT_BY_PATTERN};
		
		CSVReaderUsecase cSVReaderUsecase = new CSVReaderUsecase(configs);
		
		PricingRules prA = new PricingRules("A", 0.50, "3 for 1.30");
		PricingRules prB = new PricingRules("B", 0.30, "2 for 0.45");
		PricingRules prC = new PricingRules("C", 0.20, "");
		PricingRules prD = new PricingRules("D", 0.15, "");
		
        List<PricingRules> expected = Arrays.asList(prA,prB,prC,prD); 

		List<PricingRules> pricingRulesList = cSVReaderUsecase.loadPricingRules();

		assertTrue(pricingRulesList.size()==expected.size());
		
		long expectedMatches = expected.size();
		
		long matches = pricingRulesList.stream().filter( pr -> {
			return expected.stream().anyMatch( epr -> {
				return  pr.getItem().equals(epr.getItem()) 
						&& pr.getUnitPrice()==(epr.getUnitPrice()) 
						&& pr.getSpecialPrice().equals(epr.getSpecialPrice());
	 	
			});
		}).count();
		if (matches > 0)
            System.out.println("pricingRules List has " + matches + " common elements with the expected once!");
        else
            System.out.println("not common");
		
		assertEquals(expectedMatches, matches);
		
	}
	
	
}
