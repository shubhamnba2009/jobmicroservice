package org.example.springprojmaven.job;

public class Job {
	private int id;
	private String title;
	private String description;
	private String maxSalary;
	private String minSalary;
	private String location;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}

	public String getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Job(int id, String title, String description, String maxSalary, String minSalary, String location) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.maxSalary = maxSalary;
		this.minSalary = minSalary;
		this.location = location;
	}
}
