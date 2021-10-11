package login.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import login.model.vo.Member;
import login.model.dao.MemberDao;

import static common.JDBCTemplate.*;

public class MemberDao {
	private Properties query = new Properties();
	
	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/login/member-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public Member loginMember(Connection conn, String id, String pwd) {
		Member loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginUser = new Member(rset.getInt("user_no"),
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
		}
		return loginUser;
	}
	//아이디 찾기
	public String findId(String name, String phone) {
		Connection conn = getConnection();
		String id = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("findId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				id = rset.getString("user_id");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
		}
		return id;
	}
	//비밀번호 찾기
	public String findPwd(String id, String phone, String Qno, String Qanswer) {
		Connection conn = getConnection();
		String pwd = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("findPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, phone);
			pstmt.setString(3, Qno);
			pstmt.setString(4, Qanswer);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pwd = rset.getString("user_pwd");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
		}
		return pwd;
	}
	
	//회원가입
	public int insertMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getQuestionNo());
			pstmt.setString(7, m.getQuestionAnswer());
			pstmt.setString(8, m.getAddress1());
			pstmt.setString(9, m.getAddress2());
			pstmt.setString(10, m.getAddress3());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	// 아이디 중복 체크
	public int idCheck(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String sql = query.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
}
