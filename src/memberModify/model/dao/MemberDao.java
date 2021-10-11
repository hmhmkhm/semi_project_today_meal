package memberModify.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import login.model.vo.Member;
public class MemberDao {
	
	private Properties query = new Properties();
	
	// MemberDao 객체 생성 시 query에 xml 파일안의 sql문 저장
	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/memberModify/memberModify-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int updateEmail(Connection conn, int userNo, String newEmail) {
		PreparedStatement pstmt = null;
		String sql = query.getProperty("updateEmail");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newEmail);
			pstmt.setInt(2, userNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectMember(Connection conn, int userNo) {
		Member mem = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mem = new Member(rset.getInt("user_no"),
			               rset.getString("user_id"),
			               rset.getString("user_pwd"),
			               rset.getString("user_name"),
			               rset.getString("phone"),
			               rset.getDate("enroll_date"),
			               rset.getDate("modify_date"),
			               rset.getString("status"),
			               rset.getString("api_token"),
			               rset.getInt("coin"),
			               rset.getString("email"),
			               rset.getString("question_no"),
			               rset.getString("question_answer"),
			               rset.getString("address1"),
			               rset.getString("address2"),
			               rset.getString("address3"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return mem;
	}

	public int updatePwd(Connection conn, int userNo, String userPwd, String newPwd) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPwd);
			pstmt.setInt(2, userNo);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection conn, int userNo) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getPhone());
			pstmt.setString(2, m.getQuestionNo());
			pstmt.setString(3, m.getQuestionAnswer());
			pstmt.setString(4, m.getAddress1());
			pstmt.setString(5, m.getAddress2());
			pstmt.setString(6, m.getAddress3());
			pstmt.setInt(7, m.getUserNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
