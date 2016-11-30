package com.tku.leave.domain;

public class LeaveUser {
	private long userId;
    private String account;
    private String password;
    private String name;
    private String identity;
    private String mail;
    
	public LeaveUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveUser(long userId, String account, String password, String name,
			String identity, String mail) {
		super();
		this.userId = userId;
		this.account = account;
		this.password = password;
		this.name = name;
		this.identity = identity;
		this.mail = mail;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	@Override
	public String toString() {
		return "LeaveUser [userId=" + userId + ", account=" + account
				+ ", password=" + password + ", name=" + name + ", identity="
				+ identity + ", mail=" + mail + "]";
	}
    
}
