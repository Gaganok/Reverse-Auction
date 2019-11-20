package ie.cit.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ie.cit.model.User;
import ie.cit.repository.UserRepository;

@Service
@Transactional
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);
        if(user == null)
        	new UsernameNotFoundException("Email " + userName + " not found");
        UserDetails userDetails = org.springframework.security.core.userdetails.User
        		.withUsername(user.getEmail())
				.password(user.getPassword())
				.authorities(getAuthorities(user))
				.build();
        return userDetails;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }
}