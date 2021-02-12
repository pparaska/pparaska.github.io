package com.cg.shoppingcart.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.shoppingcart.product.model.Product;
import com.cg.shoppingcart.product.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@ApiOperation(value = "Get list of Products in the System ", response = Iterable.class, tags = "getAllProducts")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/all")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@ApiOperation(value = "Get a Product by productId ", response = Iterable.class, tags = "getProductById")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/{productId}")
	public Product getProductById(@PathVariable Integer productId) {
		logger.info("Inside getProductById method");
		return productService.getProductById(productId);
	}

	@ApiOperation(value = "Add new Product ", response = Iterable.class, tags = "addNewProduct")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping("/addProduct/{userRole}")
	public void addNewProduct(@RequestBody Product product, @PathVariable String userRole) {
		logger.info("Inside addNewProduct method");
		productService.addNewProduct(product, userRole);
	}

	@ApiOperation(value = "Update Product by productId", response = Iterable.class, tags = "updateProductById")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@PutMapping("/updateProduct/{productId}/{userRole}")
	public void updateProductDetails(@RequestBody Product product, @PathVariable Integer productId, @PathVariable String userRole) {
		logger.info("Inside updateProductDetails method");
		productService.updateProductById(product.getProductId(), userRole, product);
	}

	@ApiOperation(value = "Delete a Product by productId ", response = Iterable.class, tags = "deleteProductById")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@DeleteMapping("/delete/{productId}/{userRole}")
	public void deleteProuctById(@PathVariable Integer productId, @PathVariable String userRole) {
		logger.info("Inside deleteProuctById method");
		productService.deleteProductById(productId, userRole);
	}

	@ApiOperation(value = "Delete All products", response = Iterable.class, tags = "deleteAllProducts")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@DeleteMapping("/delete/all/{userRole}")
	public void deleteAllProducts(@PathVariable String userRole) {
		logger.info("Inside deleteAllProducts method");
		productService.deleteAllProducts(userRole);
	}
}
