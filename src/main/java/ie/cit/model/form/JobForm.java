package ie.cit.model.form;

import java.time.LocalDate;
import java.util.Set;

import ie.cit.model.Bid;
import ie.cit.model.Job;
import ie.cit.model.User;

public class JobForm {
	
	private String name;
	private String description;
	private User user;
	public String getName() {
		return name;
	}
	
	private JobForm() {}
	
	public JobForm(String name, String description, User user) {
		super();
		this.name = name;
		this.description = description;
		this.user = user;
	}



	public String getDescription() {
		return description;
	}
	public User getUser() {
		return user;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return new Job(this.name, this.description, null, LocalDate.now(), null, true);
	}
	
	
}
