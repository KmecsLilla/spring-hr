package hu.webuni.hr.lilla.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	private Map<Long, CompanyDto> allCompany = new HashMap<>();
	{
		List<EmployeeDto> employeesOfCompany1 = new ArrayList<>();
		employeesOfCompany1.add(new EmployeeDto(1L,"Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)));
		employeesOfCompany1.add(new EmployeeDto(2L, "Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1)));
		
		List<EmployeeDto> employeesOfCompany2 = new ArrayList<>();
		employeesOfCompany2.add(new EmployeeDto(1L, "Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)));
		employeesOfCompany2.add(new EmployeeDto(2L, "Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1)));
			
		allCompany.put(1L, new CompanyDto(1L, 1L, "Libretto", "Budapest", employeesOfCompany1));
		allCompany.put(2L, new CompanyDto(2L, 2L, "Librarius", "Miskolc", employeesOfCompany2));

//		allCompany.put(1L, new CompanyDto(1L, 1L, "Libretto", "Budapest", List.of(new EmployeeDto(1L,"Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)), (new EmployeeDto(2L, "Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1))))));
//		allCompany.put(2L, new CompanyDto(2L, 2L, "Librarius", "Miskolc", List.of(new EmployeeDto(1L, "Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)), (new EmployeeDto(2L, "Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1))))));
	}

	
	
	//Kiegészítés:spéci JSON-serializáció
//	@GetMapping(params = "full=true")
//	public List<CompanyDto> getCompanies() {
//		return new ArrayList<>(allCompany.values());
//	}
//		
//	
//	@GetMapping
//	@JsonView(Views.BaseData.class)
//	public List<CompanyDto> getCompaniesWithBaseData(@RequestParam(required = false) Boolean full) {
//		return getCompanies();
//	}
	
//	Alap "full" nélkül:	
//	@GetMapping
//	public List<CompanyDto> getAll() {
//		return new ArrayList<>(allCompany.values());
//	}
	
	@GetMapping
	public List<CompanyDto> getAllCompany(@RequestParam(required = false) Boolean full) {
		if (full != null && full) {
			return new ArrayList<>(allCompany.values());
		} else {
			return allCompany.values().stream()
					.map(mapToCompanyWithoutEmployees())
					.collect(Collectors.toList());
		}
	}

	private Function<? super CompanyDto, ? extends CompanyDto> mapToCompanyWithoutEmployees() {
		return c -> new CompanyDto(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null);
	}
		
//	@GetMapping("/{registrationNumber}")
//	public ResponseEntity<CompanyDto> getCompanyByRegistrationNumber(@PathVariable long registrationNumber) {
//		CompanyDto companyDto = allCompany.get(registrationNumber);
//		if (companyDto != null) {
//			return ResponseEntity.ok(companyDto);
//		}
//		else {
//			return ResponseEntity.notFound().build();
//		}
//	}
	
//	Alap "full" nélkül:
//  @GetMapping("/{registrationNumber}")
//	public CompanyDto getCompanyByRegistrationNumber(@PathVariable long registrationNumber) {	
//		return findByIdOrThrow(registrationNumber);
//	}
	
	@GetMapping("/{registrationNumber}")
	public CompanyDto getCompanyByRegistrationNumber(@PathVariable long registrationNumber, @RequestParam(required = false) Boolean full) {	
		CompanyDto companyDto = findByIdOrThrow(registrationNumber);
		if (full != null && full) {
			return companyDto;
		} else {
			return mapToCompanyWithoutEmployees().apply(companyDto);
		}
		
	}
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		allCompany.put(companyDto.getRegistrationNumber(), companyDto);
		return companyDto;
	}
	
	@PutMapping("/{registrationNumber}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long registrationNumber, @RequestBody CompanyDto companyDto) {
		if (!allCompany.containsKey(registrationNumber)) {
			return ResponseEntity.notFound().build();
		} else {
			companyDto.setRegistrationNumber(registrationNumber);
			allCompany.put(registrationNumber, companyDto);
			return ResponseEntity.ok(companyDto);
		}	
	}
	
	@DeleteMapping("/{registrationNumber}")
	public void removeCompanyByRegistrationNumber(@PathVariable long registrationNumber) {
		allCompany.remove(registrationNumber);
	}
	
//	@PostMapping("/{id}/employees")
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
		CompanyDto companyDto = findByIdOrThrow(id);
		List<EmployeeDto> employeesOfCompany = companyDto.getEmployeesOfCompany();
		employeesOfCompany.add(employeeDto);
		return companyDto;
   	}
	
//	@DeleteMapping("/{id}/employee/{employeeId}")
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
	
	@DeleteMapping("/{id}/employee/{employeeId}")
	public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId) {
		CompanyDto companyDto = findByIdOrThrow(id);
		for (Iterator<EmployeeDto> iterator = companyDto.getEmployeesOfCompany().iterator(); iterator.hasNext();) {
			EmployeeDto employeeDto = iterator.next();
			if (employeeDto.getId() == employeeId) {
				iterator.remove();
				break;
				}	
			}
		return companyDto;
	}
	
	@PutMapping("/{id}/employees")
	public CompanyDto replaceEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> employees) {
		CompanyDto companyDto = findByIdOrThrow(id);
		companyDto.setEmployeesOfCompany(employees);
		return companyDto;
	}
	
	
	public CompanyDto findByIdOrThrow(long id) {
		CompanyDto companyDto = allCompany.get(id);
		if (companyDto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return companyDto;
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