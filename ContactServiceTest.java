package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
	
	@BeforeEach
	void init() {
		ContactService.getInstance().database.clear(); // clear the local database before each test
	}
	
	@Test
	void testGetInstance () {
		assertThat(ContactService.getInstance()).isNotNull();
	}
	
	@Test
	void testAdd() throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1231234567", "1234 Example St");
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		assertThat(ContactService.getInstance().database)
			.containsEntry("1", contact);
	}
	
	@Test
	void testFailedAdd() throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1231234567", "1234 Example St"); // add contact so id will be taken
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		assertThat(ContactService.getInstance().database)
			.containsEntry("1", contact);
		
		Contact contactFail = new Contact("1", "First", "Last", "1231234567", "1234 Example St");
		assertThat(ContactService.getInstance().add(contactFail)).isFalse(); // try to add contact with an existing id
		
	}
	
	@Test
	void testDelete() throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1231234567", "1234 Example St");
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		assertThat(ContactService.getInstance().delete("1")).isTrue();
		assertThat(ContactService.getInstance().database).doesNotContainEntry("1", contact);
	}
	
	@Test
	void testFailedDelete() throws Exception {
		assertThat(ContactService.getInstance().delete("1")).isFalse(); // try to delete id not in local database
	}
	
	@Test
	void testUpdate() throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1231234567", "1234 Example St");
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		
		Contact updated = new Contact("2", "Robert", "DiMaio", "2031234567", "1234 MyStreet St");
		assertThat(ContactService.getInstance().update("1", updated)).isTrue();
		assertThat(ContactService.getInstance().database)
			.extracting("1")
			.hasFieldOrPropertyWithValue("firstName", "Robert")
			.hasFieldOrPropertyWithValue("lastName", "DiMaio")
			.hasFieldOrPropertyWithValue("phone", "2031234567")
			.hasFieldOrPropertyWithValue("address", "1234 MyStreet St");
	}
	
	@Test
	void testFailedUpdate() throws Exception {
		Contact updated = new Contact("2", "Robert", "DiMaio", "2031234567", "1234 MyStreet St");
		assertThat(ContactService.getInstance().update("1", updated)).isFalse(); //  try to update id not in local database
	}

}
