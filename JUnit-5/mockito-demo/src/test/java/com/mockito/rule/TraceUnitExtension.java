package com.mockito.rule;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

// Custom Rule to write trace logs before and after a test
public class TraceUnitExtension implements AfterEachCallback, BeforeEachCallback {
	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		System.out.println("Before the Unit Test: " + context.getDisplayName());
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		System.out.println("After the Unit Test: " + context.getDisplayName());
	}
}