package org.snhu.cs320.contact;

public class Contact {
	private String id;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	public Contact(String id, String firstName, String lastName, String phone, String address) throws Exception {
		super();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setAddress(address);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws Exception {
		if (firstName == null || firstName.trim().length() < 1 || firstName.length() > 10) {
			throw new Exception("firstName is invalid");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws Exception {
		if (lastName == null || lastName.trim().length() < 1 || lastName.length() > 10) {
			throw new Exception("lastName is invalid");
		}
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws Exception {
		if (phone == null || phone.trim().length() < 10 || phone.length() > 10) {
			throw new Exception("phone is invalid");
		}
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws Exception {
		if (address == null || address.trim().length() < 1 || address.length() > 30) {
			throw new Exception("address is invalid");
		}
		this.address = address;
	}

	public String getId() {
		return id;
		
	}
	
	private void setId(String id) throws Exception { // private method, so not accessible outside of class, i.e. immutable
		if (id == null || id.trim().length() < 1 || id.length() > 10) {
			throw new Exception("id is invalid");
		}
		this.id = id;
	}
	
	

}
