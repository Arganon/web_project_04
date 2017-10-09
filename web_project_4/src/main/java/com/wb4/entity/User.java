package com.wb4.entity;

import java.time.LocalDate;

import com.wb4.enums.ClientType;
import com.wb4.enums.UserRole;

public class User {
	protected Integer id;
	protected final String firstName;
	protected final String middleName;
	protected final String lastName;
	protected String telephone;
	protected String email;
	protected String login;
	protected String password;
	protected LocalDate createTime; // have some questions about this field
	
	protected ClientType clientType;
	protected UserRole userRole;
	
	public static class Builder {
		protected Integer id = null;
		protected String firstName = null;
		protected String middleName = null;
		protected String lastName = null;
		protected String telephone = null;
		protected String email = null;
		protected String login = null;
		protected String password = null;
		
		protected ClientType clientType;
		protected UserRole userRole;
		
		public Builder(String login, String password) {
			this.login = login;
			this.password = password;
		}
		
		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}
		
		public Builder setUserRole(UserRole userRole) {
			this.userRole = userRole;
			return this;
		}
		
		public Builder setTelephone(String telephone) {
			this.telephone = telephone;
			return this;
		}
		
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setMiddleName(String middleName) {
			this.middleName = middleName;
			return this;
		}
		
		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder setClientType(ClientType clientType) {
			this.clientType = clientType;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
	
	protected User(Builder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.middleName = builder.middleName;
		this.lastName = builder.lastName;
		this.telephone = builder.telephone;
		this.email = builder.email;
		this.login = builder.login;
		this.password = builder.password;
		this.clientType = builder.clientType;
		this.userRole = builder.userRole;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public ClientType getClientType() {
		return clientType;
	}
	
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	
	public LocalDate getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDate createTime) {
		this.createTime = createTime;
	}

	@SuppressWarnings("null")
	public String toString() {
		StringBuffer info = null;
		
		info.append("User first name: " + firstName + "\n" +
					  "User middle name: " + middleName + "\n" +
					  "User last name: " + lastName + "\n" +
					  "User telephone: " + telephone + "\n" +
					  "User email: " + email + "\n" +
					  "User login: " + login + "\n" +
					  "User type: " + clientType + "\n" +
					  "User role: " + userRole + "\n" +
					  "Registration date: " + createTime + "\n\n");
		
		return info.toString();
	}
}
