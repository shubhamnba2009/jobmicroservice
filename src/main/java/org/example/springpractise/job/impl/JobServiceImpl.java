package org.example.springpractise.job.impl;

import org.example.springpractise.job.Job;
import org.example.springpractise.job.JobRepository;
import org.example.springpractise.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

	JobRepository jobRepository;

	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public List<Job> findAll() {
		return jobRepository.findAll();
	}

	@Override
	public void createJob(Job job) {
		jobRepository.save(job);
	}

	@Override
	public Job getJobById(Long id) {
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteJobById(Long id) {
		try {
			jobRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateJob(Long id, Job job) {
		Optional<Job> jobOptional = jobRepository.findById(id);

		if (jobOptional.isPresent()) {
			Job updateJob = jobOptional.get();
			updateJob.setDescription(job.getDescription());
			updateJob.setLocation(job.getLocation());
			updateJob.setTitle(job.getTitle());
			updateJob.setMaxSalary(job.getMaxSalary());
			updateJob.setMinSalary(job.getMinSalary());
			jobRepository.save(updateJob);
			return true;
		}
		return false;
	}
}
