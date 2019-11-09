package ie.cit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@RestController
@RequestMapping*/
@Controller
public class LoginController {
	/*
	 * @RequestMapping(value="/") public String hello() { return "Hello"; }
	 */
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
}
