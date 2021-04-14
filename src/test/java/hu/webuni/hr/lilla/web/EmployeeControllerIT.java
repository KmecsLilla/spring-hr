package hu.webuni.hr.lilla.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.lilla.dto.EmployeeDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

	private static final String BASE_URI = "/api/employees";

	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void testThatCreatedEmployeeIsListed() throws Exception {
		List<EmployeeDto> allEmployeesBefore = getAllEmployees();
		
		EmployeeDto newEmployeeDto = new EmployeeDto(10L,"Borbála", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1));
		createEmployee(newEmployeeDto);
		
		List<EmployeeDto> allEmployeesAfter = getAllEmployees();
		
		assertThat(allEmployeesAfter.subList(0, allEmployeesBefore.size()))
		.usingFieldByFieldElementComparator()
		.containsExactlyElementsOf(allEmployeesBefore);
		
		assertThat(allEmployeesAfter.get(allEmployeesAfter.size()-1))
		.usingRecursiveComparison()
		.isEqualTo(newEmployeeDto);
		
	}

	private void createEmployee(EmployeeDto newEmployeeDto) {
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(newEmployeeDto)
			.exchange()
			.expectStatus()
			.isOk();
	}

	private List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> responseList = webTestClient
			.get()
			.uri(BASE_URI)
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(EmployeeDto.class)
			.returnResult().getResponseBody();
		
		Collections.sort(responseList, (e1, e2) -> Long.compare(e1.getId(), e2.getId()));
			
		return responseList;
	}
	
	@Test
	void testThatCreatedEmployeeIsNotListed() throws Exception {
		List<EmployeeDto> allEmployeesBefore = getAllEmployees();
		
		EmployeeDto newEmployeeDto = new EmployeeDto(11L,"", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1));
		createEmployee_withoutSuccess(newEmployeeDto);
		
		List<EmployeeDto> allEmployeesAfter = getAllEmployees();
		
		assertThat(allEmployeesAfter)
		.usingFieldByFieldElementComparator()
		.containsExactlyElementsOf(allEmployeesBefore);
		
		
		assertThat(allEmployeesAfter.get(allEmployeesAfter.size()-1))
		.usingRecursiveComparison()
		.isEqualTo(allEmployeesAfter.get(allEmployeesBefore.size()-1));
		
	}

	private void createEmployee_withoutSuccess(EmployeeDto newEmployeeDto) {
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(newEmployeeDto)
			.exchange()
			.expectStatus()
			.isBadRequest();
	}
	
}
