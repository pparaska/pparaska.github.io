package com.cg.shoppingcart.payment.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Cart {
	
	private Integer cartId;
	private Integer numberOfProducts;
	private Object product;
	
	
	
	public Cart(Integer cartId, Integer numberOfProducts, Object product) {
		super();
		this.cartId = cartId;
		this.numberOfProducts = numberOfProducts;
		this.product = product;
	}



	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
