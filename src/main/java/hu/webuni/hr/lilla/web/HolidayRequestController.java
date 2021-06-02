package hu.webuni.hr.lilla.web;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.lilla.dto.HolidayRequestDto;
import hu.webuni.hr.lilla.dto.HolidayRequestFilterDto;
import hu.webuni.hr.lilla.mapper.EmployeeMapper;
import hu.webuni.hr.lilla.mapper.HolidayRequestMapper;
import hu.webuni.hr.lilla.model.HolidayRequest;
import hu.webuni.hr.lilla.service.HolidayRequestService;

public class HolidayRequestController {

	@Autowired
	HolidayRequestService holidayRequestService;

	@Autowired
	EmployeeMapper employeeMapper;

	@Autowired
	HolidayRequestMapper holidayRequestMapper;

	@GetMapping
	public List<HolidayRequestDto> getAll() {
		return holidayRequestMapper.holidayRequestsToDtos(holidayRequestService.findAll());
	}

	@GetMapping("/{id}")
	public HolidayRequestDto getById(@PathVariable long id) {
		HolidayRequest holidayRequest = holidayRequestService.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return holidayRequestMapper.holidayRequestToDto(holidayRequest);
	}

	@PostMapping
	public HolidayRequestDto addHolidayRequest(@PathVariable long id, @RequestBody HolidayRequestDto newHolidayRequest) {
		HolidayRequest holidayRequest;
		try {
			holidayRequest = holidayRequestService.addHolidayRequest(holidayRequestMapper.dtoToHolidayRequest(newHolidayRequest), id);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return holidayRequestMapper.holidayRequestToDto(holidayRequest);
	}

	@PostMapping(value = "/search")
	public List<HolidayRequestDto> findByExample(@RequestBody HolidayRequestFilterDto example, Pageable pageable) {
		Page<HolidayRequest> page = holidayRequestService.findHolidayRequestByExample(example, pageable);
		return holidayRequestMapper.holidayRequestsToDtos(page.getContent());
	}

	@PutMapping("/{id}")
	public HolidayRequestDto modifyHolidayRequest(@PathVariable long id, @RequestBody HolidayRequestDto modifiedHolidayRequest) {
		modifiedHolidayRequest.setId(id);
		HolidayRequest holidayRequest;
		try {
			holidayRequest = holidayRequestService.modifyHolidayRequest(id, holidayRequestMapper.dtoToHolidayRequest(modifiedHolidayRequest), modifiedHolidayRequest);
		} catch (InvalidParameterException e) {
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return holidayRequestMapper.holidayRequestToDto(holidayRequest);
	}

	@DeleteMapping("/{id}")
	public void deleteHolidayRequest(@PathVariable long id) {
		try {
			holidayRequestService.deleteHolidayRequest(id);
		} catch (InvalidParameterException e) {
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/{id}/approval", params = {"statusOfApproving", "approverId" })
	public HolidayRequestDto approveHolidayRequest(@PathVariable long id, @RequestParam long approverId, @RequestParam Boolean statusOfApproving) {
		HolidayRequest holidayRequest;
		try {
			holidayRequest = holidayRequestService.approveHolidayRequest(id, approverId, statusOfApproving);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return holidayRequestMapper.holidayRequestToDto(holidayRequest);
	}
}
