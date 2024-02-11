package org.example.springprojmaven.job.impl;

import org.example.springprojmaven.job.Job;
import org.example.springprojmaven.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

	private List<Job> jobs = new ArrayList<>();
	private static int idCounter = 0;
	@Override
	public List<Job> findAll() {
		return jobs;
	}

	@Override
	public void createJob(Job job) {
		job.setId(idCounter++);
		jobs.add(job);
	}

	@Override
	public Job getJobById(int id) {
		for(Job job : jobs){
			if(job.getId() == id)
				return job;
		}
		return null;
	}

	@Override
	public boolean deleteJob(int id) {
		for(Job job : jobs){
			if(job.getId()==id) {
				jobs.remove(job);
				return true;
			}
		}
		return false;
	}
}
