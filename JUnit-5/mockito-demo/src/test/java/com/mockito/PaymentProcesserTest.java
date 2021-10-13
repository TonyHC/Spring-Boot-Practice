package com.mockito;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import com.mockito.business.PaymentProcessor;

class PaymentProcesserTest {
	@Test
	void Should_ReturnBigDecimal_When_MockDifferentObjectConstruction() {
		// Mocking Object Constructions
		try (MockedConstruction<PaymentProcessor> mocked = Mockito.mockConstruction(PaymentProcessor.class)) {
			PaymentProcessor firstInstance = new PaymentProcessor();
			PaymentProcessor secondInstance = new PaymentProcessor("PayPal", BigDecimal.TEN);
			PaymentProcessor thirdInstance = new PaymentProcessor(true, "PayPal", BigDecimal.TEN);
		
			when(firstInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
			when(secondInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
			when(thirdInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
			
			assertAll(
					() -> assertEquals(BigDecimal.TEN, firstInstance.chargeCustomer("23", BigDecimal.valueOf(23))),
					() -> assertEquals(BigDecimal.TEN, secondInstance.chargeCustomer("23", BigDecimal.valueOf(23))),
					() -> assertEquals(BigDecimal.TEN, thirdInstance.chargeCustomer("23", BigDecimal.valueOf(23))),
					() -> assertEquals(3, mocked.constructed().size())
			);
		}
	}
	
	@Test
	void Should_ReturnBigDecimal_When_MockDifferentObjectConstructions() {
		// Use an overloaded version of mockConstruction() to pass a MockInitializer as a second argument
		// This MockInitializer is a callback that we use to stub the behavior of the mock
		try (MockedConstruction<PaymentProcessor> mocked = Mockito.mockConstruction(PaymentProcessor.class,
				(mock, context) -> {
					// Stub chargeCustomer(String, BigDecimal)
					when(mock.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
				})) {
			PaymentProcessor firstInstance = new PaymentProcessor();
			PaymentProcessor secondInstance = new PaymentProcessor("PayPal", BigDecimal.TEN);
			PaymentProcessor thirdInstance = new PaymentProcessor(true, "PayPal", BigDecimal.TEN);
			
			assertAll(
					() -> assertEquals(BigDecimal.TEN, firstInstance.chargeCustomer("23", BigDecimal.valueOf(23))),
					() -> assertEquals(BigDecimal.TEN, secondInstance.chargeCustomer("23", BigDecimal.valueOf(23))),
					() -> assertEquals(BigDecimal.TEN, thirdInstance.chargeCustomer("23", BigDecimal.valueOf(23))),
					() -> assertEquals(3, mocked.constructed().size())
			);
		}
	}
}