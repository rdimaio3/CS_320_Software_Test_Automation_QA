package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ContactTest {
	
	@Test
	void testSuccessfulCreation() throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1231234567", "1234 Example St");
		assertThat(contact)
		.hasFieldOrPropertyWithValue("id", "1")
		.hasFieldOrPropertyWithValue("firstName", "First")
		.hasFieldOrPropertyWithValue("lastName", "Last")
		.hasFieldOrPropertyWithValue("phone", "1231234567")
		.hasFieldOrPropertyWithValue("address", "1234 Example St");
	}
	
	@Test
	void testSuccessfulSetters() throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1231234567", "1234 Example St");
		contact.setFirstName("Robert");
		contact.setLastName("DiMaio");
		contact.setPhone("2039999999");
		contact.setAddress("1234 MyStreet St");
		
		assertThat(contact)
			.hasFieldOrPropertyWithValue("firstName", "Robert")
			.hasFieldOrPropertyWithValue("lastName", "DiMaio")
			.hasFieldOrPropertyWithValue("phone", "2039999999")
			.hasFieldOrPropertyWithValue("address", "1234 MyStreet St");
			
	}
	
	@CsvSource({
		"' ',First,Last,1231234567,1234 Example St", // Blank ID
		",First,Last,1231234567,1234 Example St", // Null ID
		"123456789123,First,Last,1231234567,1234 Example St", // ID too long
		"1,' ',Last,1231234567,1234 Example St", // Blank firstName
		"1,,Last,1231234567,1234 Example St", // Null firstName
		"1,First123456789,Last,1231234567,1234 Example St", // firstName too long
		"1,First,' ',1231234567,1234 Example St", // Blank lastName
		"1,First,,1231234567,1234 Example St", // Null lastName
		"1,First,Last123456789,1231234567,1234 Example St", // lastName too long
		"1,First,Last,' ',1234 Example St", // Blank phone
		"1,First,Last,,1234 Example St", // Null phone
		"1,First,Last,123123456789,1234 Example St", // phone too long
		"1,First,Last,123456789,1234 Example St", // phone too short
		"1,First,Last,1231234567,' '", // Blank address
		"1,First,Last,1231234567,", // Null address
		"1,First,Last,1231234567,1234 Example St 1234 Example St 1234 Example St", // address too long
	})
	
	@ParameterizedTest
	void testFailedCreation(String id, String firstName, String lastName, String phone, String address) {
		assertThatThrownBy(() -> new Contact(id, firstName, lastName, phone, address))
		.isNotNull();
	}
	
	@CsvSource({
		"' ',", // blank FirstName
		",", // null firstName
		"First123456789,", // firstName too long
	})
	
	@ParameterizedTest
	void testFailedSetFirstName(String firstName) throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1231234567", "1234 Example St");
		assertThatThrownBy(() -> contact.setFirstName(firstName))
		.isNotNull();
	}
	
	@CsvSource({
		"' ',", // blank lastName
		",", // null lastName
		"Last123456789,", // lastName too long
	})
	
	@ParameterizedTest
	void testFailedSetLastName(String lastName) throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1231234567", "1234 Example St");
		assertThatThrownBy(() -> contact.setLastName(lastName))
		.isNotNull();
	}
	
	@CsvSource({
		"' ',", // blank phone
		",", // null phone
		"123123456789", // phone too long
		"1234567,", // phone too short ** must be exactly 10 digits, not just < **
	})
	
	@ParameterizedTest
	void testFailedSetPhone(String phone) throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1231234567", "1234 Example St");
		assertThatThrownBy(() -> contact.setPhone(phone))
		.isNotNull();
	}
	
	@CsvSource({
		"' ',", // blank address
		",", // null address
		"1234 Example St 1234 Example St 1234 Example St", // address too long
	})
	
	@ParameterizedTest
	void testFailedSetAddress(String address) throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1231234567", "1234 Example St");
		assertThatThrownBy(() -> contact.setAddress(address))
		.isNotNull();
	}
	
}
