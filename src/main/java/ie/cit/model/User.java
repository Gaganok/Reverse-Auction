package ie.cit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	private User() {}
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User(UserForm userForm) {
		this.email = userForm.getEmail();
		this.password = userForm.getPassword();
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}	
