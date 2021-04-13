package hu.webuni.hr.lilla.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.lilla.dto.CompanyDto;
import hu.webuni.hr.lilla.model.Company;

@Mapper(componentModel="spring")
public interface CompanyMapper {

	List<CompanyDto> allCompanyToCompanyDtos(List<Company> allCompany);
	
	CompanyDto companyToDto(Company company);

	Company dtoToCompany(CompanyDto companyDto);

}
