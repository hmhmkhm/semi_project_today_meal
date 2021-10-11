package totalReview.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import totalReview.model.dao.TotalReviewDao;
import totalReview.model.vo.Review;

import static common.JDBCTemplate.*;

public class TotalReviewService {
	private TotalReviewDao trd = new TotalReviewDao();
	
	public int getListCount(List<Integer> categoryList, String keyword) {
		Connection conn = getConnection();
		int result = trd.getListCount(conn, categoryList, keyword);

		close(conn);
		
		return result;
	}

	public List<Review> selectList(int page, List<Integer> categoryList, String st, String keyword, int userNo) {
		Connection conn = getConnection();
		List<Review> reviewList = trd.selectList(conn, page, categoryList, st, keyword, userNo);
		
		close(conn);
		
		return reviewList;
	}

	public Review selectReview(int rno, int userNo) {
		Connection conn = getConnection();

		Review review = trd.selectReview(conn, rno, userNo);

		close(conn);
		
		return review;
	}

	public Map<Integer, Integer> getCategoryListCount(List<Integer> categoryNumberList, String keyword) {
		Connection conn = getConnection();
		
		Map<Integer, Integer> result = trd.getCategoryListCount(conn, categoryNumberList, keyword);

		close(conn);

		return result;
	}
	
	
}
