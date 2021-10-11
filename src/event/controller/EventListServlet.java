package event.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import event.model.service.EventService;
import event.model.vo.Event;


/**
 * Servlet implementation class EventListServelt
 */
@WebServlet("/event/list")
public class EventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		
		request.setCharacterEncoding("utf-8");
		String searchValue = request.getParameter("searchValue");
		List<Event> eventList;
		
		if(searchValue != null) {
			// 검색 된 리스트 조회
			eventList = new EventService().selectList(searchValue);
		} else {
			eventList = new EventService().selectList();
		}
		request.setAttribute("eventList", eventList);
		
		request.getRequestDispatcher("/WEB-INF/views/event/eventListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
