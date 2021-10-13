package com.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpyTest {
	private List arrayListSpy;
	
	@BeforeEach
	void setup() {
		// A Spy gets all logic from the Class
		// You can stub specific methods of your choice
		// Avoid Spy (mostly for legacy systems don't have access to code of dependencies)
		arrayListSpy = spy(ArrayList.class);
	}
	
	@Test
	void test() {
		// Stub the size() to return 5 instead of current size of ArrayList
		when(arrayListSpy.size()).thenReturn(5);
		assertEquals(5, arrayListSpy.size());
		
		arrayListSpy.add("Test");
		// Verify that the add("Test") of ArrayList Class was use
		verify(arrayListSpy).add("Test");
		// Verify that the add("Unit") of ArrayList Class was not use
		verify(arrayListSpy, never()).add("Unit");
	}
}