package hu.webuni.hr.lilla.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
@NamedEntityGraph(
		name = "Company.full",
		attributeNodes = @NamedAttributeNode("employees"))
@Entity
public class Company {
	@Id
	@GeneratedValue
	private Long id;

	private long registrationNumber;
	private String name;
	private String address;

	@OneToMany(mappedBy = "company"/*, cascade = {CascadeType.MERGE, CascadeType.PERSIST} */)
	private List<Employee> employees;

	@ManyToOne
	private CompanyType companyType;

	public Company(Long id, int registrationNumber, String name, String address, List<Employee> employees) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}

	public Company() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(int registrationNumber) {
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee employee) {
		if (this.employees == null) {
			this.employees = new ArrayList<>();
		}
		employees.add(employee);
		employee.setCompany(this);
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}


}
