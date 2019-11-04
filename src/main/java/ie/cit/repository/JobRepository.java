package ie.cit.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cit.model.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
	Set<Job> findAllPleaseByDescription(String description);
}
