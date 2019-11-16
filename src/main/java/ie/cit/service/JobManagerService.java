package ie.cit.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cit.model.Bid;
import ie.cit.model.Job;
import ie.cit.model.User;
import ie.cit.repository.JobRepository;

@Service
public class JobManagerService {
	
	@Autowired private JobRepository jobRepository;
	
	public Job addJob(Job job) {
		return jobRepository.save(job);
	}
	
	public Set<Job> findAllJob(){
		Set<Job> jobSet = new HashSet<Job>();
		jobRepository.findAll().forEach(jobSet::add);
		return  jobSet;
	}
	
	public void updateBid(Long jobId, int bidValue, User user) throws Exception {
		Optional<Job> opt = jobRepository.findById(jobId);
		if(opt.isPresent()){
			Job job = opt.get();
			Bid bid = new Bid(user, job, bidValue);
			job.getBids().add(bid);
			job.setLowest_bid(bid);
			jobRepository.save(job);			
		} else
			throw new Exception("Can't find Job with Id: " + jobId);
		
	}
	
	public void addBid() {}
	public void deleteJob() {}
	public void deleteBid() {}
	public void deactivateJob() {}

	public Job findById(Long jobId) throws Exception {
		Optional<Job> opt = jobRepository.findById(jobId);
		if(opt.isPresent())
			return opt.get();
		throw new Exception("Can't find Job with id: " + jobId);
	}

	
	
}
