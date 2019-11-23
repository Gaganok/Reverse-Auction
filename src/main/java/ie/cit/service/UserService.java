package ie.cit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cit.model.Role;
import ie.cit.model.User;
import ie.cit.repository.RoleRepository;
import ie.cit.repository.UserRepository;

@Service
public class UserService {
	@Autowired private UserRepository userRepository;
	@Autowired private RoleRepository roleRepository;
	
	
	
	public User createUser(User user) {
		Optional<Role> opt = roleRepository.findByName("USER");
		Role role = opt.isPresent() ? opt.get() : new Role("USER");
		user.setRole(role);
		return userRepository.save(user);
	}
	
	public User findUserByUserName(String userName) {
		return userRepository.findByEmail(userName);
	}
}
