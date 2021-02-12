package com.cg.shoppingcart.order.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Order {
	
	private Cart cart;
	private Integer orderId;
	private Float orderTotal;
	
	
	public Order(Cart cart, Integer orderId, Float orderTotal) {
		super();
		this.cart = cart;
		this.orderId = orderId;
		this.orderTotal = orderTotal;
	}



	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
