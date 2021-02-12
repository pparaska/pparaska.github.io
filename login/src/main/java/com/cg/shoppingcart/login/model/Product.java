package com.cg.shoppingcart.login.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Product {

	@Id
    @GeneratedValue
	private Integer productId;
	
    @NotEmpty(message = "Please provide a name")
	private String productName;
    
    @NotNull(message = "Please provide a price")
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
