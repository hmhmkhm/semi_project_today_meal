package totalReview.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import totalReview.model.vo.Option;
import totalReview.model.vo.Product;
import totalReview.model.vo.Review;


public class TotalReviewDao {
	private Properties query = new Properties();

	public TotalReviewDao() {
		String fileName = TotalReviewDao.class.getResource("/sql/totalReview/review-query.xml").getPath();
		
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getCategoryNoArrayString(List<Integer> categoryList) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<categoryList.size(); i++) {
			if(i != 0) {
				sb.append(",");
			}
			sb.append(categoryList.get(i));
		}
		
		return sb.toString();
	}
	
	public int getListCount(Connection conn, List<Integer> categoryList, String keyword) {
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rset = null;
		int result = 0;
		
		try {
			if(categoryList.size() > 0) {
				sql = query.getProperty("categoryListTotalCount");
				sql = sql.replace("CATEGORY_NO_ARRAY", getCategoryNoArrayString(categoryList));
			} else {
				sql = query.getProperty("listCount");
			}
			
			if(keyword.equals("")) {
				sql = sql.replace("AND REVIEW_TEXT LIKE '%KEYWORD%' OR PRODUCT_NAME LIKE '%KEYWORD%'", "");
			} else {
				sql = sql.replace("KEYWORD", keyword);
			}
			
			pstmt = conn.prepareStatement(sql);	
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public List<Review> selectList(Connection conn, int page, List<Integer> categoryList, String st, String keyword, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> reviewList = new ArrayList<>();
		String sql = query.getProperty("selectList");
		
		try {
			int parameterIndex = 1;

			if(userNo > 0) {
				sql = query.getProperty("selectListJoinLikeManagement");
			}

			if(categoryList.size() > 0) {
				sql = sql.replace("CATEGORY_NO_ARRAY", getCategoryNoArrayString(categoryList));
			} else {
				sql = sql.replace("AND CATEGORY_NO IN (CATEGORY_NO_ARRAY)", "");
			}
			
			String orderStatus = ""; 
			switch(st) {
				case "popular" : orderStatus = "REVIEW_COUNT DESC"; break;
				case "satisfaction" : orderStatus = "POINT DESC"; break;
				case "hightprice" : orderStatus = "ORDER_SUM DESC"; break;
				case "lowprice" : orderStatus = "ORDER_SUM ASC"; break;
				default : orderStatus = "REVIEW_NO DESC"; break;
			}
			sql = sql.replace("ORDER_STATUS", orderStatus);

			if(keyword.equals("")) {
				sql = sql.replace("AND REVIEW_TEXT LIKE '%KEYWORD%' OR PRODUCT_NAME LIKE '%KEYWORD%'", "");
			} else {
				sql = sql.replace("KEYWORD", keyword);
			}

			pstmt = conn.prepareStatement(sql);
			if(userNo > 0) {
				pstmt.setInt(parameterIndex++, userNo);
			}

			pstmt.setInt(parameterIndex++, (page-1)*9+1);
			pstmt.setInt(parameterIndex++, page*9);
			
			rset = pstmt.executeQuery();
			
			int prevReviewNo = 0;
			Product p = null;
			Review r = null;
			List<Option> optionList = null;
			boolean status = true;
			boolean liked = false;
			while(rset.next()) {
				int reviewNo = rset.getInt("REVIEW_NO");

				if(prevReviewNo != reviewNo) {
					prevReviewNo = reviewNo;

					if(r != null) {
						r.getProduct().setOptionList(optionList);
						r.setStatus(status);
						if(userNo > 0) {
							r.setLiked(liked);	
						}
						reviewList.add(r);
						status = true;
					}

					p = new Product(rset.getString("PRODUCT_NAME")
								  , rset.getString("PRODUCT_IMG")
								  , rset.getInt("PRODUCT_BUY_QUANTITY")
								  );
		
					r = new Review(reviewNo
							, rset.getFloat("POINT")
							, rset.getString("REVIEW_IMAGE")
							, rset.getInt("ORDER_SUM")
							, p
							);

					optionList = new ArrayList<>();
					if(p.getBuyQuantity() > rset.getInt("PRODUCT_INVENTORY_QUANTITY")) {
						status = false;
					}
					
					if(userNo > 0) {
						liked = rset.getString("LIKED").equals("Y") ? true : false;
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

			if(r != null) {
				r.getProduct().setOptionList(optionList);
				r.setStatus(status);
				if(userNo > 0) {
					r.setLiked(liked);	
				}
				reviewList.add(r);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return reviewList;
	}

	public Review selectReview(Connection conn, int rno, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectReview");
		Review review = null;
		
		try {
			int parameterIndex = 1;

			if(userNo > 0) {
				sql = query.getProperty("selectReviewJoinLikeManagement");
			}

			pstmt = conn.prepareStatement(sql);
			
			if(userNo > 0) {
				pstmt.setInt(parameterIndex++, userNo);
			}
			pstmt.setInt(parameterIndex++, rno);
			
			rset = pstmt.executeQuery();
			
			List<Option> optionList = null;
			boolean status = true;
			while(rset.next()) {
				if(review == null) {
					Product p = new Product(rset.getString("PRODUCT_NAME")
										  , rset.getString("PRODUCT_IMG")
										  , rset.getInt("PRODUCT_BUY_QUANTITY")
										  );

					review = new Review(rset.getInt("REVIEW_NO")
									  , rset.getFloat("POINT")
									  , rset.getString("REVIEW_TEXT")
									  , rset.getString("REVIEW_IMAGE")
									  , rset.getDate("REVIEW_REGISTER")
									  , rset.getString("USER_ID")
									  , rset.getInt("ORDER_SUM")
									  , p
									  , rset.getInt("REVIEW_COUNT")
									  );

					optionList = new ArrayList<>();

					if(p.getBuyQuantity() > rset.getInt("PRODUCT_INVENTORY_QUANTITY")) {
						status = false;
					}
					
					if(userNo > 0) {
						review.setLiked(rset.getString("LIKED").equals("Y") ? true : false);
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
			review.getProduct().setOptionList(optionList);
			review.setStatus(status);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn);
			close(pstmt);
		}

		return review;
	}

	public Map<Integer, Integer> getCategoryListCount(Connection conn, List<Integer> categoryList, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Map<Integer, Integer> result = new HashMap<>();
		String sql = query.getProperty("categoryListCount");
		
		for(int i : categoryList) {
			result.put(i, 0);
		}
		
		try {
			sql = sql.replace("CATEGORY_NO_ARRAY", getCategoryNoArrayString(categoryList));

			if(keyword.equals("")) {
				sql = sql.replace("AND REVIEW_TEXT LIKE '%KEYWORD%' OR PRODUCT_NAME LIKE '%KEYWORD%'", "");
			} else {
				sql = sql.replace("KEYWORD", keyword);
			}

			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result.put(rset.getInt(1), rset.getInt(2));
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
