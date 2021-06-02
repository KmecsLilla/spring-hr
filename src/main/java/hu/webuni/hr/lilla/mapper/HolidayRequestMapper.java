package hu.webuni.hr.lilla.mapper;

import java.util.List;

import hu.webuni.hr.lilla.dto.HolidayRequestDto;
import hu.webuni.hr.lilla.model.HolidayRequest;

public interface HolidayRequestMapper {

	public List<HolidayRequestDto> holidayRequestsToDtos(List<HolidayRequest> holidayRequests);

	public HolidayRequestDto holidayRequestToDto(HolidayRequest holidayRequest);

	public HolidayRequest dtoToHolidayRequest(HolidayRequestDto holidayRequestDto);

	public List<HolidayRequest> dtosToHolidayRequests(List<HolidayRequestDto> holidayRequestsDto);
}
