package com.sirma.longesteamperiod.domain;

public class OverlappedEmployeePair {
	private int empIdOne;
	private int empIdTwo;
	private int projectId;
	private long overlappedDays;

	public int getEmpIdOne() {
		return empIdOne;
	}

	public void setEmpIdOne(int empIdOne) {
		this.empIdOne = empIdOne;
	}

	public int getEmpIdTwo() {
		return empIdTwo;
	}

	public void setEmpIdTwo(int empIdTwo) {
		this.empIdTwo = empIdTwo;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public long getOverlappedDays() {
		return overlappedDays;
	}

	public void setOverlappedDays(int overlappedDays) {
		this.overlappedDays = overlappedDays;
	}

	public OverlappedEmployeePair(int empIdOne, int empIdTwo, int projectId, long overlappedDays) {
		super();
		this.empIdOne = empIdOne;
		this.empIdTwo = empIdTwo;
		this.projectId = projectId;
		this.overlappedDays = overlappedDays;
	}

}
