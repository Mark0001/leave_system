package com.tku.leave.domain;


public class LeaveAdyear {
	private long adyearId;
	private String adyear;
	
	
	public LeaveAdyear() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveAdyear(long adyearId, String adyear) {
		super();
		this.adyearId = adyearId;
		this.adyear = adyear;
	}
	public long getAdyearId() {
		return adyearId;
	}
	public void setAdyearId(long adyearId) {
		this.adyearId = adyearId;
	}
	public String getAdyear() {
		return adyear;
	}
	public void setAdyear(String adyear) {
		this.adyear = adyear;
	}
	@Override
	public String toString() {
		return "LeaveAdyear [adyearId=" + adyearId + ", adYear=" + adyear + "]";
	}
	
	
}

