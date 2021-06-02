package hu.webuni.hr.lilla.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.dto.HolidayRequestDto;
import hu.webuni.hr.lilla.dto.HolidayRequestFilterDto;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.model.HolidayRequest;
import hu.webuni.hr.lilla.repository.HolidayRequestRepository;

@Service
public class HolidayRequestService {

	@Autowired
	HolidayRequestRepository holidayRequestRepository;

	@Autowired
	EmployeeService employeeService;

	public List<HolidayRequest> findAll() {
		return holidayRequestRepository.findAll();
	}

	public Optional<HolidayRequest> findById(long id) {
		return holidayRequestRepository.findById(id);
	}

	public Page<HolidayRequest> findHolidayRequestByExample(HolidayRequestFilterDto example, Pageable pageable) {
		return null;
	}

	public HolidayRequest addHolidayRequest(HolidayRequest holidayRequest, long employeeId) {
		Employee employee = employeeService.findById(employeeId).get();
		employee.addHolidayRequest(holidayRequest);
		holidayRequest.setCreatedAt(LocalDateTime.now());
		return holidayRequestRepository.save(holidayRequest);
	}

	public HolidayRequest modifyHolidayRequest(long id, HolidayRequest newHolidayRequest,
			HolidayRequestDto modifiedHolidayRequest) {
		HolidayRequest holidayRequest = holidayRequestRepository.findById(id).get();
		if (holidayRequest.getApproved() != null) {
			throw new IllegalStateException();
		}
		holidayRequest.setStartDate(newHolidayRequest.getStartDate());
		holidayRequest.setEndDate(newHolidayRequest.getEndDate());
		holidayRequest.setApprovedAt(LocalDateTime.now());
		return holidayRequest;
	}

	public void deleteHolidayRequest(long id) {
		HolidayRequest holidayRequest = holidayRequestRepository.findById(id).get();
		if (holidayRequest.getApproved() != null) {
			throw new IllegalStateException();
		}
		holidayRequestRepository.deleteById(id);
	}

	public HolidayRequest approveHolidayRequest(long id, long approverId, Boolean status) {
		HolidayRequest holidayRequest = holidayRequestRepository.findById(id).get();
		if (holidayRequest.getApproved() != null) {
			throw new IllegalStateException();
		}
		holidayRequest.setApprover(employeeService.findById(approverId).get());
		holidayRequest.setApproved(status);
		holidayRequest.setApprovedAt(LocalDateTime.now());
		return holidayRequest;
	}







}
