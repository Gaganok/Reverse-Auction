package ie.cit.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import ie.cit.service.CustomerUserDetailsService;

@Configuration 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired CustomerUserDetailsService userDetailsService;
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
	    return new PasswordEncoderTest();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		 .userDetailsService(userDetailsService) 
		 .passwordEncoder(passwordEncoder());
		
		/*auth.inMemoryAuthentication()
		.withUser("user").password("{noop}password").roles("USER")
		.and()
		.withUser("admin").password("{noop}123").roles("USER", "ADMIN");*/
	}

	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()	
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
			.logout()
			.logoutSuccessUrl("/login")
			.permitAll()
		.and().headers().frameOptions().disable()
		.and()
		.authorizeRequests().antMatchers("/**").hasAnyRole("USER", "ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/**").hasRole("ADMIN")
		.and()
		.authorizeRequests().antMatchers("/h2-console/**").hasRole("ADMIN");
	}

}
