package org.example.springpractise.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

	JobService jobService;
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping
	public ResponseEntity<List<Job>> findAll(){
		return ResponseEntity.ok(jobService.findAll());
	}

	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job){
		jobService.createJob(job);
		return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id){
		Job job = jobService.getJobById(id);
		if(job == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(job);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJobById(@PathVariable Long id){
		boolean deleted = jobService.deleteJobById(id);
		if(deleted){
			return ResponseEntity.ok("Job Deleted Successfully");
		}
		return new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job){
		boolean updated = jobService.updateJob(id, job);
		if(updated){
			return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);
	}

}
