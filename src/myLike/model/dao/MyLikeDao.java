package myLike.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import common.paging.model.vo.PageInfo;
import myLike.model.vo.MyLike;

import static common.JDBCTemplate.*;

import subpage.model.dao.ProductDao;
import totalReview.model.vo.Option;
import totalReview.model.vo.Product;
import totalReview.model.vo.Review;

public class MyLikeDao {
	private Properties query = new Properties();

	public MyLikeDao() {
		String fileName = MyLikeDao.class.getResource("/sql/myLike/myLike-query.xml").getPath();

		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getLikeNoArrayString(String[] likeNos) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<likeNos.length; i++) {
			if(i != 0) {
				sb.append(",");
			}
			sb.append(Integer.parseInt(likeNos[i]));
		}
		
		return sb.toString();
	}

	public int insertLike(Connection conn, int userNo, int reviewNo) {
		PreparedStatement pstmt = null;
		String sql = query.getProperty("insertLike");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, reviewNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteLike(Connection conn, int userNo, int reviewNo) {
		PreparedStatement pstmt = null;
		String sql = query.getProperty("deleteLike");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, reviewNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getLikeCount(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("likeCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}
	
	public int getCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("personalLikeListCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return result;
	}

	public List<MyLike> selectList(Connection conn, PageInfo pi, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MyLike> myLikeList = new ArrayList<>();
		String sql = query.getProperty("selectList");
		
		try {
			int startItem = (pi.getPage() - 1) * pi.getItemLimit() + 1;
			int endItem = startItem + pi.getItemLimit() - 1;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, userNo);
			pstmt.setInt(3, startItem);
			pstmt.setInt(4, endItem);
			
			rset = pstmt.executeQuery();
			
			int prevReviewNo = 0;
			MyLike myLike = null;
			Review review = null;
			Product product = null;
			List<Option> optionList = null;
			boolean status = true;
			while(rset.next()) {
				int reviewNo = rset.getInt("REVIEW_NO");
				
				if(prevReviewNo != reviewNo) {
					prevReviewNo = reviewNo;

					if(myLike != null) {
						review.getProduct().setOptionList(optionList);
						review.setStatus(status);
						myLike.setReview(review);
						myLikeList.add(myLike);
						status = true;
					}

					product = new Product(rset.getString("PRODUCT_NAME")
										, rset.getString("PRODUCT_IMG")
										, rset.getInt("PRODUCT_BUY_QUANTITY")
										);
		
					review = new Review(rset.getInt("ORDER_SUM")
									  , product
									  );
					myLike = new MyLike(rset.getInt("LIKE_NO")
									  , review);

					optionList = new ArrayList<>();
					if(product.getBuyQuantity() > rset.getInt("PRODUCT_INVENTORY_QUANTITY")) {
						status = false;
					}
				}
				
				Option o = new Option(rset.getString("OPTION_NAME")
									, rset.getInt("OPTION_BUY_QUANTITY")
						   			);
				
				if(o.getName() != null) {
					optionList.add(o);
					
					if(o.getBuyQuantity() > rset.getInt("OPTION_INVENTORY_QUANTITY")) {
						status = false;
					}
				}
			}
			
			if(myLike != null) {
				review.getProduct().setOptionList(optionList);
				review.setStatus(status);
				myLike.setReview(review);
				myLikeList.add(myLike);
				status = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return myLikeList;
	}

	public int deleteLikeList(Connection conn, int userNo, String[] likeNos) {
		PreparedStatement pstmt = null;
		String sql = query.getProperty("deleteLikeList");
		int result = 0;
		
		try {
			sql = sql.replace("LIKE_NO_ARRAY", getLikeNoArrayString(likeNos));
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

}
