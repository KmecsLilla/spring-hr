package hu.webuni.hr.lilla.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.lilla.model.HolidayRequest;

public interface HolidayRequestRepository extends JpaRepository<HolidayRequest, Long> {

	Page<HolidayRequest> findAll(Specification<HolidayRequest> spec, Pageable pageable);

}
