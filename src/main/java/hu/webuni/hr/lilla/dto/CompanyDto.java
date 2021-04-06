package hu.webuni.hr.lilla.dto;

import java.util.List;

public class CompanyDto {

	private long registerId;
	private String name;
	private String address;
	private List<EmployeeDto> employeesOfCompany;
	
//	private Address address;
//		"streetAddress": "21 2nd Street",
//		"city": "New York",
//		"state": "NY",
//		"postalCode": "10021"
	
	public CompanyDto() {
		
	}
	
	public CompanyDto(long registerId, String name, String address, List<EmployeeDto> employeesOfCompany) {
		super();
		this.registerId = registerId;
		this.name = name;
		this.address = address;
		this.employeesOfCompany = employeesOfCompany;
	}

	public long getRegisterId() {
		return registerId;
	}

	public void setRegisterId(long registerId) {
		this.registerId = registerId;
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

	public List<EmployeeDto> getEmployeesOfCompany() {
		return employeesOfCompany;
	}

	public void setEmployeesOfCompany(List<EmployeeDto> employeesOfCompany) {
		this.employeesOfCompany = employeesOfCompany;
	}
	
}



//Írj egy CompanyDto osztályt, a dto alpackage-be, amely egy céget reprezentál! 
//A cégről tudjuk a cégjegyzékszámát, a nevét és címét. 
//A cégnél dolgozó alkalmazottakat egy EmployeeDto lista tartalmazza.