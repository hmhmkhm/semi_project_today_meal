package memberModify.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.model.vo.Member;
import memberModify.model.service.MemberService;



/**
 * Servlet implementation class MemberModifyServlet
 */
@WebServlet("/memberModify")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		request.getRequestDispatcher("/WEB-INF/views/memberModifyForm/memberModifyForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String phone = request.getParameter("phone");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		String questionNo = request.getParameter("questionNo");
		String pwdAnswer = request.getParameter("pwdAnswer");
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Member m = new Member(userNo, phone, questionNo, pwdAnswer, address1, address2, address3);
		Member updateMember = new MemberService().updateMember(m);
		
		if(updateMember != null) {
			
			request.getSession().setAttribute("loginUser", updateMember);
			request.setAttribute("result", "success");
			request.getRequestDispatcher("WEB-INF/views/memberModifyForm/memberModifyForm.jsp").forward(request, response);
		} else {
			
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("WEB-INF/views/memberModifyForm/memberModifyForm.jsp").forward(request, response);
		}
		
	}

}
