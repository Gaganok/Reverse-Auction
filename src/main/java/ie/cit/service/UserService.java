package ie.cit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cit.model.User;
import ie.cit.repository.UserRepository;

@Service
public class UserService {
	@Autowired private UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User findUserByUserName(String userName) {
		return userRepository.findByEmail(userName);
	}
}