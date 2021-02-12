package com.cg.shoppingcart.payment.service;

import org.springframework.stereotype.Service;

import com.cg.shoppingcart.payment.model.PaymentModel;

@Service
public interface PaymentService {
	
	public PaymentModel getPaymentDetailsByTransactionId(Integer transactionId);
		
	public PaymentModel payOrderBill(Integer orderId);

}
