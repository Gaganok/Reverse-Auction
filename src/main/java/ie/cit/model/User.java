package ie.cit.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ie.cit.model.form.UserForm;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;	

	@Column(name = "phone")
	private String phone;

	@JsonIgnore
	@Column(name = "password")
	private String password;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable( 
			name = "users_roles", 
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id")) 
	private Collection<Role> roles;

	private User() {}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public User(String email, String password, String name, String surname, String phone) {
		this(email, password);
		this.name = name;
		this.surname = surname;
		this.phone = phone;
	}

	public User(UserForm userForm) {
		this.email = userForm.getEmail();
		this.password = userForm.getPassword();
		this.name = userForm.getName();
		this.surname = userForm.getSurname();
		this.phone = userForm.getPhone();
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
	
	public void setRole(Role role) {
		roles = new ArrayList<Role>();
		roles.add(role);
	}
	
	public void setRole(Role... roles) {
		this.roles = new ArrayList<Role>();
		for( Role role : roles)
			this.roles.add(role);
	}
	
	public void setRole(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public Collection<Role> getRoles(){
		return roles;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phoneNumber) {
		this.phone = phoneNumber;
	}
}	
