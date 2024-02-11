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
}
