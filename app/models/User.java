package models;

import javax.persistence.*;
//import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User_TABLE")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min = 3, max = 10)
	@Column(length = 10)
	private String firstName;

	@Size(min = 3, max = 20)
	@Column(length = 20)
	private String lastName;

	//@Email
	@NotNull
	@Column(length = 30)
	private String email;
	
	@NotNull
	private String password;

	@Size(min = 4, max = 17)
	@Column(length = 17)
	private String phone;
	
	
	//getters and setters
	
	public Long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}