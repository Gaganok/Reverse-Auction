package ie.cit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bid {
	
	@Id
	@GeneratedValue
	private long id;
	
	private Bid() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
