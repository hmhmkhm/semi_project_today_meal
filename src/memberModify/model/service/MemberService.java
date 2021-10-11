package memberModify.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import login.model.vo.Member;
import memberModify.model.dao.MemberDao;

public class MemberService {
	private MemberDao md = new MemberDao();

	public Member updateEmail(int userNo, String newEmail) {
		
		Connection conn = getConnection();
		Member updateMember = null;
		
		int result = md.updateEmail(conn, userNo, newEmail);
		
		if(result > 0) {
			updateMember = md.selectMember(conn, userNo);
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return updateMember;
	}

	public Member updatePwd(int userNo, String userPwd, String newPwd) {
		
		Connection conn = getConnection();
		Member updateMember = null;
		
		int result = md.updatePwd(conn, userNo, userPwd, newPwd);
		
		if(result > 0) {
			updateMember = md.selectMember(conn, userNo);
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMember;
	}

	public Member deleteMember(int userNo) {
		
		Connection conn = getConnection();
		Member deleteMember = null;
		
		int result = md.deleteMember(conn, userNo);
		
		if(result > 0) {
			
			deleteMember = md.selectMember(conn, userNo);
			commit(conn);
			
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return deleteMember;
	}

	public Member updateMember(Member m) {
		
		Connection conn = getConnection();
		Member updateMember = null;
		
		// 1. 수정하려는 정보가 담긴 Member 객체를 가지고 Dao 에서 updateMember 수행
		int result = md.updateMember(conn, m);
		
		// 2. 수정이 잘 되었다면 수정된 정보의 member 객체 select 후 리턴
		if(result > 0) {
			updateMember = md.selectMember(conn, m.getUserNo());
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMember;
	}
	
	
}
