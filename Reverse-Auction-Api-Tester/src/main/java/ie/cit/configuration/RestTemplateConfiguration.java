package ie.cit.configuration;

import java.nio.charset.StandardCharsets;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		/*RestTemplate restTemplate = builder
				//.rootUri("http://localhost:8080/api/")
				.basicAuthentication("admin", "123", StandardCharsets.UTF_8)
				.build();*/
		final RestTemplate restTemplate = new RestTemplate();
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(); 
		factory.setHttpClient(HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build());
		restTemplate.setRequestFactory(factory);
		
		restTemplate.getMessageConverters()
        	.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("username", "admin");
		map.add("password", "123");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> str = restTemplate.postForEntity("http://localhost:8080/login", request, String.class);
		
	    return restTemplate;
	}
}
