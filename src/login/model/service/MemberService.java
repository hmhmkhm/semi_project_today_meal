package login.model.service;

import java.sql.Connection;

import login.model.vo.Member;
import login.model.dao.MemberDao;

import static common.JDBCTemplate.*;

public class MemberService {
	private MemberDao md = new MemberDao();
	
	public Member loginMember(String id, String pwd) {
		Connection conn = getConnection();
		Member loginUser = new MemberDao().loginMember(conn, id, pwd);
		
		close(conn);
		return loginUser;
		
	}
	
	// 회원가입
	public int insertMember(Member m) {
        Connection conn = getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	// 아이디 중복 체크
	public int idCheck(String userId) {
		Connection conn = getConnection();
		
		int result = md.idCheck(conn, userId);
		
		close(conn);
		
		return result;
	}
	

}
