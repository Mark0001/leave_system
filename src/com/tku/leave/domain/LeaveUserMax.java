package com.tku.leave.domain;

public class LeaveUserMax {
	private long adYearId; 
	private long userId; 
	private long classId;
	private String approveMax;
	
	private String name;
	private String adyear;
	private String className;
	
	
	public LeaveUserMax() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveUserMax(long adYearId, long userId, long classId,
			String approveMax, String name, String adyear, String className) {
		super();
		this.adYearId = adYearId;
		this.userId = userId;
		this.classId = classId;
		this.approveMax = approveMax;
		this.name = name;
		this.adyear = adyear;
		this.className = className;
	}
	public long getAdYearId() {
		return adYearId;
	}
	public void setAdYearId(long adYearId) {
		this.adYearId = adYearId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public String getApproveMax() {
		return approveMax;
	}
	public void setApproveMax(String approveMax) {
		this.approveMax = approveMax;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdyear() {
		return adyear;
	}
	public void setAdyear(String adyear) {
		this.adyear = adyear;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "LeaveUserMax [adYearId=" + adYearId + ", userId=" + userId
				+ ", classId=" + classId + ", approveMax=" + approveMax
				+ ", name=" + name + ", adyear=" + adyear + ", className="
				+ className + "]";
	}

	
	
}
