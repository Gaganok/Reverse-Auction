package ie.cit.model;

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
}
