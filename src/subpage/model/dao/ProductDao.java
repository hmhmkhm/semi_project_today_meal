package subpage.model.dao;

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

import common.paging.model.vo.PageInfo;
import subpage.model.vo.Product;
import subpage.model.vo.Search;


public class ProductDao {
	private Properties query = new Properties(); 

	public ProductDao() {
		String fileName = ProductDao.class.getResource("/sql/subpage/subpage-query.xml").getPath();

		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String selectCategoryName(Connection conn, int cno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String result = null;
		String sql = query.getProperty("selectCategoryName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getString("CATEGORY_NAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}
	
	// product 총 개수 
	public int getListCount(Connection conn, int cno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("getListCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cno);
			
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

	public List<Product> selectList(Connection conn, PageInfo pi, int cno, String st) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> productList = new ArrayList<>();
		String sql = null;
		
		if(st.equals("recent")) {
			sql = query.getProperty("selectRecentList");
		}else if(st.equals("satisfaction")) {
			sql = query.getProperty("selectSatisfactionList");
		}else if(st.equals("hightprice")) {
			sql = query.getProperty("selectHightpriceList");
		}else if(st.equals("lowprice")) {
			sql = query.getProperty("selectLowpriceList");
		}
		
		try {
			int parameterIndex = 1;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(parameterIndex++, cno);

			int startItem = (pi.getPage() - 1) * pi.getItemLimit() + 1;
			int endItem = startItem + pi.getItemLimit() - 1;
			
			pstmt.setInt(parameterIndex++, startItem);
			pstmt.setInt(parameterIndex++, endItem);
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				Product p = new Product(rset.getInt("PRODUCT_NO"),
										rset.getString("PRODUCT_NAME"),
										rset.getString("PRODUCT_IMG"),
										rset.getInt("PRODUCT_PRICE"),
										rset.getDate("PRODUCT_DATE"),
										rset.getString("SOLD_OUT"));

				productList.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return productList;
	}
	
	//=======================================================================================================================
	public int getSearchListCount(Connection conn, int cno, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("getListCount");
		int result = 0;
		
		
		// search
		if(s.getSearchBox() != null) {
			sql = query.getProperty("getListSearch");
		} 
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cno);
			//  search
			if(s.getSearchBox() != null) {
				pstmt.setString(1, s.getSearchBox());
			} 
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

	
	public List<Product> searchList(Connection conn, PageInfo pi, int cno, String st, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> productList = new ArrayList<>();
		String sql = null;
		
		if(st.equals("recent")) {
			sql = query.getProperty("selectRecentList");
		}else if(st.equals("satisfaction")) {
			sql = query.getProperty("selectSatisfactionList");
		}else if(st.equals("hightprice")) {
			sql = query.getProperty("selectHightpriceList");
		}else if(st.equals("lowprice")) {
			sql = query.getProperty("selectLowpriceList");
		}
		
		
		try {
			int parameterIndex = 1;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(parameterIndex++, cno);
			// search
			if(s.getSearchBox() != null) {
				pstmt.setString(1, s.getSearchBox());
			} 

			int startItem = (pi.getPage() - 1) * pi.getItemLimit() + 1;
			int endItem = startItem + pi.getItemLimit() - 1;
			
			pstmt.setInt(parameterIndex++, startItem);
			pstmt.setInt(parameterIndex++, endItem);
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				Product p = new Product(rset.getInt("PRODUCT_NO"),
										rset.getString("PRODUCT_NAME"),
										rset.getString("PRODUCT_IMG"),
										rset.getInt("PRODUCT_PRICE"),
										rset.getDate("PRODUCT_DATE"),
										rset.getString("SOLD_OUT"));

				productList.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return productList;
	}
	

	
}
