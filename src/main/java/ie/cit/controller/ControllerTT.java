package ie.cit.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value="/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String login(Model model) {	
		return "login";
	}	
	
	@RequestMapping(value="/home")
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
		Set<Job> jobSet = jobService.getActiveJobs();
		Set<Job> inactJobSet = jobService.getInactiveJobs();
		model.addAttribute("activeJobs", jobSet);
		model.addAttribute("inactiveJobs", inactJobSet);
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
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/bid", method = RequestMethod.POST)
	public String bid(@RequestParam long jobId, @RequestParam int bidValue, Authentication auth) throws Exception {
		User user = userService.findUserByUserName(auth.getName().toString());
		jobService.updateBid(jobId, bidValue, user);
		return "redirect:/home";
	}
	
	@PostMapping("/jobdetails")
	public String jobDetails(Model model, @RequestParam long jobId) throws Exception {
		Job job = jobService.findById(jobId);
		model.addAttribute("job", job);
		return "jobdetails";
	}
	
	/*@GetMapping("/logout")
	public String getLogoutPage(Authentication auth){
	
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null)
	        new SecurityContextLogoutHandler().logout(request, response, authentication);
	    
	    return "redirect:/login";
	}*/
}
