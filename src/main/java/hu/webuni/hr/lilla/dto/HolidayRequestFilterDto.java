package hu.webuni.hr.lilla.dto;

import java.time.LocalDateTime;

public class HolidayRequestFilterDto {

	//private long id;
	private LocalDateTime createdTimeStart;
	private LocalDateTime createdTimeEnd;

	private String employeeName;

	private String approverName;
	private Boolean approved;

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public HolidayRequestFilterDto() {
		}

	public LocalDateTime getCreatedTimeStart() {
		return createdTimeStart;
	}

	public void setCreatedTimeStart(LocalDateTime createdTimeStart) {
		this.createdTimeStart = createdTimeStart;
	}

	public LocalDateTime getCreatedTimeEnd() {
		return createdTimeEnd;
	}

	public void setCreatedTimeEnd(LocalDateTime createdTimeEnd) {
		this.createdTimeEnd = createdTimeEnd;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

}
