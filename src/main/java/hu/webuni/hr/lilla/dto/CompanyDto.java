package hu.webuni.hr.lilla.dto;

import java.util.List;

public class CompanyDto {
	private long id;
	private long registrationNumber;
	private String name;
	private String address;

	private List<EmployeeDto> employees;

	public CompanyDto(long id, long registrationNumber, String name, String address, List<EmployeeDto> employees) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}

	public CompanyDto() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(long registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployeesOfCompany(List<EmployeeDto> employees) {
		this.employees = employees;
	}
}
