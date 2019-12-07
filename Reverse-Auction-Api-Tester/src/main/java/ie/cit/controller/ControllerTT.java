package ie.cit.controller;

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

@Controller
public class ControllerTT {

	@Autowired private RestTemplate restTemplate;

	@GetMapping("/jobs")
	public String jobs(Model model) {
		String url = "http://localhost:8080/api/jobs";

		ResponseEntity<Set<Job>> responseEntity = 
				restTemplate.exchange(url, HttpMethod.GET, null, 
						new ParameterizedTypeReference<Set<Job>>() {});
		model.addAttribute("jobs", responseEntity.getBody());
		
		return "jobs";
	}
	
	@RequestMapping("/bids")
	public String bids(@RequestParam int userId, Model model) {
		String url = "http://localhost:8080/api/bids?userId=" + userId ;

		ResponseEntity<Set<Bid>> responseEntity = 
				restTemplate.exchange(url, HttpMethod.GET, null, 
						new ParameterizedTypeReference<Set<Bid>>() {});
		model.addAttribute("bids", responseEntity.getBody());
		
		return "bids";
	}
}
