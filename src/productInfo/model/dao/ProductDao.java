package productInfo.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import productInfo.model.vo.Product;
import review.model.vo.Review;

public class ProductDao {
	private Properties query = new Properties();
	public ProductDao() {
		String fileName = ProductDao.class.getResource("/sql/productInfo/productInfo-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 모든 상품 조회
	public Product selectProduct(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		String sql = query.getProperty("selectProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, pno);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p = new Product(rset.getInt("product_no"),
								rset.getString("product_name"),
								rset.getString("product_img"),
								rset.getInt("product_price"),
								rset.getString("product_detail"),
								rset.getDate("product_date"),
								rset.getInt("category_no"),
								rset.getString("status"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return p;
	}
	
	

	// 상품에 대한 리뷰 조회
	public List<Review> selectReviewList(Connection conn, int pno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> reviewList = new ArrayList<>();
		String sql = query.getProperty("selectReviewList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				reviewList.add(new Review(rset.getInt("review_no"),
											rset.getDouble("point"),
											rset.getString("review_text"),
											rset.getString("review_image"),
											rset.getDate("review_register"),
											rset.getInt("user_no"),
											rset.getInt("product_no"),
											rset.getInt("order_no"),
											rset.getString("status"),
											rset.getString("user_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return reviewList;
	}

	// 로그인한 사용자의 리뷰리스트 조회
	public List<Review> selectMyReviewList(Connection conn,int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> reviewList = new ArrayList<>();
		String sql = query.getProperty("selectMyReviewList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				reviewList.add(new Review(rset.getInt("REVIEW_NO"),
											rset.getDouble("POINT"),
											rset.getString("REVIEW_TEXT"),
											rset.getString("REVIEW_IMAGE"),
											rset.getDate("REVIEW_REGISTER"),
											rset.getInt("USER_NO"),
											rset.getInt("PRODUCT_NO"),
											rset.getInt("ORDER_NO"),
											rset.getString("STATUS")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return reviewList;
	}

	// 별점 순 정렬
	public List<Review> selectReviewPoint(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> reviewList = new ArrayList<>();
		String sql = query.getProperty("selectReviewPointList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, pno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
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

	// 마이페이지 리뷰 상품명 내림차순 정렬
	public List<Review> selectReviewNameDesc(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> reviewList = new ArrayList<>();
		String sql = query.getProperty("selectReviewNameDesc");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
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

	// 마이페이지 리뷰 오름차순 정렬
	public List<Review> selectReviewNameAsc(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> reviewList = new ArrayList<>();
		String sql = query.getProperty("selectReviewNameAsc");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
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

	// 상품상세페이지 상품정보
	public Product selectProductDetail(Connection conn, int pno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		String sql = query.getProperty("selectProductDetail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p = new Product(rset.getInt("product_no"),
								rset.getString("product_name"),
								rset.getString("product_img"),
								rset.getInt("product_price"),
								rset.getString("product_detail"),
								rset.getDate("product_date"),
								rset.getInt("category_no"),
								rset.getString("status"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return p;
	}
	

}
