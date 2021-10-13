package com.mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mockito.rule.TraceUnitExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(TraceUnitExtension.class)
public class FirstMockitoTest {
	@Test
	void simpleTest() {
		assertTrue(true);
	}
}