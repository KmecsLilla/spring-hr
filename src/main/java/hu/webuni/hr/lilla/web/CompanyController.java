package hu.webuni.hr.lilla.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import hu.webuni.hr.lilla.dto.CompanyDto;
import hu.webuni.hr.lilla.dto.EmployeeDto;
import hu.webuni.hr.lilla.dto.Views;
import hu.webuni.hr.lilla.mapper.CompanyMapper;
import hu.webuni.hr.lilla.mapper.EmployeeMapper;
import hu.webuni.hr.lilla.model.Company;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.service.HrCompanyService;
import hu.webuni.hr.lilla.service.HrEmployeeService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
//2	private Map<Long, CompanyDto> allCompany = new HashMap<>();
//	
//	{
//		List<EmployeeDto> employeesOfCompany1 = new ArrayList<>();
//		employeesOfCompany1.add(new EmployeeDto(1L,"Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)));
//		employeesOfCompany1.add(new EmployeeDto(2L, "Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1)));
//		
//		List<EmployeeDto> employeesOfCompany2 = new ArrayList<>();
//		employeesOfCompany2.add(new EmployeeDto(1L, "Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)));
//		employeesOfCompany2.add(new EmployeeDto(2L, "Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1)));
//			
//		allCompany.put(1L, new CompanyDto(1L, 1L, "Libretto", "Budapest", employeesOfCompany1));
//		allCompany.put(2L, new CompanyDto(2L, 2L, "Librarius", "Miskolc", employeesOfCompany2));
//
////1		allCompany.put(1L, new CompanyDto(1L, 1L, "Libretto", "Budapest", List.of(new EmployeeDto(1L,"Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)), (new EmployeeDto(2L, "Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1))))));
////1		allCompany.put(2L, new CompanyDto(2L, 2L, "Librarius", "Miskolc", List.of(new EmployeeDto(1L, "Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)), (new EmployeeDto(2L, "Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1))))));
//	}

	@Autowired
	HrCompanyService hrCompanyService;
	
	@Autowired
	HrEmployeeService hrEmployeeService;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
//1  Kiegészítés:spéci JSON-serializáció
//	@GetMapping(params = "full=true")
//	public List<CompanyDto> getCompanies() {
//		return new ArrayList<>(allCompany.values());
//	}
//		
//	
//1	@GetMapping
//	@JsonView(Views.BaseData.class)
//	public List<CompanyDto> getCompaniesWithBaseData(@RequestParam(required = false) Boolean full) {
//		return getCompanies();
//	}
	
//1	Alap "full" nélkül:	
//	@GetMapping
//	public List<CompanyDto> getAll() {
//		return new ArrayList<>(allCompany.values());
//	}
	
//2	@GetMapping
//	public List<CompanyDto> getAllCompany(@RequestParam(required = false) Boolean full) {
//		if (full != null && full) {
//			return new ArrayList<>(allCompany.values());
//		} else {
//			return allCompany.values().stream()
//					.map(mapToCompanyWithoutEmployees())
//					.collect(Collectors.toList());
//		}
//	}
//
//	private Function<? super CompanyDto, ? extends CompanyDto> mapToCompanyWithoutEmployees() {
//		return c -> new CompanyDto(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null);
//	}
	
	@GetMapping
	public List<CompanyDto> getAllCompany(@RequestParam(required = false) Boolean full) {
		if (full != null && full) {
			return companyMapper.allCompanyToCompanyDtos(hrCompanyService.findAll());
		} else {
			return companyMapper.allCompanyToCompanyDtos(hrCompanyService.findAll().stream()
					.map(mapToCompanyWithoutEmployees())
					.collect(Collectors.toList()));
		}
	}

	private Function<? super Company, ? extends Company> mapToCompanyWithoutEmployees() {
		return c -> new Company(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null);
	}
		
//1	@GetMapping("/{registrationNumber}")
//	public ResponseEntity<CompanyDto> getCompanyByRegistrationNumber(@PathVariable long registrationNumber) {
//		CompanyDto companyDto = allCompany.get(registrationNumber);
//		if (companyDto != null) {
//			return ResponseEntity.ok(companyDto);
//		}
//		else {
//			return ResponseEntity.notFound().build();
//		}
//	}
	
