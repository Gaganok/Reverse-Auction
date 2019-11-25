package ie.cit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bid {
	
	private long id;
	
	@JsonProperty("bidUser")
	private User user;
	
	@JsonProperty("job")
	@JsonIgnoreProperties({"bids", "lowestBid"})
	private Job job;
	
	@JsonProperty("bidValue")
	private float value;
	
	public Bid() {}
	
	public Bid(User user, Job job, float value) {
		super();
		this.user = user;
		this.job = job;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
}
