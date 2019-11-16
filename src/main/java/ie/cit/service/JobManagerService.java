package ie.cit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cit.model.Job;
import ie.cit.repository.JobRepository;

@Service
public class JobManagerService {
	
	@Autowired private JobRepository jobRepository;
	
	public Job addJob(Job job) {
		return jobRepository.save(job);
	}
	
	public void addBid() {}
	public void deleteJob() {}
	public void deleteBid() {}
	public void deactivateJob() {}

	public List<Job> findAll() {
		return jobRepository.findAll();
		
	}
	
}
