package ie.cit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ie.cit.model.User;
import ie.cit.model.UserForm;
import ie.cit.service.JobManagerService;
import ie.cit.service.UserService;

@Controller
@RequestMapping
public class ControllerTT {
	
	@Autowired private JobManagerService jobService;
	@Autowired private UserService userService;
	
	@RequestMapping(value="/login")
	public String login(Model model) {
		return "login";
	}	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView  showRegistrationPage(ModelAndView modelAndView, UserForm userForm) {
		modelAndView.addObject("user", userForm);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String processRegistrationForm(ModelAndView modelAndView, UserForm userForm) {
		User user = new User(userForm);
		userService.createUser(user);
		return "login";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
}
