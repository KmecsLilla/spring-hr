package hu.webuni.hr.lilla.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.lilla.dto.CompanyDto;
import hu.webuni.hr.lilla.model.Company;

@Mapper(componentModel="spring")
public interface CompanyMapper {
	CompanyDto companyToDto(Company company);

	@Mapping(target = "employees", ignore = true)
	@Named("summary")
	CompanyDto companySummaryToDto(Company company);

	Company dtoToCompany(CompanyDto companyDto);

	List<CompanyDto> companyToCompanyDtos(List<Company> allCompany);

	@IterableMapping(qualifiedByName = "summary")
	@Mapping(target = "employees", ignore = true)
	List<CompanyDto> companySummaryToCompanyDtos(List<Company> allCompany);
}
