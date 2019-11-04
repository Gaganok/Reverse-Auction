package ie.cit.application;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import ie.cit.model.Job;
import ie.cit.repository.JobRepository;


@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@ComponentScan(basePackages={"ie.cit.*"})
@EnableJpaRepositories(basePackages="ie.cit.repository")
@EntityScan(basePackages="ie.cit.model")
@EnableScheduling

public class ReverseAuctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReverseAuctionApplication.class, args);
	}
	
	@Autowired private JobRepository jobRepository;
	
	@PostConstruct
	public void test() {
		
	}
}
