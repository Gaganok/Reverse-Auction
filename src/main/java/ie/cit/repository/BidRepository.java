package ie.cit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cit.model.Bid;
import ie.cit.model.User;

@Repository
public interface BidRepository extends CrudRepository<Bid, Long> {
	Iterable<Bid> findAllByUser(User user);
}
