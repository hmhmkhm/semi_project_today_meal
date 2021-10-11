package event.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import event.model.dao.EventDao;
import event.model.vo.Event;

import common.paging.model.vo.PageInfo;

public class EventService {
	private EventDao ed = new EventDao();
	
	// 검색한 결과 목록 조회 (출력)
	public List<Event> selectList(String searchValue) {
		Connection conn = getConnection();
		
		List<Event> eventList = ed.selectList(conn, searchValue);
		
		close(conn);
		
		return eventList;
	}

	// 전체 목록 조회
	public List<Event> selectList() {
		Connection conn = getConnection();
		List<Event> eventList = ed.selectList(conn);
		close(conn);
		return eventList;
	}
	
	// 조회수 증가
		public int increaseCount(int eno) {
			Connection conn = getConnection();
			
			int result = ed.increaseCount(conn, eno);
			
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			
			close(conn);
			
			return result;
		}

	// 이벤트 상세 정보 페이지 조회
	public Event selectEvent(int eno) {
		Connection conn = getConnection();
		
		Event e = ed.selectEvent(conn, eno);
		
		close(conn);
		
		return e;
		
	}

	// 이벤트 글 작성 메소드
	public int insertEvent(Event e) {
		Connection conn = getConnection();
		int result = ed.insertEvent(conn, e);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	// 이벤트 게시글 삭제
	public int deleteEvent(int eno) {
		Connection conn = getConnection();
		
		int result = ed.deleteEvent(conn, eno);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 이벤트 수정하기
	public int udpateEvent(Event e) {
		Connection conn = getConnection();
		
		int result = ed.updateEvent(conn, e);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public Map<String, Object> selectListPage(int page, String searchValue) {
		Connection conn = getConnection();
		
		// 1. 게시글 총 개수 구하기
		int listCount = ed.getListCountPage(conn, searchValue);
		//System.out.println("listCount : " + listCount);
		
		// 2. PageInfo 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 8, 10);
		//System.out.println("pi : " + pi);
		
		// 3. 페이징 처리가 된 게시글 목록 조회
		List<Event> eventlist = ed.selectListPage(conn, pi, searchValue);
		
		// 리턴용 Map 선언
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("eventlist", eventlist);
		
		return returnMap;
	}

	// 페이징처리
	public Map<String, Object> selectList(int page) {
		Connection conn = getConnection();
		
		// 1. 게시글 총 개수 구하기
		int listCount = ed.getListCount(conn);
		//System.out.println("listCount : " + listCount);
		
		// 2. PageInfo 객체 만들기
		PageInfo pi = new PageInfo(page, listCount, 10, 8);
		//System.out.println("pi : " + pi);
		
		// 3. 페이징 처리가 된 게시글 목록 조회
		List<Event> eventList = ed.selectList(conn, pi);
		//System.out.println("boardList : " + boardList);
		
		// 리턴용 Map 선언
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("eventList", eventList);
		
		return returnMap;
	}
	

}
