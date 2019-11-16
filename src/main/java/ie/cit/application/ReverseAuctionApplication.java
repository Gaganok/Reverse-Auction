package ie.cit.application;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import ie.cit.model.Bid;
import ie.cit.model.Job;
import ie.cit.model.Role;
import ie.cit.model.User;
import ie.cit.repository.BidRepository;
import ie.cit.repository.JobRepository;
import ie.cit.repository.RoleRepository;
import ie.cit.repository.UserRepository;



@SpringBootApplication(exclude = {
        //org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
        //,org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})
@ComponentScan(basePackages={"ie.cit.*"})
@EnableJpaRepositories(basePackages="ie.cit.repository")
@EntityScan(basePackages="ie.cit.model")
@EnableScheduling
public class ReverseAuctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReverseAuctionApplication.class, args);
	}

	@Autowired private UserRepository userRepository;
	@Autowired private RoleRepository roleRepository;
	@Autowired private BidRepository bidRepository;
	@Autowired private JobRepository jobRepository;
	
	@PostConstruct
	@Transactional
	public void test() {
		User user = new User("ole", "123");
		Role role = new Role("USER");
		role = roleRepository.save(role);
		user.setRole(role);
		user = userRepository.save(user);
		
		User user1 = new User("admin", "123");
		Role admin = new Role("ADMIN");
		role = roleRepository.save(admin);
		user1.setRole(admin, role);
		user1 = userRepository.save(user1);
		
		Job job = new Job("123", "123", user, null, true);
		job = jobRepository.save(job);
		Bid bid = new Bid(user1, job, 100.0f);
		bid = bidRepository.save(bid);
	}
}
