package com.cg.shoppingcart.order.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.shoppingcart.order.exception.ProductNotFoundException;
import com.cg.shoppingcart.order.model.Cart;
import com.cg.shoppingcart.order.model.Order;
import com.cg.shoppingcart.order.model.Product;

@Service
public class OrderServiceImpl implements OrderService {

	ArrayList<Cart> carts = new ArrayList<>();
	ArrayList<Order> orders = new ArrayList<>();

	@Autowired
	RestTemplate restTemplate;

	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Override
	public String searchProductById(Integer productId) throws ProductNotFoundException {
		logger.info("Inside searchProductById method");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8082/product/" + productId, HttpMethod.GET, entity, String.class)
				.getBody();
	}

	Object stringToProduct(Integer productId) throws ProductNotFoundException {
		String productToCart = searchProductById(productId).toString();
		Object product = new Product();
		product = productToCart;
		return product;

	}

	@Override
	public Cart addProductToCart(Integer productId) throws ProductNotFoundException {
		logger.info("Inside addProductToCart method");

		Object product = stringToProduct(productId);
		Cart cart = new Cart();
		cart.setCartId(productId);
		cart.setNumberOfProducts(1);
		cart.setProduct(product);
		carts.add(cart);
		logger.info("Product in the cart {} added", cart);

		return cart;
	}

	@Override
	public void deleteProductFromCart(Integer productId) {
		logger.info("Inside deleteProductFromCart method");

		Cart cart = getCartById(productId);
		carts.remove(cart);
	}

	@Override
	public Cart getCartById(Integer cartId) {
		logger.info("Inside getCartById method");
		try {
			carts.get(cartId);
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}
		return carts.get(cartId);

	}

	@Override
	public List<Cart> viewAllCarts(String userRole) {
		if (userRole == "admin")
			;
		return carts;
	}

	@Override
	public Order placeOrder(Integer cartId) {
		logger.info("Inside placeOrder method");

		Cart cart = getCartById(cartId);
		Order order = new Order();
		order.setCart(cart);
		order.setOrderId(cartId);
		order.setOrderTotal(123.2f);
		;
		logger.info("Order is{} added", order);
		orders.add(order);
		return order;
	}

	@Override
	public Order getOrderDetails(Integer orderId) {
		logger.info("Inside getOrderDetails method");

		return orders.get(orderId);

	}

	@Override
	public void cancelOrder(Integer orderId) {
		getOrderDetails(orderId);
		orders.remove(getOrderDetails(orderId));

	}

}
