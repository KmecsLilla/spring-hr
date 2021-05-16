package hu.webuni.hr.lilla.dto;

import java.time.LocalDateTime;

public class IntervalDto {
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public IntervalDto(LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public IntervalDto() {}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartingDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
}
