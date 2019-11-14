package ie.cit.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ie.cit.model.Bid;
import ie.cit.model.User;
import ie.cit.repository.BidRepository;
import ie.cit.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class RestControllerTT {
	
	@Autowired private BidRepository bidRepository;
	@Autowired private UserRepository userRepository;
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping("/bids")
	public String getAllBids() throws JsonGenerationException, JsonMappingException, IOException {
		return parseJson(bidRepository.findAll());	
	}
	
	@RequestMapping("/bidsByUserId")
	public String getBidsByUser(@RequestParam long userId) throws JsonGenerationException, JsonMappingException, IOException {
		Optional opt = userRepository.findById(userId);
		String result = "";
		if(opt.isPresent()) {
			User user = (User) opt.get();
			return parseJson(bidRepository.findAllByUser(user));
		} else 
			return "No Such user!!";
	}
	
	//Move Somewhere
	private String parseJson(Iterable<Bid> bidsItr) throws JsonGenerationException, JsonMappingException, IOException {	
		List<Bid> bids = new ArrayList<Bid>();
	    bidsItr.forEach(bids::add);
	    
	    final ByteArrayOutputStream out = new ByteArrayOutputStream();
	    final ObjectMapper mapper = new ObjectMapper();
	    
	    mapper.writeValue(out, bids);
	    
	    return new String(out.toByteArray());
	}
}
