package com.daniele.marketshop.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.daniele.marketshop.utils.Constants;

public class CashierImpl implements Cashier{

	private CheckoutService service;

	public CashierImpl(String [] configs) { 
		this.service = new CheckoutServiceImpl(configs);
	}

	@Override
	public double serveCustomer() throws IOException {
		double tot = 0.0;
		try ( BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); )
		{
			String itemSku = null;
			List<String> listFinal = new ArrayList<>();
			while((itemSku=reader.readLine()) != null){
				
				if(!Constants.EMPTY_CHAR.equals(itemSku) ) 
				{
					if(!Constants.EXIT_CODE.equals(itemSku)) {
						System.out.println("Added items : " + itemSku);
						
						String[] cols = itemSku.split(Constants.SPACES_CHAR);
						
						listFinal.addAll(Arrays.asList(cols)); 
						
						tot = service.checkout(listFinal);
						System.out.println("Tot. $ " + tot + ". Would you like to add more items ( 'n' -> exit)?");
					}
				}
			}
		}
		return tot;
	}

}
