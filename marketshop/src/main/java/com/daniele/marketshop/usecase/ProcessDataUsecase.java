package com.daniele.marketshop.usecase;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.daniele.marketshop.model.PricingRules;

public class ProcessDataUsecase {
	
	private List<PricingRules> pricingRules;

    public ProcessDataUsecase(List<PricingRules> pricingRules) {
    		this.pricingRules = pricingRules;
    }
    
    public double checkoutItems(List<String> items) {
		System.out.println("+--------------------------------+");
		System.out.println("|    DANCO )><><( MarketShop     |");
		System.out.println("|                                |");
		System.out.println("+----+------+------------+-------+");
		System.out.println("| No | Q.ty | Product    | Price |");
		System.out.println("+----+------+------------+-------+");
		
		AtomicReference<Double> basicAtomicSum = new AtomicReference<Double>(0.0);
		AtomicInteger c = new AtomicInteger();
		items.forEach(
				item -> {
					double price = getPrice(item);
					System.out.println("| "+c.incrementAndGet()+")    1*      "+item+"         "+price+ "");
					basicAtomicSum.accumulateAndGet(price, (x, y) -> x + y);
				}
		);
		System.out.println("+--------------------------------+");
		System.out.println("| (+) total amount owed   "+roundToNumDecimal(basicAtomicSum.get(), 2)+" $");

		getDistinctList(items).forEach(
				item -> {
					double price = 0;
					boolean discounted = isDiscounted(item);
					
					long occurrences = 0;
					if(discounted)
					{
						double discountedPrice = getSpecialPricePerUnit(item);
						occurrences = getItemOccurrInCurrentList(items,item);
						int discountLimit = getLimitDiscount(pricingRules, item);
						
						boolean isLimitMatched = checkIfItemOccurrMatchDiscountRules(occurrences, discountLimit);
						if(isLimitMatched) {
							
							long numVoucher = getItemDiscountVoucher(occurrences,discountLimit);
							double priceOffset = getPrice(item) - discountedPrice ;
							price = priceOffset * numVoucher * discountLimit;
							System.out.println("|  (-)  Discounted "+item+" "+ numVoucher * discountLimit  + "x   " + roundToNumDecimal(discountedPrice,3)+" $");
							
						}
					
					}
					basicAtomicSum.accumulateAndGet(price, (x, y) -> x - y);
				}
				
		);

		double tot = roundToNumDecimal(basicAtomicSum.get(), 2);
		System.out.println("\n| (=)           TOTAL :    "+ tot + " $");
		System.out.println("+--------------------------------+");
		
		return tot;
	}
	
	
	private boolean isDiscounted(String item) {
		if(item!=null && !item.isEmpty()){
		  String specPrice = pricingRules.stream().filter( o -> o.getItem().equals(item))
				.findFirst().get().getSpecialPrice();
		
			if(specPrice!=null && !specPrice.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	private double getPrice(String item) {
		if(item.isEmpty()) {
			return 0;
		}
		return pricingRules.stream().filter( 
				o -> o.getItem().equals(item)).findFirst().get().getUnitPrice();

	}
	
	private double getSpecialPricePerUnit(String item) 
	{
		String specPrice = pricingRules.stream()
		           .filter( o -> o.getItem().equals(item))
		           .findFirst().get().getSpecialPrice();
		
		String[] splitted = Arrays.stream(specPrice.split("\\s"))
									.toArray(String[]::new);
		
		double numUnits = Double.parseDouble(splitted[0]);
		double price = Double.parseDouble(splitted[2]);
		return price / numUnits;
	}
	
	private long getItemOccurrInCurrentList(List<String> items, String item) {
		return items.stream().filter( o -> o.equals(item)).count();
		
	}
	
	private List<String> getDistinctList(List<String> items) {
		return items.stream().distinct().collect(Collectors.toList());
	}
	
	private boolean checkIfItemOccurrMatchDiscountRules(long occurrences, int discountLimit) {
		if(occurrences >= discountLimit) {
    		   return true;
		}
		return false;
	}

	private int getLimitDiscount(List<PricingRules> pricingRules, String  item) {
		String specialPrice = pricingRules.stream()
								.filter( o -> o.getItem().equals(item))
								.findFirst().get().getSpecialPrice();
		
		String[] splitted = Arrays.stream(specialPrice.split("\\s"))
									.toArray(String[]::new);
		
		return Integer.parseInt(splitted[0]);
	}

	private long getItemDiscountVoucher(long occurrences, int limit) {
		return occurrences / limit ;
		
	}

	
	private double roundToNumDecimal(double amount, double pos) {
		double pow = Math.pow(10.0, pos);
		return Math.round(amount * pow) / pow;
	}
	
}
