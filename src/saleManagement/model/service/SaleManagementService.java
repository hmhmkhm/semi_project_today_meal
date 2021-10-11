package saleManagement.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.paging.model.vo.PageInfo;
import saleManagement.model.dao.SaleManagementDao;
import saleManagement.model.vo.Receipt;

public class SaleManagementService {
	private SaleManagementDao smd = new SaleManagementDao();

	public Map<String, Object> selectList(int page, Date startDate, Date endDate, String userId, String orderNumber, int orderStatus) {
		Connection conn = getConnection();
		Map<String, Object> map = new HashMap<>();
		int itemCount = smd.getListCount(conn, startDate, endDate, userId, orderNumber, orderStatus);

		PageInfo pi = new PageInfo(page, itemCount, 10, 20);
		List<Receipt> receiptList = smd.selectList(conn, pi, startDate, endDate, userId, orderNumber, orderStatus);
		
		map.put("pi", pi);
		map.put("receiptList", receiptList);

		close(conn);

		return map;
	}

	public Receipt selectReceipt(int rno) {
		Connection conn = getConnection();
		Receipt receipt = smd.selectReceipt(conn, rno);
		
		close(conn);
		
		return receipt;
	}

	public int changeStatus(String[] receiptNos, int chageStatus) {
		Connection conn = getConnection();
		
		int result = smd.changeStatus(conn, receiptNos, chageStatus);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


}
