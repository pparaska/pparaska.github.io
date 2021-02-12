package com.cg.shoppingcart.login.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.shoppingcart.login.model.Product;

@RestController
@Validated
public class LoginController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "/getAllProducts")
	public String getProductList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8082/product/all", HttpMethod.GET, entity, String.class)
				.getBody();
	}

	@GetMapping(value = "/getProductById/{productId}")
	public String getProductById(@PathVariable("productId") String productId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8082/product/" + productId, HttpMethod.GET, entity, String.class)
				.getBody();
	}

	@PostMapping("/addNewProduct/{role}")
	public String createProducts(@PathVariable("role") String role, @RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

		return restTemplate
				.exchange("http://localhost:8082/product/addProduct/" + role, HttpMethod.POST, entity, String.class)
				.getBody();
	}

	@PutMapping("/updateProduct/{productId}/{role}")
	public String updateProduct(@PathVariable("productId") String productId, @PathVariable("role") String role,
			@RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

		return restTemplate.exchange("http://localhost:8082/product/updateProduct/" + productId + "/" + role,
				HttpMethod.PUT, entity, String.class).getBody();
	}

	@DeleteMapping("/deleteProduct/{productId}/{userRole}")
	public String deleteProductById(@PathVariable("productId") String productId,
			@PathVariable("userRole") String userRole) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(headers);

		return restTemplate.exchange("http://localhost:8082/product/delete/" + productId + "/" + userRole,
				HttpMethod.DELETE, entity, String.class).getBody();
	}

	@DeleteMapping("/deleteAllProduct/{userRole}")
	public String deleteAllProducts(@PathVariable("userRole") String userRole) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(headers);

		return restTemplate.exchange("http://localhost:8082/product/delete/all/" + userRole, HttpMethod.DELETE, entity,
				String.class).getBody();
	}
}
