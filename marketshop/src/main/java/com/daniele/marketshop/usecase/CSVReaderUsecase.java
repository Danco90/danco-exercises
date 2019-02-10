package com.daniele.marketshop.usecase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.daniele.marketshop.model.PricingRules;
import com.daniele.marketshop.utils.Constants;

public class CSVReaderUsecase {

	private String [] configs;

	public CSVReaderUsecase(String [] configs){
		this.configs = configs;
	}
	
	public List<PricingRules> loadPricingRules() {
		
		String filePath  = configs.length >= 1 ? configs[0] : Constants.DEFAULT_CSV_FILE;
		String splitPattern = configs.length >= 2 ? configs[1] : Constants.DEFAULT_CSV_SPLIT_PATTERN;
		
		List<PricingRules> ruleList = new ArrayList<>();
		System.out.println("Attempting to load PricingRules file from path: '" + filePath + "' ...");
		try( BufferedReader br =  new BufferedReader(new FileReader(filePath));){
			System.out.println("\n+--------------------------------+");
			System.out.println("|         'PricingRules'         |");
			System.out.println("+------------+-------+-----------+");
			System.out.println("|  Item      | Price | unitPrice |");
			System.out.println("+------------+-------+-----------+");
			String line = "";
			while ((line = br.readLine()) != null) 
			{    
				String[] row = line.split(splitPattern);
				if(row.length > 0) 
				{	System.out.print("| ");
                		for(int i=0; i<row.length; i++) {
                			System.out.print("   " + row[i] + "   ");
                		}
                		System.out.println();
	                PricingRules pricingRules = new PricingRules(row[0], Double.parseDouble(row[1]));
	                
	                if(row.length > 2) {
	                	  pricingRules.setSpecialPrice(row[2]); 
	                } else { 
	                	  pricingRules.setSpecialPrice(""); 
	                }
	                ruleList.add(pricingRules);
                }
            }
			System.out.println("+--------------------------------+\n");
		} catch (IOException e){
			e.printStackTrace();
		} 
		return ruleList;
	}

}
