package com.root.homepage.member.db;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MemberDAO {
	
	@Autowired
	SqlSession sqlsession = null;
	
	// 아이디 중복 검사
	public int check_id(String id) throws Exception{
		return sqlsession.selectOne("member.check_id", id);
	}
	
	// 이메일 중복 검사
	public int check_email(String email) throws Exception{
		return sqlsession.selectOne("member.check_email", email);
	}
	
	// 회원가입
	@Transactional
	public int join_member(MemberDTO member) throws Exception{
		return sqlsession.insert("member.join_member", member);
	}

	// *이메일 인증
	@Transactional
	public int approval_member(MemberDTO member) throws Exception{
		return sqlsession.update("member.approval_member", member);
	}
	
	// **로그인 검사
	public MemberDTO login(String id) throws Exception{
		return sqlsession.selectOne("member.login", id);
	}
	
	// **로그인 접속일자 변경
	@Transactional
	public int update_log(String id) throws Exception{
		return sqlsession.update("member.update_log", id);
	}
}