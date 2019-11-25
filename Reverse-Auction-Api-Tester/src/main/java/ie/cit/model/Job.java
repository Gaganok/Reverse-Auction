package ie.cit.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public class Job {

	private long id;
	private String name;

	private String description;

	@JsonProperty("jobUser")
	private User user;

	@JsonProperty("bids")
	@JsonIgnoreProperties("job")
	private Set<Bid> bids = new HashSet<Bid>();

	@JsonProperty("lowestBid")
	@JsonIgnoreProperties("job")
	private Bid lowestBid;

	private boolean active = true;

	private LocalDate time = LocalDate.now();

	public Job() {}

	public Job(String name, String description, User user) {
		this.name = name;
		this.description = description;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Bid getLowestBid() {
		return lowestBid;
	}

	public void setLowestBid(Bid lowestBid) {
		this.lowestBid = lowestBid;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	public void addBid(Bid bid) {
		lowestBid = bid;
		bids.add(bid);
	}
}

