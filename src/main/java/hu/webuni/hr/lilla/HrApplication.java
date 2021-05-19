package hu.webuni.hr.lilla;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.service.InitDbService;
import hu.webuni.hr.lilla.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	@Autowired
	InitDbService initDbService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(salaryService.getRaisedSalary(new Employee(18L, "Eliza", 1_500_000, LocalDateTime.of(1999,4,1,1,1,1))));
		System.out.println(salaryService.getRaisedSalary(new Employee(14L, "Jani", 800_000, LocalDateTime.of(2002,12,1,1,1,1))));
		System.out.println(salaryService.getRaisedSalary(new Employee(12L, "Józsi", 600_000, LocalDateTime.of(2013,1,4,1,1,1))));
		System.out.println(salaryService.getRaisedSalary(new Employee(16L, "Pisti", 500_000, LocalDateTime.of(2017,3,5,1,1,1))));
		System.out.println(salaryService.getRaisedSalary(new Employee(20L, "Vilma", 400_000, LocalDateTime.of(2019,12,9,1,1,1))));

		initDbService.initDb();

		//salaryService.raiseMinimalSalary("fejleszto", 350000);
	}
}
