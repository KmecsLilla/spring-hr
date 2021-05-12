package hu.webuni.hr.lilla.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String status;
	private int salary;
	//@Temporal(TemporalType.TIMESTAMP)
	//@Basic
	private LocalDateTime startingToWork;
	
	public Employee(long id, String name, String status, int salary, LocalDateTime startingToWork) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.salary = salary;
		this.startingToWork = startingToWork;
	}
	
	public Employee() {
		super();
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
	
	
}




//Hozz létre egy alkalmazottat reprezentáló osztályt a root package model alpackage-ében, Employeenéven! 
//Az Employee-ről tudjuk a cégen belüli azonosítóját (Long), a nevét, a beosztását, a havi fizetését (egész szám), 
//és hogy mikor kezdett dolgozni a cégnél (LocalDateTime). Ezekre gettereket, settereketis definiálj!
//Célszerű lehet konstruktort is írni hozzá.
