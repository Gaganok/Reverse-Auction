package ie.cit.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ie.cit.model.Bid;
import ie.cit.model.Job;

@RestController
@RequestMapping("/rest")
public class RestControllerTT {
	
	@Autowired private RestTemplate restTemplate;
	
	@GetMapping(value="/home")
	public Set<Job> home() {
		String url = "http://localhost:8080/api/jobs";
		
		ResponseEntity<Set<Job>> responseEntity = 
				restTemplate.exchange(url, HttpMethod.GET, null, 
						new ParameterizedTypeReference<Set<Job>>() {});

		return responseEntity.getBody();
	}
	
	@GetMapping(value="/bids")
	public Set<Bid> bids(@RequestParam int userId) {
		String url = "http://localhost:8080/api/bids";
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("userId", userId);
		        
		ResponseEntity<Set<Bid>> responseEntity = 
				restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, 
						new ParameterizedTypeReference<Set<Bid>>() {});

		return responseEntity.getBody();
	}
}
