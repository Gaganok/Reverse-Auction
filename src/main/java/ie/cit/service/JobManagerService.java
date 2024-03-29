package ie.cit.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
		return new HashSet<Job>(jobRepository.findAll());
	}

	public void updateBid(Long jobId, int bidValue, User user) throws Exception {
		Optional<Job> opt = jobRepository.findById(jobId);
		if(opt.isPresent()){
			Job job = opt.get();
			if(job.getLowestBid() != null && job.getLowestBid().getValue() <= bidValue || bidValue <=0) 
				throw new Exception("User Bid is bigger or equal to the lowest bid for the Job: " + jobId);
			if(job.getUser().getEmail().equals(user.getEmail()))
				throw new Exception("User can't bid on his own job!");
			Bid bid = new Bid(user, job, bidValue);
			job.addBid(bid);
			jobRepository.save(job);			
		} else
			throw new Exception("Can't find Job with Id: " + jobId);
	}

	public Job findById(Long jobId) throws Exception {
		Optional<Job> opt = jobRepository.findById(jobId);
		if(opt.isPresent())
			return opt.get();
		throw new Exception("Can't find Job with id: " + jobId);
	}

	public Set<Job> getActiveJobs() {
		return jobRepository.findAllByActive(true);
	}
	
	public Set<Job> getInactiveJobs(){
		return jobRepository.findAllByActive(false);
	}
	
	public void updateJobActivity(long days) {
		LocalDate now = LocalDate.now();
		getActiveJobs().forEach(job -> {
			if(ChronoUnit.DAYS.between(job.getTime(), now) >= days) {
				job.setActive(false);
				jobRepository.save(job);
			}
		});
	}
}
