package hu.webuni.hr.lilla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.config.HrConfigurationProperties;
import hu.webuni.hr.lilla.model.Employee;

@Service
public class DefaultEmployeeService extends HrEmployeeService {

//	@Value("${hr.employeeaugment.def.percent0}")
//	private int defPercent;
	
//	@Override
//	public int getPayRaisePercent(Employee employee) {
//		return 5;
//	}
	
	@Autowired
	HrConfigurationProperties config;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getEmployeeAugment().getDef().getPercent();
	}

}


//Az EmployeeService-nek hozz létre két implementációs osztályát
//•Az egyiket DefaultEmployeeService néven, amely fixen 5%-os fizetésemelést ad bármelyik alkalmazottnak
//•A másikat SmartEmployeeService néven, amely figyelembe veszi, hogy mióta dolgozik az alkalmazott a cégnél:
//•Ha legalább 10 éve, akkor 10%
//•Ha legalább 5 éve, de mégnincs 10 éve, akkor5 %
//•Ha legalább 2 és fél éve, de még nincs5 éve, akkor2 %
//•Ha kevesebb, mint 2 és fél éve, akkor 0%