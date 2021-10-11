package salesAnalysis.model.service;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.paging.model.vo.PageInfo;
import salesAnalysis.model.dao.ReceiptDao;
import salesAnalysis.model.vo.Ranking;
import salesAnalysis.model.vo.Receipt;
import salesAnalysis.model.vo.SalesData;
public class ReceiptService {
	
	private ReceiptDao rd = new ReceiptDao();
	
	public Map<String, Object> selectReceipts(int userNo, int page, String searchStart, String searchEnd) {
		Connection conn = getConnection();
		
		// 1. 영수증 총 개수 구하기
		int listCount = rd.getReceiptListCount(conn, userNo, searchStart, searchEnd); 
		
		// 2. PageInfo 객체 만들기
		int boardLimit = 3;
		if(searchStart != null && searchEnd != null) {
			boardLimit = 6;
		}
		
		PageInfo pi = new PageInfo(page, listCount, 3, boardLimit);
		List<Receipt> receiptList = rd.selectReceiptList(conn, userNo, pi, searchStart, searchEnd);
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("pi", pi);
		returnMap.put("receiptList", receiptList);
		
		return returnMap;
	}

	public List<SalesData> selectSalesAnalysis(String searchStart, String searchEnd) {
		
		Connection conn = getConnection();
		
		List<SalesData> salesList = rd.selectSalesAnalysis(conn, searchStart, searchEnd);
		
		close(conn);
		
		return salesList;
		
	}

	public List<Ranking> selectRankingTop5() {
		
		Connection conn = getConnection();
		
		List<Ranking> rankingList = rd.selectRankingTop5(conn);
		
		close(conn);
		
		return rankingList;
	}

	public int cancelProduct(int orderNo) {
		Connection conn = getConnection();
		int result = rd.cancelProduct(conn, orderNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

}
