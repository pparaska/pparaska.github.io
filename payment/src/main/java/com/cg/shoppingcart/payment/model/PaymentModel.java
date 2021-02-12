package com.cg.shoppingcart.payment.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class PaymentModel {
	
	private Object orderDeatils;
	private Float totalPay;
	private Integer transactionId;
	private Float totalAccoountBalance;
	

}
