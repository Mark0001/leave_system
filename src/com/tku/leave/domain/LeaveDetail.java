package com.tku.leave.domain;

import java.util.Date;

public class LeaveDetail {
	private long detailId;
	private long mainId ;
	private long classId;
	private Date beginTime;
	private Date endTime;
	private String leaveTime;
	private String reason;
	
	private String name;
	private String className;
	private String approveStatus;
	private String mail;
	
	
	
	public LeaveDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveDetail(long detailId, long mainId, long classId,
			Date beginTime, Date endTime, String leaveTime, String reason,
			String name, String className, String approveStatus, String mail) {
		super();
		this.detailId = detailId;
		this.mainId = mainId;
		this.classId = classId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.leaveTime = leaveTime;
		this.reason = reason;
		this.name = name;
		this.className = className;
		this.approveStatus = approveStatus;
		this.mail = mail;
	}
	
	public long getDetailId() {
		return detailId;
	}
	public void setDetailId(long detailId) {
		this.detailId = detailId;
	}
	public long getMainId() {
		return mainId;
	}
	public void setMainId(long mainId) {
		this.mainId = mainId;
	}
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Override
	public String toString() {
		return "LeaveDetail [detailId=" + detailId + ", mainId=" + mainId
				+ ", classId=" + classId + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + ", leaveTime=" + leaveTime
				+ ", reason=" + reason + ", name=" + name + ", className="
				+ className + ", approveStatus=" + approveStatus + ", mail="
				+ mail + "]";
	}
	
	

	
	
}
