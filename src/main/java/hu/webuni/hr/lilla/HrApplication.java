package hu.webuni.hr.lilla;

import java.time.LocalDateTime;
// When using @ConfigurationProperties it is recommended to add 'spring-boot-configuration-processor' to your classpath to generate configuration metadata

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

import hu.webuni.hr.lilla.model.Employee;
//import hu.webuni.hr.lilla.service.EmployeeService;
import hu.webuni.hr.lilla.service.SalaryService;
//import hu.webuni.hr.lilla.service.SmartEmployeeService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {
	
	@Autowired
	SalaryService salaryService;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(salaryService.getRaisedSalary(new Employee(18, "Eliza", 700000, LocalDateTime.of(1999,4,1,1,1,1))));
		System.out.println(salaryService.getRaisedSalary(new Employee(14, "Jani", 600000, LocalDateTime.of(2002,12,1,1,1,1))));
		System.out.println(salaryService.getRaisedSalary(new Employee(12, "Józsi", 500000, LocalDateTime.of(2013,1,4,1,1,1))));
		System.out.println(salaryService.getRaisedSalary(new Employee(16, "Pisti", 400000, LocalDateTime.of(2017,3,5,1,1,1))));
		System.out.println(salaryService.getRaisedSalary(new Employee(20, "Vilma", 300000, LocalDateTime.of(2019,12,9,1,1,1))));
		
	}
	
//	@Bean 
//	public EmployeeService employeeService() {
//		return new SmartEmployeeService();
//	}

}


//Implementáld a main osztályban a CommandLineRunnerinterfészt, és a run metódusban hívd meg a SalaryService metódusát 
//néhány teszt alkalmazottal, majd írd ki a módosított fizetéseket. 
//Mit tapasztalsz? -> 2 lehetséges bean közül nem tud választani a konstruktor, jelölést kér
