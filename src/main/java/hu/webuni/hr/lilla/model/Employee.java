package hu.webuni.hr.lilla.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	//private String status;
	private int salary;
	private LocalDateTime startingToWork;

	@ManyToOne
	private Company company;
	@ManyToOne//(fetch = FetchType.EAGER)
	private Position position;

	@OneToMany(mappedBy = "employee")
	private List<HolidayRequest> holidayRequests;

	@ManyToOne
	private Employee manager;

	@OneToMany(mappedBy = "manager")
	private List<Employee> managedEmployees;

	public Employee(Long id, String name, int salary, LocalDateTime startingToWork) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.startingToWork = startingToWork;
	}

	public Employee() {
	}

	public Long getId() {
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<HolidayRequest> getHolidayRequests() {
		return holidayRequests;
	}

	public void setHolidayRequests(List<HolidayRequest> holidayRequests) {
		this.holidayRequests = holidayRequests;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getManagedEmployees() {
		return managedEmployees;
	}

	public void setManagedEmployees(List<Employee> managedEmployees) {
		this.managedEmployees = managedEmployees;
	}

	public void addHolidayRequest(HolidayRequest holidayRequest) {
		if (this.holidayRequests == null) {
			this.holidayRequests = new ArrayList<>();
		}
		this.holidayRequests.add(holidayRequest);
		holidayRequest.setEmployee(this);
	}

}
