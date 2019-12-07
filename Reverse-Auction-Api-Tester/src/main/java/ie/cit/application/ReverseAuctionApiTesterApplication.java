package ie.cit.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"ie.cit.*"})
public class ReverseAuctionApiTesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReverseAuctionApiTesterApplication.class, args);
	}

}
