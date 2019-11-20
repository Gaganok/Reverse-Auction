package ie.cit.model.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserForm {
	     
    @NotNull
    @NotEmpty
    private String password;
    
    @NotNull
    @NotEmpty
    private String matchingPassword;
     
    @NotNull
    @NotEmpty
    private String email;
    
    @NotNull
    @NotEmpty
    private String name;
    
    @NotNull
    @NotEmpty
    private String surname;
    
    @NotNull
    @NotEmpty
    private String phone;

	public String getPassword() {
		return password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
