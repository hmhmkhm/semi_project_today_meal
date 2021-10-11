package myLike.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.paging.model.vo.PageInfo;

import static common.JDBCTemplate.*;

import myLike.model.dao.MyLikeDao;
import myLike.model.vo.MyLike;

public class MyLikeService {
	private MyLikeDao md = new MyLikeDao();

	public int insertLike(int userNo, int reviewNo) {
		Connection conn = getConnection();
		int result = md.insertLike(conn, userNo, reviewNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);

		return result;
	}

	public int deleteLike(int userNo, int reviewNo) {
		Connection conn = getConnection();
		int result = md.deleteLike(conn, userNo, reviewNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);

		return result;
	}

	public int getLikeCount(int reviewNo) {
		Connection conn = getConnection();
		int result = md.getLikeCount(conn, reviewNo);
		
		close(conn);

		return result;
	}

	public Map<String, Object> selectList(int page, int userNo) {
		Connection conn = getConnection();
		Map<String, Object> map = new HashMap<>();
		int itemCount = md.getCount(conn, userNo);
		PageInfo pi = new PageInfo(page, itemCount, 10, 10);
        List<MyLike> myLikeList = md.selectList(conn, pi, userNo);

        map.put("pi", pi);
        map.put("myLikeList", myLikeList);
		
		close(conn);
		
		return map;
	}

	public int deleteLikeList(int userNo, String[] likeNos) {
		Connection conn = getConnection();
		int result = md.deleteLikeList(conn, userNo, likeNos);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);

		return result;
	}


}
