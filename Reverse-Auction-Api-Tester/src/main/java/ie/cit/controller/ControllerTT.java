package ie.cit.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import ie.cit.model.Bid;
import ie.cit.model.Job;
import ie.cit.security.SecurityRestEndpointAccess;

@Controller
public class ControllerTT {

	@Autowired private RestTemplate restTemplate;
	@Autowired private SecurityRestEndpointAccess access;

	@GetMapping("/jobs")
	public String jobs(Model model) {

		Set<Job> jobs =  new HashSet<Job>();
		if(access.authenticated()) {
			final String url = "http://localhost:8080/api/jobs";
			try {
				ResponseEntity<Set<Job>> responseEntity = 
						restTemplate.exchange(url, HttpMethod.GET, null, 
								new ParameterizedTypeReference<Set<Job>>() {});
				jobs = responseEntity.getBody();
			}catch(Exception e) {
				model.addAttribute("error_request_fail", true);
				access.setAuthentication(false);
			}
		} else 
			model.addAttribute("error_auth", true);

		model.addAttribute("jobs", jobs);
		return "jobs";
	}

	@RequestMapping("/bids")
	public String bids(@RequestParam int userId, Model model) {
		Set<Bid> bids =  new HashSet<Bid>();

		if(access.authenticated()) {
			String url = "http://localhost:8080/api/bids?userId=" + userId ;
			try {
				ResponseEntity<Set<Bid>> responseEntity = 
						restTemplate.exchange(url, HttpMethod.GET, null, 
								new ParameterizedTypeReference<Set<Bid>>() {});
				bids = responseEntity.getBody();
			}catch(Exception e) {
				model.addAttribute("error_request_fail", true);
				access.setAuthentication(false);
			}
		}else 
			model.addAttribute("error", true);

		model.addAttribute("bids", bids);

		return "bids";
	}


}
