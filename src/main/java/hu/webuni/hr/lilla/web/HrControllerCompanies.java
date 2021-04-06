package hu.webuni.hr.lilla.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.lilla.dto.CompanyDto;
import hu.webuni.hr.lilla.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class HrControllerCompanies {
	private Map<Long, CompanyDto> allCompany = new HashMap<>();
	{
		allCompany.put(1L, new CompanyDto(1L, "Libretto", "Budapest", List.of(new EmployeeDto(1L,"Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)),(new EmployeeDto(2L, "Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1))))));
		allCompany.put(2L, new CompanyDto(2L, "Librarius", "Miskolc", List.of(new EmployeeDto(1L, "Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)), (new EmployeeDto(2L, "Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1))))));
	}

	
	
	@GetMapping
	public List<CompanyDto> getAll() {
		return new ArrayList<>(allCompany.values());
	}
		
	@GetMapping("/{registerId}")
	public ResponseEntity<CompanyDto> getCompanyByRegisterId(@PathVariable long registerId) {
		CompanyDto companyDto = allCompany.get(registerId);
		if (companyDto != null) {
			return ResponseEntity.ok(companyDto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		allCompany.put(companyDto.getRegisterId(), companyDto);
		return companyDto;
	}
	
	@PutMapping("/{registerId}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long registerId, @RequestBody CompanyDto companyDto) {
		if (!allCompany.containsKey(registerId)) {
			return ResponseEntity.notFound().build();
		} else {
			companyDto.setRegisterId(registerId);
			allCompany.put(registerId, companyDto);
			return ResponseEntity.ok(companyDto);
		}	
	}
	
	@DeleteMapping("/{registerId}")
	public void removeCompanyByRegisterId(@PathVariable long registerId) {
		allCompany.remove(registerId);
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