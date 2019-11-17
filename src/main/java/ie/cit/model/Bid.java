package ie.cit.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Bid {
	
	@Id
	@GeneratedValue
	private long id;
	
	@JsonProperty("Bid_User")
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@JsonProperty("Job")
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;
	
	@JsonProperty("Bid_Value")
	@Column(name = "value")
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
