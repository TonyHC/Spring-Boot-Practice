package com.mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasToString;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mockito.utils.StaticUtils;

@ExtendWith(MockitoExtension.class)
class StaticUtilsTest {
	@Test
	void Should_ReturnName_When_MockNoArgStaticMethodCall() {		
		// use the Mockito.mockStatic(Class<T> classToMock) method to mock invocations to static method calls
		try (MockedStatic<StaticUtils> utilities = Mockito.mockStatic(StaticUtils.class)) {
			utilities.when(StaticUtils::name).thenReturn("JUnit 5");
			assertThat(StaticUtils.name(), hasToString("JUnit 5"));
		}
		
		assertThat(StaticUtils.name(), hasToString("Mockito"));
	}
	
	@Test
	void Should_ReturnRangeOfNumbers_When_MockStaticMethodWithArgsCall() {
		try (MockedStatic<StaticUtils> utilities = Mockito.mockStatic(StaticUtils.class)) {
			utilities.when(() -> StaticUtils.range(2, 6))
				.thenReturn(Arrays.asList(1, 2, 3));
			
			assertThat(StaticUtils.range(2, 6), hasItems(1, 2, 3));	
		}
		
		assertThat(StaticUtils.range(2, 6), hasItems(2, 3, 4, 5));
	}
}