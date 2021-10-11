package event.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import event.model.service.EventService;
import event.model.vo.Event;

/**
 * Servlet implementation class EventUpdateViewServlet
 */
@WebServlet("/event/updateView")
public class EventUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eno = Integer.parseInt(request.getParameter("eno"));
		
		Event e = new EventService().selectEvent(eno);
		
		if(e != null) {
			request.setAttribute("e", e);
			request.getRequestDispatcher("/WEB-INF/views/event/eventUpdateView.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "수정할 게시글을 불러오는데 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int eno = Integer.parseInt(request.getParameter("eno"));
//		
//		Event e = new EventService().selectEvent(eno);
//		
//		if(e != null) {
//			request.setAttribute("e", e);
//			request.getRequestDispatcher("/WEB-INF/views/event/eventUpdateView.jsp").forward(request, response);
//		} else {
//			request.setAttribute("msg", "수정할 게시글을 불러오는데 실패하였습니다.");
//			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
//		}
	}

}
