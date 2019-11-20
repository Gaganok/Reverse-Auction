package ie.cit.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Job {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@JsonProperty("Job_User")
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@JsonBackReference
	@OneToMany(mappedBy = "job")
	private Set<Bid> bids = new HashSet<Bid>();

	@JsonProperty("Current_Lowest_Bid")
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bid_id", referencedColumnName = "id")
	private Bid lowestBid;

	@Column(name = "active")
	private boolean active = true;

	@Column(name = "date")
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

