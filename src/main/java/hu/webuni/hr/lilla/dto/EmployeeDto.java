package hu.webuni.hr.lilla.dto;

import java.time.LocalDateTime;

public class EmployeeDto {
	private long id;
	private String name;
	private int salary;
	private LocalDateTime startingToWork;
	
	public EmployeeDto(long id, String name, int salary, LocalDateTime startingToWork) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.startingToWork = startingToWork;
	}
	
	public EmployeeDto() {
	
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public LocalDateTime getStartingToWork() {
		return startingToWork;
	}
	
	public void setStartingToWork(LocalDateTime startingToWork) {
		this.startingToWork = startingToWork;
	}
}
