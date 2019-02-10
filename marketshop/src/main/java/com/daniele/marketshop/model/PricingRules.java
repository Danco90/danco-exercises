package com.daniele.marketshop.model;

public class PricingRules {
	
	private String item ;
	
	private double unitPrice;
	
	private String specialPrice;
	
	public PricingRules(String item, double unitPrice) {
		this.item = item;
		this.unitPrice = unitPrice;
	}
	
	public PricingRules(String item, double unitPrice, String specialPrice) {
		this.item = item;
		this.unitPrice = unitPrice;
		this.specialPrice = specialPrice;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(String specialPrice) {
		this.specialPrice = specialPrice;
	}
	
	

}
