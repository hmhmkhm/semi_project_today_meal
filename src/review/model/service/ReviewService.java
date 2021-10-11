package review.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import productInfo.model.vo.Product;
import review.model.dao.ReviewDao;
import review.model.vo.Review;

public class ReviewService {

	 //리뷰 작성하기 메소드
	public int insertReview(Review r) {
		Connection conn = getConnection();
		int result = new ReviewDao().insertReview(conn, r);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 리뷰 전체 목록 출력
	public List<Review> selectList() {
		Connection conn = getConnection();
		List<Review> reviewList = new ReviewDao().selectList(conn);
		close(conn);
		return reviewList;
	}
	
	// 특정 리뷰 조회하기
	public Review selectReview(int rno) {
		Connection conn = getConnection();
		Review r = new ReviewDao().selectReview(conn, rno);
		
		close(conn);
		
		return r;
	}

	// 마이페이지 리뷰 목록 출력
	public List<Review> selectMyReviewList() {
		Connection conn = getConnection();
		List<Review> reviewList = new ReviewDao().selectMyReviewList(conn);
		close(conn);
		return reviewList;
	}

	// 리뷰 수정
	public int udpateReview(Review r) {
		Connection conn = getConnection();
		
		int result = new ReviewDao().updateReview(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	// 리뷰 삭제
	public int deleteReview(int rno) {
		Connection conn = getConnection();
		
		int result = new ReviewDao().deleteReview(conn, rno);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 리뷰 별점순 정렬
	public Review selectReviewPoint() {
		Connection conn = getConnection();
		
		Review r = new ReviewDao().selectReviewPoint(conn);
		
		close(conn);
		
		return r;
	}

	public List<Review> selectMyReviewProductList(int userNo) {
		Connection conn = getConnection();
		
		List<Review> r = new ReviewDao().selectMyReviewProductList(conn,userNo);
		
		close(conn);
		
		return r;
	}
	
	public List<Review> selectMyReviewProductListDesc(int userNo) {
		Connection conn = getConnection();
		
		List<Review> r = new ReviewDao().selectMyReviewProductListDesc(conn,userNo);
		
		close(conn);
		
		return r;
	}

	public List<Review> selectMyReviewProductListAsc(int userNo) {
		Connection conn = getConnection();
		
		List<Review> r = new ReviewDao().selectMyReviewProductListAsc(conn,userNo);
		
		close(conn);
		
		return r;
	}

}
