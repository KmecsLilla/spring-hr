package hu.webuni.hr.lilla.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

public class EmployeeDto {
	private Long id;

	@NotEmpty
	private String name;

//	@NotEmpty  //Position kialakítása miatt kikerült
	private String status;

	@Positive
	private int salary;

	@Past
	private LocalDateTime startingToWork;

	private String companyName;

	public EmployeeDto(Long id, String name, String status, int salary, LocalDateTime startingToWork) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.salary = salary;
		this.startingToWork = startingToWork;
	}

	public EmployeeDto() {
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


}