//1	Alap "full" nélkül:
//  @GetMapping("/{registrationNumber}")
//	public CompanyDto getCompanyByRegistrationNumber(@PathVariable long registrationNumber) {	
//		return findByIdOrThrow(registrationNumber);
//	}
	
//2	@GetMapping("/{registrationNumber}")
//	public CompanyDto getCompanyByRegistrationNumber(@PathVariable long registrationNumber, @RequestParam(required = false) Boolean full) {	
//		CompanyDto companyDto = findByIdOrThrow(registrationNumber);
//		if (full != null && full) {
//			return companyDto;
//		} else {
//			return mapToCompanyWithoutEmployees().apply(companyDto);
//		}
//		
//	}
	
	@GetMapping("/{registrationNumber}")
	public CompanyDto getCompanyByRegistrationNumber(@PathVariable long registrationNumber, @RequestParam(required = false) Boolean full) {	
		Company company = findByIdOrThrow(registrationNumber);
		if (full != null && full) {
			return companyMapper.companyToDto(company);
		} else {
			return companyMapper.companyToDto(mapToCompanyWithoutEmployees().apply(company));
		}
		
	}
	
//2	@PostMapping
//	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
//		allCompany.put(companyDto.getRegistrationNumber(), companyDto);
//		return companyDto;
//	}
	
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		Company company = companyMapper.dtoToCompany(companyDto);
		hrCompanyService.save(company);
		return companyMapper.companyToDto(company);
	}
	
//2	@PutMapping("/{registrationNumber}")
//	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long registrationNumber, @RequestBody CompanyDto companyDto) {
//		if (!allCompany.containsKey(registrationNumber)) {
//			return ResponseEntity.notFound().build();
//		} else {
//			companyDto.setRegistrationNumber(registrationNumber);
//			allCompany.put(registrationNumber, companyDto);
//			return ResponseEntity.ok(companyDto);
//		}	
//	}
	
	
	
	@PutMapping("/{registrationNumber}")
	public CompanyDto modifyCompany(@PathVariable long registrationNumber, @RequestBody CompanyDto companyDto) {
		Company company= findByIdOrThrow(registrationNumber);
		company.setRegistrationNumber(registrationNumber);
		hrCompanyService.modify(company);
		return companyMapper.companyToDto(company);
		}	

	
//2	@DeleteMapping("/{registrationNumber}")
//	public void removeCompanyByRegistrationNumber(@PathVariable long registrationNumber) {
//		allCompany.remove(registrationNumber);
//	}
	
	@DeleteMapping("/{registrationNumber}")
	public void removeCompanyByRegistrationNumber(@PathVariable long registrationNumber) {
		hrCompanyService.delete(registrationNumber);
	}
	
//1	@PostMapping("/{id}/employees")
//	public ResponseEntity<CompanyDto> addNewEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
//		CompanyDto companyDto = allCompany.get(id);
//		if (companyDto == null) {
//			return ResponseEntity.notFound().build();
//		} else {
//			 companyDto.getEmployeesOfCompany().add(employeeDto);
//					return ResponseEntity.ok(companyDto);
//		}
//	}
	

	@PostMapping("/{id}/employees")
	public CompanyDto addNewEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		Company company = findByIdOrThrow(id);
		List<Employee> employeesOfCompany = company.getEmployeesOfCompany();
		//HrEmployeeService hrEmployeeService = 
		//Employee employee = /hr/src/main/java/hu/webuni/hr/lilla/web/CompanyController.java.employeeMapper.dtoToEmployee(employeeDto);
		
		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
		hrEmployeeService.save(employee);
		//employeesOfCompany.add(employee);
		
		hrCompanyService.modify(company); 
		return companyMapper.companyToDto(company);
   	}
	//Mit csinálok? változtatom adott cég dolgozói listáját
	//megkeresem adott céget, lekérem a listáját
	//elmentem (post--> save) az új dolgozót, akit előbb dtoból sima emp-be mappelek
	//módosítom(put--> modify) a cég listáját
	
