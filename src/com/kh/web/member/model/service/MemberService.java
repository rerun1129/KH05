package com.kh.web.member.model.service;

import java.sql.Connection;

import com.kh.web.member.model.dao.MemberDAO;
import com.kh.web.member.model.vo.Member;
import static com.kh.web.common.JDBCTemplate.*;

public class MemberService {

	private Connection con;
	private MemberDAO dao = new MemberDAO();
	
	public int insertMember(Member m) {
		con = getConnection();
		
		int result = dao.insertMember(con, m);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public Member selectMember(Member loginMember) {
		con = getConnection();
		
		Member result = dao.selectMember(con, loginMember);
		
		close(con);
		
		return result;
	}

	public int updateMember(Member updateMember) {
		con = getConnection();

		int result = dao.updateMember(con, updateMember);

		if( result > 0 ) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}

	public int deleteMember(String userId) {
		con = getConnection();

		int result = dao.deleteMember(con, userId);

		if( result > 0 ) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;
	}
}












