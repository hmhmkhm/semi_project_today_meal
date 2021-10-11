package event.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import event.model.service.EventService;
import event.model.vo.Event;

/**
 * Servlet implementation class EventDetailServlet
 */
@WebServlet("/event/detail")
public class EventDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eno = Integer.parseInt(request.getParameter("eno"));
		
		
		Cookie[] cookies =  request.getCookies();
		
		// bcount라는 쿠키 값 담을 변수
		String bcount = "";
		
		// 쿠키가 잘 넘어왔다면
		if(cookies != null && cookies.length > 0) {
			// 쿠키 값 배열을 반복하며 탐색
			for(Cookie c : cookies) {
				// 읽은 게시물 정보를 저장해두는 쿠키의 이름 bcount가 있는지 확인
				if(c.getName().equals("bcount")) {
					bcount = c.getValue();
				}
			}
		}
		
		// 처음 읽는 게시글인 경우
		if(bcount.indexOf("|"+eno+"|") == -1) {
			// 해당 게시글 번호를 붙여서
			Cookie newBcount = new Cookie("bcount", bcount + "|" + eno+"|");
			// 유효 기간 설정 가능 (setMaxAge) -> 초단위
			//newBcount.setMaxAge(1 * 24 * 60 * 60);
			// 응답에 쿠키를 담음
			response.addCookie(newBcount);
			// 조회수 증가 로직 실행
			int result = new EventService().increaseCount(eno);
			
			if(result > 0) {
				System.out.println("조회수 증가 성공");
			} else {
				System.out.println("조회수 증가 실패");
			}
		} //else {
//			System.out.println("다시 조회 시 조회수 증가하지 않음");
//		}
		
		Event e = new EventService().selectEvent(eno);
		String page = "";
		if(e != null) {
			request.setAttribute("event", e);
			page = "/WEB-INF/views/event/eventDetailView.jsp";
		} else {
			request.setAttribute("msg", "이벤트 상세 페이지를 불러오는데 실패하였습니다.");
			page = "/WEB-INF/views/common/erroerpage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		//request.getRequestDispatcher("/WEB-INF/views/event/eventDetailView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
