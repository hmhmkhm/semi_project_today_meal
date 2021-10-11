package saleManagement.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import common.paging.model.vo.PageInfo;
import saleManagement.model.vo.Delivery;
import saleManagement.model.vo.Payment;
import saleManagement.model.vo.Product;
import saleManagement.model.vo.Receipt;
import saleManagement.model.vo.Option;

public class SaleManagementDao {
	private Properties query = new Properties();
	
	public SaleManagementDao() {
		String fileName = SaleManagementDao.class.getResource("/sql/saleManagement/saleManagement-query.xml").getPath();
		
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getReceiptNoArrayString(String[] receiptNo) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<receiptNo.length; i++) {
			if(i != 0) {
				sb.append(",");
			}
			sb.append(Integer.parseInt(receiptNo[i]));
		}
		
		return sb.toString();
	}

	public int getListCount(Connection conn, Date startDate, Date endDate, String userId, String orderNumber, int orderStatus) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("listCount");
		int result = 0;
		
		try {
			int parameterIndex = 1;

			if(userId.equals("")) {
				sql = sql.replace("AND USER_ID = ?", "");
			}
			
			if(orderNumber.equals("")) {
				sql = sql.replace("AND ORDER_NO = ?", "");
			}
			
			if(orderStatus == 0 || orderStatus == 5) {
				sql = sql.replace("AND ORDER_STATE_NO = ?", "");
			}
			
			if(orderStatus != 5) {
				sql = sql.replace("AND RECEIPT.PRODUCT_NO = 2", "");
			}
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(parameterIndex++, (new java.sql.Date(startDate.getTime())).toString());
			pstmt.setString(parameterIndex++, (new java.sql.Date(endDate.getTime())).toString());
			
			if(!userId.equals("")) {
				pstmt.setString(parameterIndex++, userId);
			}
			
			if(!orderNumber.equals("")) {
				pstmt.setInt(parameterIndex++, Integer.parseInt(orderNumber));
			}
			
			if(orderStatus != 0 && orderStatus != 5) {
				pstmt.setInt(parameterIndex++, orderStatus);
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

	public List<Receipt> selectList(Connection conn, PageInfo pi, Date startDate, Date endDate, String userId, String orderNumber, int orderStatus) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Receipt> receiptList = new ArrayList<>();
		String sql = query.getProperty("selectList");

		try {
			int parameterIndex = 1;
			int startItem = (pi.getPage() - 1) * pi.getItemLimit() + 1;
			int endItem = startItem + pi.getItemLimit() - 1;

			if(userId.equals("")) {
				sql = sql.replace("AND USER_ID = ?", "");
			}
			
			if(orderNumber.equals("")) {
				sql = sql.replace("AND ORDER_NO = ?", "");
			}
			
			if(orderStatus == 0 || orderStatus == 5) {
				sql = sql.replace("AND ORDER_STATE_NO = ?", "");
			}
			
			if(orderStatus != 5) {
				sql = sql.replace("AND RECEIPT.PRODUCT_NO = 2", "");
			}

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(parameterIndex++, (new java.sql.Date(startDate.getTime())).toString());
			pstmt.setString(parameterIndex++, (new java.sql.Date(endDate.getTime())).toString());
			
			if(!userId.equals("")) {
				pstmt.setString(parameterIndex++, userId);
			}
			
			if(!orderNumber.equals("")) {
				pstmt.setInt(parameterIndex++, Integer.parseInt(orderNumber));
			}
			
			
			if(orderStatus != 0 && orderStatus != 5) {
				pstmt.setInt(parameterIndex++, orderStatus);
			}
			
			pstmt.setInt(parameterIndex++, startItem);
			pstmt.setInt(parameterIndex++, endItem);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product product = new Product(rset.getString("PRODUCT_NAME"));

				Receipt receipt =  new Receipt(rset.getInt("ORDER_NO")
											 , rset.getDate("SALE_DATE")
											 , rset.getInt("ORDER_SUM")
											 , rset.getString("USER_ID")
											 , product
											 , rset.getInt("ORDER_STATE_NO")
											 , rset.getString("ORDER_STATE_NAME")
											);
				if(orderStatus == 5) {
					receipt.setOrderState("환불 요청");
					receipt.setOrderStateNo(5);
				}

				receiptList.add(receipt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return receiptList;
	}

	public Receipt selectReceipt(Connection conn, int rno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectReceipt");
		Receipt receipt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				if(receipt == null) {
					Product product = new Product(rset.getString("PRODUCT_NAME")
												, rset.getInt("PRODUCT_BUY_QUANTITY")
												, rset.getInt("PRODUCT_PRICE")
												, new ArrayList<>()
												);

					Delivery delivery = new Delivery(rset.getString("DELIVERY_NAME")
												   , rset.getString("PHONE")
												   , rset.getString("ADDRESS")
												   , rset.getString("DREQUEST")
												   , rset.getString("DELIVERY_FEE")
												   );
					Payment payment = new Payment(rset.getString("IMP_UID")
												, rset.getString("PAY_METHOD")
												, rset.getInt("PAID_AMOUNT")
												, rset.getTimestamp("PAID_AT")
												, rset.getString("STATUS")
												);
					
					receipt = new Receipt(rset.getInt("ORDER_NO")
										, rset.getDate("SALE_DATE")
										, rset.getInt("COIN")
										, rset.getDate("DELIVERY_DATE")
										, rset.getInt("ORDER_SUM")
										, product
										, rset.getString("ORDER_STATE_NAME")
										, delivery
										, payment
										);
				}
				
				Option option = new Option(rset.getString("OPTION_NAME")
										 , rset.getInt("OPTION_BUY_QUANTITY")
										 , rset.getInt("OPTION_PRICE")
										);
				
				if(option.getName() != null) {
					receipt.getProduct().getOptionList().add(option);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return receipt;
	}

	public int changeStatus(Connection conn, String[] receiptNos, int changeStatus) {
		PreparedStatement pstmt = null;
		String sql = query.getProperty("changeStatus");
		int result = 0;
		
		try {
			sql = sql.replace("RECEIPT_NO_ARRAY", getReceiptNoArrayString(receiptNos));
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, changeStatus);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
