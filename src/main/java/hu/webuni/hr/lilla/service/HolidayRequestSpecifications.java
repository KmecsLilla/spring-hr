package hu.webuni.hr.lilla.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.lilla.model.Company_;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.model.Employee_;
import hu.webuni.hr.lilla.model.HolidayRequest;
import hu.webuni.hr.lilla.model.HolidayRequest_;
import hu.webuni.hr.lilla.model.Position_;

public class HolidayRequestSpecifications {

	public static Specification<HolidayRequest> hasApproved(Boolean approved) {
		return (root, cq, cb) -> cb.equal(root.get(HolidayRequest_.approved), approved);
	}

	public static Specification<HolidayRequest> hasApprovalName(String approvalName) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(HolidayRequest_.approver)
				.get(Employee_.name)), (approvalName + "%").toLowerCase());
	}

	public static Specification<HolidayRequest> hasEmployeeName(String employeeName) {
			return (root, cq, cb) -> cb.like(cb.lower(root.get(HolidayRequest_.employee)
					.get(Employee_.name)), (employeeName + "%").toLowerCase());
		}

	public static Specification<HolidayRequest> createDateIsBetween(LocalDateTime createDateTimeStart,
			LocalDateTime createDateTimeEnd) {
		return (root, cq, cb) -> cb.between(root.get(HolidayRequest_.createdAt), createDateTimeStart, createDateTimeEnd);
	}

	public static Specification<HolidayRequest> isEndDateGreaterThan(LocalDateTime startOfHolidayRequest) {
		return (root, cq, cb) -> cb.greaterThan(root.get(HolidayRequest_.startDate), startOfHolidayRequest);
	}

	public static Specification<HolidayRequest> isStartDateLessThan(LocalDateTime endOfHolidayRequest) {
		return (root, cq, cb) -> cb.lessThan(root.get(HolidayRequest_.endDate), endOfHolidayRequest);
	}

	public static Specification<Employee> hasId(long id) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.id), id);
	}

	public static Specification<Employee> hasName(String name) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Employee_.name)),
				(name + "%").toLowerCase());
	}

	public static Specification<Employee> hasStatus(String status) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.position).get(Position_.name), status);
	}

	public static Specification<Employee> hasSalary(int salary) {
		return (root, cq, cb) -> cb.between(root.get(Employee_.salary), (int)(0.95*salary), (int)(1.05*salary));
	}

	public static Specification<Employee> hasStartingToWork (LocalDateTime startingToWork) {
		LocalDateTime startOfDay = LocalDateTime.of(
				startingToWork.toLocalDate(), LocalTime.of(0, 0));

		return (root, cq, cb) -> cb.between(root.get(Employee_.startingToWork),
				startOfDay, startOfDay.plusDays(1));
	}

	public static Specification<Employee> hasCompanyName(String companyName) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Employee_.company).get(Company_.name)), (companyName + "%").toLowerCase());
	}
}
