package hu.webuni.hr.lilla.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class CompanyDto {
	
	@JsonView(Views.BaseData.class)
	private long id;
	
	@JsonView(Views.BaseData.class)
	private long registrationNumber;
	
	@JsonView(Views.BaseData.class)
	private String name;
	
	@JsonView(Views.BaseData.class)
	private String address;
	
	private List<EmployeeDto> employees;
	
	public CompanyDto(long id, long registrationNumber, String name, String address, List<EmployeeDto> employees) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}
		
//	public CompanyDto(long id, long registrationNumber, String name, String address) {
//	super();
//	this.id = id;
//	this.registrationNumber = registrationNumber;
//	this.name = name;
//	this.address = address;
//	}
	
//	private Address address;
//		"streetAddress": "21 2nd Street",
//		"city": "New York",
//		"state": "NY",
//		"postalCode": "10021"
	
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



//Írj egy CompanyDto osztályt, a dto alpackage-be, amely egy céget reprezentál! 
//A cégről tudjuk a cégjegyzékszámát, a nevét és címét. 
//A cégnél dolgozó alkalmazottakat egy EmployeeDto lista tartalmazza.