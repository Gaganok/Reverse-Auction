package ie.cit.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ie.cit.model.Bid;
import ie.cit.model.User;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
	Set<Bid> findAllByUser(User user);
}
