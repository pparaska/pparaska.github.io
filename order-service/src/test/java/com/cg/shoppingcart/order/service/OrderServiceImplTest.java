package com.cg.shoppingcart.order.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.cg.shoppingcart.order.exception.ProductNotFoundException;
import com.cg.shoppingcart.order.model.Cart;
import com.cg.shoppingcart.order.model.Order;
import com.cg.shoppingcart.order.model.Product;
import com.cg.shoppingcart.order.model.ProductPrice;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ OrderServiceImpl.class, Product.class, Cart.class, Order.class })
@PowerMockIgnore("javax.management.*")
public class OrderServiceImplTest {

	@Mock
	OrderServiceImpl orderServiceImpl;

	@BeforeEach
	public Product setUpProducts() {

		ProductPrice price = new ProductPrice("Euro", 12.3f);
		Product product = new Product();
		product.setDescription("Best ever product");
		product.setProductId(12345);
		product.setProductName("Wireless Headphones - JBL");
		product.setProductPrice(price);
		product.setType("Electronics");
		return product;
	}

	@Test
	public void viewAllCartsTest() {
		List<Cart> carts = new ArrayList<Cart>();

		Cart cartOne = new Cart(12345, 1, setUpProducts());
		Cart cartTwo = new Cart(54321, 2, setUpProducts());
		carts.add(cartOne);
		carts.add(cartTwo);
		when(orderServiceImpl.viewAllCarts("admin")).thenReturn(carts);

		assertEquals(2, carts.size());
	}

	@Test
	public void addProductToCartTest() throws ProductNotFoundException {
		List<Cart> carts = new ArrayList<Cart>();

		Cart cartOne = new Cart(12345, 1, setUpProducts());
		carts.add(cartOne);
		when(orderServiceImpl.addProductToCart(12345)).thenReturn(cartOne);

		assertEquals(orderServiceImpl.addProductToCart(12345), cartOne);

	}

	@Test
	public void removeProductFromCartTest() {
		List<Cart> carts = new ArrayList<Cart>();
		Cart cartOne = new Cart(12345, 1, setUpProducts());
		carts.add(new Cart(12345, 1, setUpProducts()));
		when(orderServiceImpl.getCartById(12345)).thenReturn(cartOne);
		orderServiceImpl.deleteProductFromCart(12345);
		List<Cart> afterProductDelete = new ArrayList<Cart>();
		Cart cart = orderServiceImpl.getCartById(12345);
		afterProductDelete.remove(cart);
		Assert.assertNotEquals(afterProductDelete, carts);
	}

	@Test
	public void getOrderDetailsTest() {
		Order order = new Order();
		Cart cartOne = new Cart(12345, 1, setUpProducts());
		order.setCart(cartOne);
		order.setOrderId(12345);
		order.setOrderTotal(123f);
		when(orderServiceImpl.getOrderDetails(12345)).thenReturn(order);

		assertEquals(orderServiceImpl.getOrderDetails(12345), order);
	}

	@Test
	public void testPlaceOrder() {
		List<Order> orders = new ArrayList<Order>();
		Order order = new Order();
		Cart cartOne = new Cart(12345, 1, setUpProducts());
		order.setCart(cartOne);
		order.setOrderId(12345);
		order.setOrderTotal(123f);
		orders.add(order);
		orderServiceImpl.cancelOrder(12345);
		List<Cart> afterCancelOrder = new ArrayList<Cart>();
		Order orderDetails = orderServiceImpl.getOrderDetails(12345);
		afterCancelOrder.remove(orderDetails);
		assertNotEquals(order, orderDetails);
	}

}
