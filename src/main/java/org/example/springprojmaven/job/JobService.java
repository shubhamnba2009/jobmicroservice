package org.example.springprojmaven.job;

import java.util.List;

public interface JobService {
	List<Job> findAll();
	void createJob(Job job);
	Job getJobById(int id);
	boolean deleteJob(int id);
	boolean updateJob(int id, Job job);
}
