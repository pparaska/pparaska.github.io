package com.cg.shoppingcart.product.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class Product {

	private Integer productId;
	private String productName;
	private ProductPrice productPrice;
	private String description;
	private String type;

	public Product() {
	}

	public Product(Integer productId, String productName, ProductPrice productPrice, String description, String type) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.description = description;
		this.type = type;
	}

}
