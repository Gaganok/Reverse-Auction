package ie.cit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cit.model.User;
import ie.cit.repository.UserRepository;

@Service
public class UserService {
	@Autowired private UserRepository userRepository;
	
	public void createUser(User user) {
		userRepository.save(user);
		
	}
}
