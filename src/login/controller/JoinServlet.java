package login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.model.vo.Member;
import login.model.service.MemberService;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/login/joinpage.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		String questionAnswer = request.getParameter("questionAnswer");
		String questionNo = request.getParameter("questionNo");
			
		
		Member mem = new Member(userId, userPwd, userName, phone, email, questionNo, questionAnswer, address1, address2, address3);
		
		
		
		int result = new MemberService().insertMember(mem);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "??????????????? ?????????????????????. ????????? ????????????.");
			response.sendRedirect(request.getContextPath());	
		} else {
			request.setAttribute("msg", "??????????????? ?????????????????????.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/errorpage.jsp");
			view.forward(request, response);
		}
		
	}

}
