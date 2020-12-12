package com.root.homepage.member.service;

import java.io.PrintWriter;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import com.root.homepage.member.db.MemberDAO;
import com.root.homepage.member.db.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	// 인증키 생성, overriding이 가능하게 해줘야 함 
	@Override
	public String create_key() throws Exception {
		String key = "";
		Random rd = new Random();

		for (int i = 0; i < 8; i++) {
			key += rd.nextInt(10);
		}
		return key;
	}
	
	// DAO에서 구현한 내용을 사용하기 위해 manager 선언 !!!
	@Inject
	private MemberDAO manager;

	// 아이디 중복 검사(AJAX)
	@Override
	public void check_id(String id, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(manager.check_id(id));
		out.close();
	}

	// 이메일 중복 검사(AJAX)
	@Override
	public void check_email(String email, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(manager.check_email(email));
		out.close();
	}

	// 회원가입
	@Override
	public int join_member(MemberDTO member, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if (manager.check_id(member.getId()) == 1) {
			out.println("<script>");
			out.println("alert('동일한 아이디가 있습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return 0;
		} else if (manager.check_email(member.getEmail()) == 1) {
			out.println("<script>");
			out.println("alert('동일한 이메일이 있습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return 0;
		} else {
			// 인증키 set, *메일 발송/인증을 위한 변경 적용
			member.setApproval_key(create_key()); // 이 코드 한 줄로 회원가입 진행 --> 인증키 db에 저장 
			manager.join_member(member);
			// 인증 메일 발송
			send_mail(member);
			return 1;
		}
	}
	
	// 이메일 발송
	@Override
	public void send_mail(MemberDTO member) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = "thisforjimserver@naver.com";
		String hostSMTPpwd = "gyu-09!@EDqwd";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "thisforjimserver@naver.com";
		String fromName = "Find Phone Homepage";
		String subject = "check your sign-up";
		String msg = "Hi";

		// 회원가입 메일 내용
		subject = "Spring Homepage 회원가입 인증 메일입니다.";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'>";
		msg += member.getId() + "님 회원가입을 환영합니다.</h3>";
		msg += "<div style='font-size: 130%'>";
		msg += "하단의 인증 버튼 클릭 시 정상적으로 회원가입이 완료됩니다.</div><br/>";
		msg += "<form method='post' action='http://localhost:8080/homepage/member/approval_member.do'>";
		msg += "<input type='hidden' name='email' value='" + member.getEmail() + "'>";
		msg += "<input type='hidden' name='approval_key' value='" + member.getApproval_key() + "'>";
		msg += "<input type='submit' value='인증'></form><br/></div>";

		// 받는 사람 E-Mail 주소
		String mail = member.getEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}
	
	// 회원 인증
	
	@Override
	public void approval_member(MemberDTO member, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (manager.approval_member(member) == 0) { // 이메일 인증에 실패하였을 경우
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		} else { // 이메일 인증을 성공하였을 경우
			out.println("<script>");
			out.println("alert('인증이 완료되었습니다. 로그인 후 이용하세요.');");
			out.println("location.href='../index.jsp';");
			out.println("</script>");
			out.close();
		}
	}
	
			
	// **로그인
	@Override
	public MemberDTO login(MemberDTO member, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 등록된 아이디가 없으면
		if(manager.check_id(member.getId()) == 0) {
			out.println("<script>");
			out.println("alert('등록된 아이디가 없습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		} else {
			String pw = member.getPw();
			member = manager.login(member.getId());
			// 비밀번호가 다를 경우
			if(!member.getPw().equals(pw)) {
				out.println("<script>");
				out.println("alert('비밀번호가 다릅니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				return null;
			// 이메일 인증을 하지 않은 경우
			}else if(!member.getApproval_status().equals("true")) {
				out.println("<script>");
				out.println("alert('이메일 인증 후 로그인 하세요.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				return null;
            // 로그인 일자 업데이트 및 회원정보 리턴			
			}else {
				manager.update_log(member.getId());
				return member;
			}
		}
	}

	// 로그아웃
	@Override
	public void logout(HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("location.href=document.referrer;");
		out.println("</script>");
		out.close();
	}
	
	
}