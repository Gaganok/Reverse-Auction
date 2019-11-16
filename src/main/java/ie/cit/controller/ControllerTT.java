package ie.cit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ie.cit.model.Job;
import ie.cit.model.User;
import ie.cit.model.form.JobForm;
import ie.cit.model.form.UserForm;
import ie.cit.service.JobManagerService;
import ie.cit.service.UserService;

@Controller
@RequestMapping
public class ControllerTT {
	
	@Autowired private JobManagerService jobService;
	@Autowired private UserService userService;
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}	
	
	@RequestMapping(value="/")
	public ModelAndView forwardHome() {
		return new ModelAndView("forward:/home");
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView  showRegistrationPage(ModelAndView modelAndView, UserForm userForm) {
		modelAndView.addObject("user", userForm);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String processRegistrationForm(UserForm userForm) {
		User user = new User(userForm);
		userService.createUser(user);
		return "login";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(Model model, Authentication authentication) {
		model.addAttribute("userName", authentication.getName().toString());
		return "home";
	}
	
	@RequestMapping(value = "/job", method = RequestMethod.GET)
	public ModelAndView job(ModelAndView modelAndView, JobForm jobForm) {
		modelAndView.addObject("job", jobForm);
		modelAndView.setViewName("job");
		return modelAndView;
	}
	
	@RequestMapping(value = "/job", method = RequestMethod.POST)
	public String process_job(JobForm jobForm, Authentication auth) {
		Job job = jobForm.getJob();
		User user = userService.findUserByUserName(auth.getName().toString());
		job.setUser(user);
		jobService.addJob(job);
		return "home";
	}
	
	@RequestMapping(value="/jobs", method = RequestMethod.GET)
	public String returnJobs(Model model) {
		model.addAttribute("jobs", jobService.findAll());
		return "jobs";
	}
	
}