//1	@DeleteMapping("/{id}/employee/{employeeId}")
//	public ResponseEntity<CompanyDto> deleteEmployee(@PathVariable long id, @PathVariable long employeeId) {
//		CompanyDto companyDto = allCompany.get(id);
//		if (companyDto == null) {
//			return ResponseEntity.notFound().build();
//	} else {
//		for (Iterator<EmployeeDto> iterator = companyDto.getEmployeesOfCompany().iterator(); iterator.hasNext();) {
//			EmployeeDto employeeDto = iterator.next();
//			if (employeeDto.getId() == employeeId) {
//				iterator.remove();
//				break;
//				}	
//			}
//		return ResponseEntity.ok(companyDto);
//		}
//	}
	
//2	@DeleteMapping("/{id}/employee/{employeeId}")
//	public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId) {
//		CompanyDto companyDto = findByIdOrThrow(id);
//		for (Iterator<EmployeeDto> iterator = companyDto.getEmployeesOfCompany().iterator(); iterator.hasNext();) {
//			EmployeeDto employeeDto = iterator.next();
//			if (employeeDto.getId() == employeeId) {
//				iterator.remove();
//				break;
//				}	
//			}
//		return companyDto;
//	}
	
	@DeleteMapping("/{id}/employee/{employeeId}")
	public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId) {
		Company company = findByIdOrThrow(id);
		for (Iterator<Employee> iterator = company.getEmployeesOfCompany().iterator(); iterator.hasNext();) {
			Employee employee = iterator.next();
			if (employee.getId() == employeeId) {
				iterator.remove();
				break;
				}	
			}
		return companyMapper.companyToDto(company);
	}
	
//2	@PutMapping("/{id}/employees")
//	public CompanyDto replaceEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> employees) {
//		CompanyDto companyDto = findByIdOrThrow(id);
//		companyDto.setEmployeesOfCompany(employees);
//		return companyDto;
//	}
//	
	
	@PutMapping("/{id}/employees")
	public CompanyDto replaceEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> employeeDtos) {
		Company company = findByIdOrThrow(id);
		List<Employee> employees = employeeMapper.allEmployeeDtosToEmployee(employeeDtos);
		company.setEmployeesOfCompany(employees);
		hrCompanyService.modify(company);
		CompanyDto result = companyMapper.companyToDto(company);
		return result;
		
		//List<Employee> actualEmployees = company.getEmployeesOfCompany(companyMapper.allEmployeeToEmployeeDtos(employees));
		//employeeMapper.allEmployeeToEmployeeDtos(hrEmployeeService.findAll());
		//company.setEmployeesOfCompany(employees);

		//List<Employee> employeesOfCompany = company.getEmployeesOfCompany();
		//employeeMapper.allEmployeeToEmployeeDtos(employeesOfCompany);
		
//		for (Employee employee : employeesOfCompany) {
//			employeeMapper.dtoToEmployee(employeeDto);
//			employeesOfCompany.add(employee);
//			hrEmployeeService.save(employee);
//		}
//		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
//		
//		company.setEmployeesOfCompany(actualEmployees);
//		hrCompanyService.modify(company); 
//		hrCompanyService.modify(company); 
//		return companyMapper.companyToDto(company);
	}
	
	//Mit csinálok? változtatom adott cég dolgozói listáját
		//megkeresem adott céget, lekérem a listáját
		//egyesével végigmegyek a dolgozói listán, 
		//módosítom egyesével a dolgozókat, akiket dtoból sima emp-be mappelek
		//a megváltozott listával módosítom a céget
		//
	
	
//2	public CompanyDto findByIdOrThrow(long id) {
//		CompanyDto companyDto = allCompany.get(id);
//		if (companyDto == null) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}
//		return companyDto;
//	}
	
	public Company findByIdOrThrow(long id) {
		Company company = hrCompanyService.findById(id);
		if (company == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return company;
	}
}


//Készíts egy JSON REST API-t /api/companies alap URL-lel, amely mögött a végpontok memóriában tárolt 
//CompanyDto-kal dolgoznak, és képesek:
//	
//•Az összes cég visszaadására
//•Adott id-jű cég visszaadására
//•Új cég felvételére
//•Meglévő cég módosítására
//•Meglévő cég törlésére

//Bővítsd ki a cégek listázását, és egy cég visszaadását oly módon, 
//hogy azok elfogadjonak egy full nevű paramétert. 
//Ha ez hiányzik, vagy értéke false, akkor a visszaadott cégekben ne szerepeljenek az alkalmazottak, 
//ellenkező esetben viszont igen.