package com.sirma.longesteamperiod.domain;

import java.time.LocalDate;

public class Record {
	private int empID;
	private int projectID;
	private LocalDate startDate;
	private LocalDate endDate;

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Record(int empID, int projectID, LocalDate startDate, LocalDate endDate) {
		super();
		this.empID = empID;
		this.projectID = projectID;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
