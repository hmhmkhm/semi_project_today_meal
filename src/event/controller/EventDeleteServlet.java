package event.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import event.model.service.EventService;

/**
 * Servlet implementation class EventDeleteServlet
 */
@WebServlet("/event/delete")
public class EventDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eno = Integer.parseInt(request.getParameter("eno"));
		String img = request.getParameter("content");
		int result = new EventService().deleteEvent(eno);
		
		if(result > 0) {
			String root = request.getSession().getServletContext().getRealPath("/");
				File failedFile = new File(root + "\\resources\\uploadFiles\\event\\"+img);
				failedFile.delete();
			response.sendRedirect(request.getContextPath() + "/admin/eventlist");
		} else {
			request.setAttribute("msg", "이벤트 게시 글 삭제에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
