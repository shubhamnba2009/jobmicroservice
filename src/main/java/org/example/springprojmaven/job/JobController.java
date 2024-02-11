package org.example.springprojmaven.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

	private JobService jobService;

	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> findAll(){
		return ResponseEntity.ok(jobService.findAll());
	}

	@PostMapping("/jobs")
	public ResponseEntity<String> addJob(@RequestBody Job job){
		jobService.createJob(job);
		return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/jobs/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable int id){
		Job job = jobService.getJobById(id);
		if(job==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(job, HttpStatus.OK);
	}

	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable int id){
		if(jobService.deleteJob(id))
			return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
