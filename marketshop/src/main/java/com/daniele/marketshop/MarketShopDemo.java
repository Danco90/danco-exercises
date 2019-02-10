package com.daniele.marketshop;

import java.io.IOException;

import com.daniele.marketshop.service.Cashier;
import com.daniele.marketshop.service.CashierImpl;

public class MarketShopDemo  {
	
	public static void main (String [] args) throws IOException {
		System.out.print("Enter your items to be checkedout (separated by space) : ");
		
		Cashier cashier = new CashierImpl(args);
		
		cashier.serveCustomer();
		
	}

}
