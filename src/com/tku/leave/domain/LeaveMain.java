package com.tku.leave.domain;

import java.util.Date;

public class LeaveMain {
	private long mainId;
	private String name;
	private Date applytime;
	private String approveStatus;
	private long userId;
	
	public LeaveMain() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveMain(long mainId, String name, Date applytime,
			String approveStatus, long userId) {
		super();
		this.mainId = mainId;
		this.name = name;
		this.applytime = applytime;
		this.approveStatus = approveStatus;
		this.userId = userId;
	}

	public long getMainId() {
		return mainId;
	}
	public void setMainId(long string) {
		this.mainId = string;
	}
	public Date getApplytime() {
		return applytime;
	}
	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "LeaveMain [mainId=" + mainId + ", name=" + name
				+ ", applytime=" + applytime + ", approveStatus="
				+ approveStatus + ", userId=" + userId + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
