package hu.webuni.hr.lilla.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.config.HrConfigurationProperties;
import hu.webuni.hr.lilla.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService {

//	@Value("${hr.employeeaugment.smart3.limit}")
//	private int limit3;
//	
//	@Value("${hr.employeeaugment.smart2.limit}")
//	private int limit2;
//	
//	@Value("${hr.employeeaugment.smart1.limit}")
//	private int limit1;
	
//	@Value("${hr.employeeaugment.smart3.percent}")
//	private int percent3;
//	
//	@Value("${hr.employeeaugment.smart2.percent}")
//	private int percent2;
//	
//	@Value("${hr.employeeaugment.smart1.percent}")
//	private int percent1;
	
	
	@Autowired
	HrConfigurationProperties config;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		LocalDate startOfWorkDate = employee.getStartingToWork().toLocalDate();
		LocalDate now = LocalDate.now();
		Period period = Period.between(startOfWorkDate, now);
		//System.out.println(period = Period.between(startOfWorkDate, now));
		int monthsPassed = 12 * period.getYears() + period.getMonths();
		if (monthsPassed >= config.getEmployeeAugment().getSmart3().getLimit()) {
			return config.getEmployeeAugment().getSmart3().getPercent();
		} else if (monthsPassed >= config.getEmployeeAugment().getSmart2().getLimit()) {
			return config.getEmployeeAugment().getSmart2().getPercent();
		} else if (monthsPassed >= config.getEmployeeAugment().getSmart1().getLimit()) {
			return config.getEmployeeAugment().getSmart1().getPercent();
		} else {
			return 0;
		}
	
	
//	@Override
//	public int getPayRaisePercent(Employee employee) {
//		LocalDate startOfWorkDate = employee.getStartingToWork().toLocalDate();
//		LocalDate now = LocalDate.now();
//		Period period = Period.between(startOfWorkDate, now);
//		//System.out.println(period = Period.between(startOfWorkDate, now));
//		int monthsPassed = 12 * period.getYears() + period.getMonths();
//		if (monthsPassed >= 120) {
//			return 10;
//		} else if (monthsPassed >= 60) {
//			return 5;
//		} else if (monthsPassed >= 30) {
//			return 2;
//		} else {
//			return 0;
//		}
		
		
		
//		Period period = getPeriod(employee);
//		Duration duration = Duration.between(employee.getStartingToWork(), LocalDateTime.now());
//		int time = duration.to;
//		if (period.getMonths() >= 120) {
//			return 10;
//		}
//		else if (time >= 60 && period.getMonths() < 120) {
//			return 5;
//		}
//		else if (period.getMonths() >= 30 && period.getMonths() < 60) {
//			return 2;
//		}
//		return 0;
	}
	
//	private static Period getPeriod(Employee employee) {
//		return Period.between(LocalDateTime.now(), employee.getStartingToWork());
//	}

}

//private static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
//return Period.between(dob.toLocalDate(), now.toLocalDate());
//Az EmployeeService-nek hozz létre két implementációs osztályát
//•Az egyiket DefaultEmployeeService néven, amely fixen 5%-os fizetésemelést ad bármelyik alkalmazottnak
//•A másikat SmartEmployeeService néven, amely figyelembe veszi, hogy mióta dolgozik az alkalmazott a cégnél:
//•Ha legalább 10 éve, akkor 10%
//•Ha legalább 5 éve, de mégnincs 10 éve, akkor5 %
//•Ha legalább 2 és fél éve, de még nincs5 éve, akkor2 %
//•Ha kevesebb, mint 2 és fél éve, akkor 0%