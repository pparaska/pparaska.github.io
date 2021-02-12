package com.cg.shoppingcart.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.shoppingcart.order.exception.ProductNotFoundException;
import com.cg.shoppingcart.order.model.Cart;
import com.cg.shoppingcart.order.model.Order;
import com.cg.shoppingcart.order.service.OrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);

	@ApiOperation(value = "Search product by Id ", response = Iterable.class, tags = "searchProductById")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/searchProductById/{productId}")
	public String searchProductById(@PathVariable Integer productId) throws ProductNotFoundException {
		logger.info("Inside searchProductById method");
		return orderService.searchProductById(productId);
	}
		

	@ApiOperation(value = "Add product to cart ", response = Iterable.class, tags = "addProductToCart")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping("/addToCart/{productId}")
	public Cart addProductToCart(@PathVariable("productId") Integer productId) throws ProductNotFoundException {
		logger.info("Inside addProductToCart method");
		return orderService.addProductToCart(productId);

	}

//	@ApiOperation(value = "Delete product from cart", response = Iterable.class, tags = "deleteProductFromCart")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
//			@ApiResponse(code = 404, message = "not found!!!") })
//	@DeleteMapping("/deleteProductFromCart/{productId}")
//	public void deleteProductFromCart(@PathVariable Integer productId) {
//		logger.info("Inside deleteProductFromCart method");
//		orderService.deleteProductFromCart(productId);
//	}

	@ApiOperation(value = "Get Cart Details", response = Iterable.class, tags = "getCartDetails")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/getCartDetails/{cartId}")
	public Cart getCartDetails(@PathVariable Integer cartId) {
		logger.info("Inside getCartDetails method");
		return orderService.getCartById(cartId);
	}

	@ApiOperation(value = "Get list of Carts", response = Iterable.class, tags = "viewAllCarts")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/viewAllCarts/{userRole}")
	public List<Cart> viewAllCarts(@PathVariable String userRole) {
		logger.info("Inside viewAllCarts method");
		return orderService.viewAllCarts(userRole);
	}

	@ApiOperation(value = "Place order", response = Iterable.class, tags = "placeOrder")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping("/checkout/placeOrder/{cartId}")
	public Order placeOrder(@PathVariable Integer cartId) {
		logger.info("Inside placeOrder method");
		return orderService.placeOrder(cartId);
	}

	@ApiOperation(value = "Get Order Details", response = Iterable.class, tags = "getOrderDetails")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/getOrderDeatils/{orderId}")
	public Order getOrderDetails(@PathVariable Integer orderId) {
		logger.info("Inside getOrderDetails method");
		return orderService.getOrderDetails(orderId);

	}

	@ApiOperation(value = "Cancel Order", response = Iterable.class, tags = "cancelOrder")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@DeleteMapping("/cancelOrder/{orderId}")
	public void cancelOrder(@PathVariable Integer orderId) {
		logger.info("Inside cancelOrder method");
		orderService.cancelOrder(orderId);
	}

}
