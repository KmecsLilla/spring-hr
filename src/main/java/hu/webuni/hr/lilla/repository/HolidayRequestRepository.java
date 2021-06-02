package hu.webuni.hr.lilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.lilla.model.HolidayRequest;

public interface HolidayRequestRepository extends JpaRepository<HolidayRequest, Long> {

}
