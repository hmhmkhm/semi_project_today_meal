package review.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import event.model.vo.Event;
import productInfo.model.vo.Product;
import review.model.vo.Review;

public class ReviewDao {
private Properties query = new Properties();
	
	public ReviewDao() {
		String fileName = ReviewDao.class.getResource("/sql/review/review-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 리뷰 작성하기 메소드
	public int insertReview(Connection conn, Review r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDouble(1, r.getPoint());
			pstmt.setString(2, r.getReviewText());
			pstmt.setString(3, r.getReviewImage());
			pstmt.setInt(4, r.getUserNo());
			pstmt.setInt(5, r.getProductNo());
			pstmt.setInt(6, r.getOrderNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 리뷰 전체 목록 출력
	public List<Review> selectList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> reviewList = new ArrayList<>();
		String sql = query.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				reviewList.add(new Review());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return reviewList;
	}

	// 특정 리뷰 조회하기
	public Review selectReview(Connection conn, int rno) {
		// TODO Auto-generated method stub
		return null;
	}

	// 마이페이지 리뷰 목록 출력
	public List<Review> selectMyReviewList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> reviewList = new ArrayList<>();
		String sql = query.getProperty("selectMyReviewList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				reviewList.add(new Review(rset.getInt("review_no"),
										  rset.getDouble("point"),
										  rset.getString("review_text"),
										  rset.getString("review_image"),
										  rset.getDate("review_register"),
										  rset.getInt("user_no"),
										  rset.getInt("product_no"),
										  rset.getInt("order_no"),
										  rset.getString("status")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return reviewList;
	}

	// 리뷰 수정
	public int updateReview(Connection conn, Review r) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("updateReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getReviewText());
			pstmt.setString(2, r.getReviewImage());
			pstmt.setInt(3, r.getReviewNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 리뷰 삭제
	public int deleteReview(Connection conn, int rno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("deleteReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	//리뷰 별점 순 정렬
	public Review selectReviewPoint(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Review r = null;
		String sql = query.getProperty("selectReviewPoint");
		
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, pno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				r = new Review(rset.getInt("reviewNo"),
								rset.getDouble("point"),
								rset.getString("reviewText"),
								rset.getString("reviewImage"),
								rset.getDate("reviewRegister"),
								rset.getInt("userNo"),
								rset.getInt("productNo"),
								rset.getInt("orderNo"),
								rset.getString("status"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}

	

	public List<Review> selectMyReviewProductList(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> r = new ArrayList<>();
		String sql = query.getProperty("selectMyReviewListTest");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				r.add(new Review(rset.getInt("REVIEW_NO"),
								rset.getDouble("POINT"),
								rset.getString("REVIEW_TEXT"),
								rset.getString("REVIEW_IMAGE"),
								rset.getDate("REVIEW_REGISTER"),
								rset.getInt("USER_NO"),
								rset.getInt("ORDER_NO"),
								rset.getString("STATUS"),
								rset.getString("PRODUCT_NAME")));
				System.out.println(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}

	public List<Review> selectMyReviewProductListDesc(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> r = new ArrayList<>();
		String sql = query.getProperty("selectMyReviewProductListDesc");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				r.add(new Review(rset.getInt("REVIEW_NO"),
								rset.getDouble("POINT"),
								rset.getString("REVIEW_TEXT"),
								rset.getString("REVIEW_IMAGE"),
								rset.getDate("REVIEW_REGISTER"),
								rset.getInt("USER_NO"),
								rset.getInt("ORDER_NO"),
								rset.getString("STATUS"),
								rset.getString("PRODUCT_NAME")));
				System.out.println(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}

	public List<Review> selectMyReviewProductListAsc(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> r = new ArrayList<>();
		String sql = query.getProperty("selectMyReviewProductListAsc");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				r.add(new Review(rset.getInt("REVIEW_NO"),
								rset.getDouble("POINT"),
								rset.getString("REVIEW_TEXT"),
								rset.getString("REVIEW_IMAGE"),
								rset.getDate("REVIEW_REGISTER"),
								rset.getInt("USER_NO"),
								rset.getInt("ORDER_NO"),
								rset.getString("STATUS"),
								rset.getString("PRODUCT_NAME")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}


	
}
