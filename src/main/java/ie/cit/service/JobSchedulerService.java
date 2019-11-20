package ie.cit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class JobSchedulerService {
	@Autowired private ScheduledAnnotationBeanPostProcessor schedulePostProcessor;
	@Autowired private JobTask task;

	private final String TASK = "task";

	public void stop() { 
		schedulePostProcessor.postProcessBeforeDestruction(task,TASK); 
	}

	public void start() {
		schedulePostProcessor.postProcessAfterInitialization(task, TASK); 
	}

	@Component 
	class JobTask{
		
		@Autowired private JobManagerService jobService;
		@Value("${scheduler.days}") private long days;
		
		@Scheduled(fixedDelayString = "${scheduler.schedule}", initialDelay = 25000)
		@Retryable( 
				value = {Exception.class}, 
				maxAttempts = 3, 
				backoff	= @Backoff(delayExpression = "#{${scheduler.retry:5000}}"))
		public void task() throws Throwable{
			jobService.updateJobActivity(days);
		}
	}
}

