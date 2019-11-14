package ie.cit.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

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
}	
