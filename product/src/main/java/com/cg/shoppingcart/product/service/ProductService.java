package com.cg.shoppingcart.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.shoppingcart.product.model.Product;

@Service
public interface ProductService {
	
	public void addNewProduct(Product product, String userRole);

	public List<Product> getAllProducts();
	
	public Product getProductById(Integer productId);
	
	public void updateProductById(Integer productId, String userRole, Product product);
	
	public void deleteProductById(Integer productId, String userRole);
	
	public void deleteAllProducts(String userRole);
	
	

}
