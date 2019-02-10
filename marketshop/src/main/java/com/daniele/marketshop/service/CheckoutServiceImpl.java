package com.daniele.marketshop.service;

import java.util.List;

import com.daniele.marketshop.model.PricingRules;
import com.daniele.marketshop.usecase.CSVReaderUsecase;
import com.daniele.marketshop.usecase.ProcessDataUsecase;

public class CheckoutServiceImpl implements CheckoutService {	
	
	private CSVReaderUsecase csvReaderUsecase;
	
	private ProcessDataUsecase processDataUsecase;

	public CheckoutServiceImpl(String [] configs) {
		this.csvReaderUsecase = new CSVReaderUsecase(configs); 
		
	}

	public double checkout(List<String> items) {
		List<PricingRules> pricingRules = csvReaderUsecase.loadPricingRules();
		
		processDataUsecase = new ProcessDataUsecase(pricingRules);
		
		return processDataUsecase.checkoutItems(items);
	}

}
