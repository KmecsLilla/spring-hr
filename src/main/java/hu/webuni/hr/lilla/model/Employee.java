package hu.webuni.hr.lilla.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String status;
	private int salary;
	private LocalDateTime startingToWork;

	@ManyToOne
	private Company company;

	public Employee(long id, String name, String status, int salary, LocalDateTime startingToWork) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.salary = salary;
		this.startingToWork = startingToWork;
	}

	public Employee() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
