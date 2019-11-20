package ie.cit.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ie.cit.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
	Set<Job> findAllByDescription(String description);
	Set<Job> findAllByActive(boolean active);
}
