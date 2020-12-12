package com.root.homepage.member.db;

import java.sql.Date;

public class MemberDTO {
	private String id;
	private String pw;
	private String email;
	private Date reg_date;
	private Date log_date;
	private String approval_status;
	private String approval_key;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getLog_date() {
		return log_date;
	}

	public void setLog_date(Date log_date) {
		this.log_date = log_date;
	}

	public String getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}

	public String getApproval_key() {
		return approval_key;
	}

	public void setApproval_key(String approval_key) {
		this.approval_key = approval_key;
	}
}
