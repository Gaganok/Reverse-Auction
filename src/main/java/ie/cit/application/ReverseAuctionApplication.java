package ie.cit.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
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

	/*
	@Autowired private JobRepository jobRepository;
	@PostConstruct
	public void test() {

	}*/
}
