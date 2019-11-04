package ie.cit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cit.model.Bid;

@Repository
public interface BidRepository extends CrudRepository<Bid, Long> {

}
