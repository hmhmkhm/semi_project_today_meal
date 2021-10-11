package salesAnalysis.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import common.paging.model.vo.PageInfo;
import salesAnalysis.model.vo.ProductDetail;
import salesAnalysis.model.vo.ProductOption;
import salesAnalysis.model.vo.Ranking;
import salesAnalysis.model.vo.Receipt;
import salesAnalysis.model.vo.SalesData;

public class ReceiptDao {
private Properties query = new Properties();
	
	public ReceiptDao() {
		String fileName = ReceiptDao.class.getResource("/sql/salesAnalysis/salesAnalysis-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getReceiptListCount(Connection conn, int userNo, String searchStart, String searchEnd) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getReceiptListCount"); // default count (mypage main query)
		
		// 검색 조건과 값이 잘 넘어왔을때
		if(searchStart != null && searchEnd != null) {
			
			if(searchStart.length() == 8 && searchEnd.length() == 8) {
				sql = query.getProperty("getDailyReceiptListCount");	// Daily sales searching...
			} else if(searchStart.length() == 6 && searchEnd.length() == 6) {
				sql = query.getProperty("getMonthlyReceiptListCount");	// Monthly sales searching...
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			if(searchStart != null && searchEnd != null) {
				pstmt.setString(2, searchStart);
				pstmt.setString(3, searchEnd);
			}
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	public List<Receipt> selectReceiptList(Connection conn, int userNo, PageInfo pi, String searchStart,
			String searchEnd) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Receipt> receiptList = new ArrayList<>();
		//String sql = query.getProperty("selectRecentReceiptList");
		String sql = query.getProperty("selectRecentReceiptList4"); // TEST
		
		// 검색 조건과 값이 잘 넘어왔을때
		if(searchStart != null && searchEnd != null) {
			if(searchStart.length() == 8 && searchEnd.length() == 8) {
				sql = query.getProperty("selectDailyReceiptList3");	// Daily sales searching...
			} else if(searchStart.length() == 6 && searchEnd.length() == 6) {
				sql = query.getProperty("selectMonthlyReceiptList");	// Monthly sales searching...
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (pi.getPage() - 1) * pi.getItemLimit() + 1;
			int endRow = startRow + pi.getItemLimit() - 1;
			int paramIndex = 1;
			pstmt.setInt(paramIndex++, userNo);
			if(searchStart != null && searchEnd != null) {
				pstmt.setString(paramIndex++, searchStart);
				pstmt.setString(paramIndex++, searchEnd);
			}
			
//			pstmt.setInt(paramIndex++, userNo); //TEST
			
//			if(searchStart != null && searchEnd != null) { //TEST
//				pstmt.setString(paramIndex++, searchStart);
//				pstmt.setString(paramIndex++, searchEnd);
//			}
			
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
//			pstmt.setInt(paramIndex++, 1);
//			pstmt.setInt(paramIndex++, 1);
			rset = pstmt.executeQuery();
			
			int orderCnt = 0;
			int psetCnt = 0;
			int optSetCnt = 0;
			
			List<ProductOption> optList = new ArrayList<>();
			List<ProductDetail> productList = new ArrayList<>();
			
			while(rset.next()) {
				
				orderCnt = rset.getInt("order_count");
				
				if(rset.getString("option_yn").equalsIgnoreCase("Y")) {
					optList.add(new ProductOption(rset.getString("option_name"), rset.getInt("option_qty")));
					optSetCnt++;
					if(rset.getInt("opt_count") == optSetCnt) {
						
						List<ProductOption> copyOptList = new ArrayList<>();
						
						for(ProductOption opt : optList) {
							copyOptList.add(new ProductOption(opt.getOptionName(), opt.getOptionQty()));
						}
						
						ProductDetail pd = new ProductDetail(rset.getString("category_name"), rset.getInt("p_no"), rset.getString("product_name"),
	 							 							 rset.getString("product_img"), rset.getInt("product_qty"), copyOptList);
						productList.add(pd);
						optList.clear();
						psetCnt++;
						optSetCnt = 0;
						
					}
				} else {
					
					ProductDetail pd = new ProductDetail(rset.getString("category_name"), rset.getInt("p_no"), rset.getString("product_name"),
 							 rset.getString("product_img"), rset.getInt("product_qty"));
					productList.add(pd);
					psetCnt++;
					
				}
				if(orderCnt == psetCnt) {
					
					List<ProductDetail> copyProductList = new ArrayList<>();
					for(int i=0; i < productList.size();i++) {
						copyProductList.add(productList.get(i));
					}
					
					receiptList.add(new Receipt(rset.getInt("order_count"),
												rset.getInt("order_no"),
												rset.getDate("sale_date"),
												rset.getInt("order_sum"),
												rset.getInt("product_no"),
												rset.getInt("order_state_no"),
												copyProductList));
					productList.clear();
					psetCnt = 0;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return receiptList;
	}
	
	public List<SalesData> selectSalesAnalysis(Connection conn, String searchStart, String searchEnd) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<SalesData> salesList = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		SimpleDateFormat df2 = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월");
		
		DecimalFormat fm = new DecimalFormat("###,###");
		
		String sql = query.getProperty("selectDailySalesAnalysistList");
		
		if(searchStart.length() == 6 && searchEnd.length() == 6) {
			sql = query.getProperty("selectMonthlySalesAnalysistList");	// Monthly sales searching...
		}
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchStart);
			pstmt.setString(2, searchEnd);
			pstmt.setString(3, searchStart);
			pstmt.setString(4, searchEnd);
			pstmt.setString(5, searchStart);
			pstmt.setString(6, searchEnd);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				// daily sales analysis
				if(searchStart.length() == 8 && searchEnd.length() == 8) {
					try {
						salesList.add(new SalesData(sdf.format(df.parse(rset.getString("sale_date"))),
													rset.getString("sale_day"),
													fm.format(rset.getInt("count_sales")),
													fm.format(rset.getInt("total_sales")),
													fm.format(rset.getInt("actual_sales")),
													fm.format(rset.getInt("cancel_sales")),
													fm.format(rset.getInt("return_sales"))));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				// monthly sales analysis
				if(searchStart.length() == 6 && searchEnd.length() == 6) {
					
					try {
						salesList.add(new SalesData(sdf2.format(df2.parse(rset.getString("sale_date"))),
													fm.format(rset.getInt("count_sales")),
													fm.format(rset.getInt("total_sales")),
													fm.format(rset.getInt("actual_sales")),
													fm.format(rset.getInt("cancel_sales")),
													fm.format(rset.getInt("return_sales"))));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return salesList;
	}
	
	public List<Ranking> selectRankingTop5(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectRankingTop5Ver3");
		List<Ranking> rankingList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				rankingList.add(new Ranking(rset.getString("category_name"),
											rset.getString("product_name"),
											rset.getString("product_img"),
											rset.getInt("quantity"),
											rset.getInt("total")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rankingList;
	}
	public int cancelProduct(Connection conn, int orderNo) {
		
		PreparedStatement pstmt = null;
		String sql = query.getProperty("cancelProduct");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, orderNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
}
