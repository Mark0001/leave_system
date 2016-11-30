package com.tku.leave.domain;

public class LeaveClass {
	private long classId;
	private String className;
	
	private float SumLeaveTime;
	private float remain;
	private float approveMax;
	
	
	
	public LeaveClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveClass(long classId, String className, float sumLeaveTime,
			float remain, float approveMax) {
		super();
		this.classId = classId;
		this.className = className;
		SumLeaveTime = sumLeaveTime;
		this.remain = remain;
		this.approveMax = approveMax;
	}
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public float getSumLeaveTime() {
		return SumLeaveTime;
	}
	public void setSumLeaveTime(float sumLeaveTime) {
		SumLeaveTime = sumLeaveTime;
	}
	public float getRemain() {
		return remain;
	}
	public void setRemain(float remain) {
		this.remain = remain;
	}
	public float getApproveMax() {
		return approveMax;
	}
	public void setApproveMax(float approveMax) {
		this.approveMax = approveMax;
	}
	@Override
	public String toString() {
		return "LeaveClass [classId=" + classId + ", className=" + className
				+ ", SumLeaveTime=" + SumLeaveTime + ", remain=" + remain
				+ ", approveMax=" + approveMax + "]";
	}
	
	

}
