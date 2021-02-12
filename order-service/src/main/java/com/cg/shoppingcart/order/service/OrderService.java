package com.cg.shoppingcart.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.shoppingcart.order.exception.ProductNotFoundException;
import com.cg.shoppingcart.order.model.Cart;
import com.cg.shoppingcart.order.model.Order;

@Service
public interface OrderService {

	public String searchProductById(Integer productId) throws ProductNotFoundException;

	public Cart addProductToCart(Integer productId) throws ProductNotFoundException;

	public void deleteProductFromCart(Integer productId);

	public Cart getCartById(Integer cartId);

	public List<Cart> viewAllCarts(String userRole);

	public Order placeOrder(Integer cartId);

	public Order getOrderDetails(Integer orderId);

	public void cancelOrder(Integer orderId);

}
