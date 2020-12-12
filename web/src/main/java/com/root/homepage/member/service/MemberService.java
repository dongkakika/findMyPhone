package com.root.homepage.member.service;


import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;

import com.root.homepage.member.db.MemberDTO;

public interface MemberService {
	public void check_id(String id, HttpServletResponse response) throws Exception;

	public void check_email(String email, HttpServletResponse response) throws Exception;

	public int join_member(MemberDTO member, HttpServletResponse response) throws Exception;

	// Implements에서 사용할 추상 메서드 생성 
	String create_key() throws Exception;

	// mail 보내기에 대한 추상 메서드 생성 
	void send_mail(MemberDTO member) throws Exception;

	// *이메일 인증 추상 메서드 생성 
	void approval_member(MemberDTO member, HttpServletResponse response) throws Exception;

	// **로그인 로직을 위한 추상 메서드 생성
	MemberDTO login(MemberDTO member, HttpServletResponse response) throws Exception;

	// **로그아웃 로직 추상 메서드 생성 
	void logout(HttpServletResponse response) throws Exception;
	
	
	
}