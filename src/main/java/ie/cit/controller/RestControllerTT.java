package ie.cit.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ie.cit.model.Bid;
import ie.cit.model.Job;
import ie.cit.model.User;
import ie.cit.repository.BidRepository;
import ie.cit.repository.UserRepository;
import ie.cit.service.JobManagerService;

@RestController
@RequestMapping("/api")
public class RestControllerTT {
	
	@Autowired private JobManagerService jobService;
	@Autowired private BidRepository bidRepository;
	@Autowired private UserRepository userRepository;
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping(value = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<Job> getActiveJobs(){
		return jobService.getActiveJobs();
	}
	
	@RequestMapping("/bids")
	public Set<Bid> getBidsByUser(@RequestParam long userId) throws Exception{
		Optional<User> opt = userRepository.findById(userId);
		
		if(opt.isPresent())
			return bidRepository.findAllByUser(opt.get());
		throw new Exception("No user with id: " + userId);
	}
}
