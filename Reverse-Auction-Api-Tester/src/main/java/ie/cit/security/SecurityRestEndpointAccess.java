package ie.cit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class SecurityRestEndpointAccess {

	@Autowired private RestTemplate restTemplate;
	@Autowired private HttpEntity<MultiValueMap<String, String>> authenticationForm;
	private boolean authenticated;
	
	private boolean restTemplateAuthenticate() {
		final String authenticationURL = "http://localhost:8080/login";

		try {
			ResponseEntity<String> str = restTemplate.postForEntity(authenticationURL, authenticationForm, String.class);
		} catch(Exception e) {
			return false;
		}

		return true;
	}

	public boolean authenticated() {
		if(authenticated)
			return true;
		else {
			authenticated = restTemplateAuthenticate();
			return authenticated;
		}
	}
	
	public void setAuthentication(boolean auth) {
		authenticated = auth;
	}
	
}
