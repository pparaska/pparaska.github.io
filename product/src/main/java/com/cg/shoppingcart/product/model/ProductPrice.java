package com.cg.shoppingcart.product.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class ProductPrice {
	
	private String currency;
	private Float price;
	
	public ProductPrice(String currency, Float price) {
		super();
		this.currency = currency;
		this.price = price;
	}
	
	

}
