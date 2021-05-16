package hu.webuni.hr.lilla.web;

import java.util.List;
import java.util.NoSuchElementException;

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

import hu.webuni.hr.lilla.dto.CompanyDto;
import hu.webuni.hr.lilla.dto.EmployeeDto;
import hu.webuni.hr.lilla.mapper.CompanyMapper;
import hu.webuni.hr.lilla.mapper.EmployeeMapper;
import hu.webuni.hr.lilla.model.Company;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.service.HrCompanyService;
import hu.webuni.hr.lilla.service.HrEmployeeService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	@Autowired
	HrCompanyService hrCompanyService;

	@Autowired
	HrEmployeeService hrEmployeeService;

	@Autowired
	CompanyMapper companyMapper;

	@Autowired
	EmployeeMapper employeeMapper;

	@GetMapping
	public List<CompanyDto> getAllCompany(@RequestParam(required = false) Boolean full) {
		List<Company> allCompany = hrCompanyService.findAll();
		if (full != null && full) {
			return companyMapper.companyToCompanyDtos(allCompany);
		} else {
			return companyMapper.companySummaryToCompanyDtos(allCompany);
		}
	}

	@GetMapping("/{id}")
	public CompanyDto getCompanyById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		Company company = findByIdOrThrow(id);
		if (full != null && full) {
			return companyMapper.companyToDto(company);
		} else {
			return companyMapper.companySummaryToDto(company);
		}

	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		Company company = companyMapper.dtoToCompany(companyDto);
		hrCompanyService.save(company);
		return companyMapper.companyToDto(company);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		companyDto.setId(id);
		Company updatedCompany= companyMapper.dtoToCompany(companyDto);
		hrCompanyService.update(updatedCompany);
		if(updatedCompany == null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 		}
		return ResponseEntity.ok(companyMapper.companyToDto(updatedCompany));
	}

	@DeleteMapping("/{id}")
	public void removeCompanyById(@PathVariable long id) {
		hrCompanyService.delete(id);
	}

	@PostMapping("/{companyId}/employees")
	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto employeeDto) {
		try {
			Employee employee = employeeMapper.dtoToEmployee(employeeDto);
			Company company = hrCompanyService.addEmployee(companyId, employee);
			CompanyDto companyDto = companyMapper.companyToDto(company);
			return companyDto;
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
   	}

	@DeleteMapping("/{id}/employee/{employeeId}")
	public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId) {
		Company company = hrCompanyService.deleteEmployee(id, employeeId);
		return companyMapper.companyToDto(company);
	}

	@PutMapping("/{id}/employees")
	public CompanyDto replaceEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> employeeDtos) {
		Company company = findByIdOrThrow(id);
		List<Employee> employees = employeeMapper.allEmployeeDtosToEmployee(employeeDtos);
		company.setEmployees(employees);
		hrCompanyService.update(company);
		CompanyDto result = companyMapper.companyToDto(company);
		return result;
	}

	public Company findByIdOrThrow(long id) {
		Company company = hrCompanyService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return company;
	}
}
