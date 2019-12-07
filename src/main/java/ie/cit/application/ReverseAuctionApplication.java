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
import ie.cit.repository.JobRepository;
import ie.cit.repository.RoleRepository;
import ie.cit.repository.UserRepository;



@SpringBootApplication
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
	@Autowired private JobRepository jobRepository;
	
	@PostConstruct
	@Transactional
	public void test() {
		User user = new User("ole", "123", "oleh", "bai", "88888");
		Role role = new Role("USER");
		role = roleRepository.save(role);
		user.setRole(role);
		user = userRepository.save(user);
		
		User user1 = new User("admin", "123", "name", "surname", "004343");
		Role admin = new Role("ADMIN");
		role = roleRepository.save(admin);
		user1.setRole(admin, role);
		user1 = userRepository.save(user1);
		
		Job job = new Job("123", "123", user);
		Bid bid = new Bid(user1, job, 100.0f);
		job.addBid(bid);
		job = jobRepository.save(job);
		
		Job job1 = new Job("Rabota", "Opesanie", user);
		Bid bid1 = new Bid(user1, job1, 100.0f);
		job1.setActive(false);
		job1.addBid(bid1);
		job1 = jobRepository.save(job1);
	}
}
