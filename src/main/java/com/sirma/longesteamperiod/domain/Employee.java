package com.sirma.longesteamperiod.domain;

import java.time.LocalDate;

public class Employee {
	private int empID;
	private LocalDate startDate;
	private LocalDate endDate;

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
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

	public Employee(int empID, LocalDate startDate, LocalDate endDate) {
		super();
		this.empID = empID;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
