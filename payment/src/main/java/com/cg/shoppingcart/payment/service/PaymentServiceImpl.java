package com.cg.shoppingcart.payment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.shoppingcart.payment.model.Order;
import com.cg.shoppingcart.payment.model.PaymentModel;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	List<PaymentModel> payments = new ArrayList<>();
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public PaymentModel getPaymentDetailsByTransactionId(Integer transactionId) {
		// TODO Auto-generated method stub
		return payments.get(transactionId);
	}

	public String searchOrderById(Integer orderId) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8083/order/getOrderDeatils/" + orderId, HttpMethod.GET, entity, String.class)
				.getBody();
	}
	
	Object stringToOrder(Integer orderId){
		String orderDetail = searchOrderById(orderId).toString();
		Object order = new Order();
		order = orderDetail;
		return order;

	}
	
	@Override
	public PaymentModel payOrderBill(Integer orderId) {
		Float totalAccountBalance = 100000f;
		Object order = stringToOrder(orderId);
		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setOrderDeatils(order);
		paymentModel.setTotalPay(123.2f);
		paymentModel.setTransactionId(orderId);
		if(totalAccountBalance > paymentModel.getTotalPay()) {
		paymentModel.setTotalAccoountBalance(totalAccountBalance - paymentModel.getTotalPay());
		}
		payments.add(paymentModel);
		return paymentModel;
	}


}
