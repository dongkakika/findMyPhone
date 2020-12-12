package com.root.homepage.member.controller;

import java.util.List;
import java.util.Random;

// db 테스트를 위한 import
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.root.homepage.member.db.MemberDTO;
import com.root.homepage.member.service.MemberServiceImpl;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	// 회원 가입 폼 이동
	@RequestMapping(value = "/memberJoinForm.do")
	public String memberJoinForm() throws Exception {
		return "/member/memberJoinForm";
	}

	@RequestMapping(value = "/google_api.do")
	public String test() throws Exception {
		return "/member/google_api";
	}

	@Autowired
	private MemberServiceImpl service;

	// 아이디 중복 검사(AJAX)
	@RequestMapping(value = "/check_id.do", method = RequestMethod.POST)
	public void check_id(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		service.check_id(id, response);
	}

	// 이메일 중복 검사(AJAX)
	@RequestMapping(value = "/check_email.do", method = RequestMethod.POST)
	public void check_email(@RequestParam("email") String email, HttpServletResponse response) throws Exception {
		service.check_email(email, response);
	}

	// 회원 가입
	@RequestMapping(value = "/join_member.do", method = RequestMethod.POST)
	public String join_member(@ModelAttribute MemberDTO member, RedirectAttributes rttr, HttpServletResponse response)
			throws Exception {
		rttr.addFlashAttribute("result", service.join_member(member, response));
		return "redirect:./memberJoinForm.do";
	}

	// 회원 인증
	@RequestMapping(value = "/approval_member.do", method = RequestMethod.POST)
	public void approval_member(@ModelAttribute MemberDTO member, HttpServletResponse response) throws Exception{
		service.approval_member(member, response);
	}
	
	// **로그인 폼 이동
	@RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
	public String login_form() throws Exception{
		return "/member/loginForm";
	}
	
	// **로그인
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@ModelAttribute MemberDTO member, HttpSession session, HttpServletResponse response) throws Exception{
		member = service.login(member, response);
		session.setAttribute("member", member);
		return "redirect:../";
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public void logout(HttpSession session, HttpServletResponse response) throws Exception{
		session.invalidate();
		//session.removeAttribute("member");
		service.logout(response);
	}
}