package com.mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HamcrestMatchersTest {
	@Test
	void Should_Pass_When_UsingHamcrestMatchers() {
		List<Integer> scores = Arrays.asList(12, 42, 52, 68);
		
		// scores has 4 items
		assertThat(scores, hasSize(4));
		// scores has the following items
		assertThat(scores, hasItems(42,68));
		// every item > 90
		assertThat(scores, everyItem(greaterThan(10)));
		// every item < 90
		assertThat(scores, everyItem(lessThan(100))); 
		
		// Empty String
		assertThat("", isEmptyString());
		// Empty or Null String
		assertThat(null, isEmptyOrNullString());
		
		Integer[] numbers = {4, 11, 19};
		
		// Length of Array
		assertThat(numbers, arrayWithSize(3));
		// Array containing each element in order
		assertThat(numbers, arrayContaining(4, 11, 19));
		// Array containing each element in any order
		assertThat(numbers, arrayContainingInAnyOrder(11, 4, 19));
		
		Map<String, Integer> values = new HashMap<>();
		values.put("one", 1);
		values.put("two", 2);
		
		// Map contains the key 'one'
		assertThat(values, hasKey("one"));
		// Map contins the value 2
		assertThat(values, hasValue(2));
	}
}