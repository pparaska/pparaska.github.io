package com.cg.shoppingcart.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.shoppingcart.payment.model.PaymentModel;
import com.cg.shoppingcart.payment.service.PaymentServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentServiceImpl paymentServiceImpl;

	@ApiOperation(value = "Get Payment Details by Transaction Id ", response = Iterable.class, tags = "getPaymanetDetailsUsingTransactionId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/{transactionId}")
	public PaymentModel getPaymanetDetailsUsingTransactionId(@PathVariable Integer transactionId) {
		return paymentServiceImpl.getPaymentDetailsByTransactionId(transactionId);

	}
	
	@ApiOperation(value = "Pay order Bill ", response = Iterable.class, tags = "payOrderBill")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping("/payOrderBill/{orderId}")
	public PaymentModel payOrderBill(@PathVariable Integer orderId) {
		return paymentServiceImpl.payOrderBill(orderId);
	}
}
