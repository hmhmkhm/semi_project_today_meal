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
 * Servlet implementation class EmailModifyServlet
 */
@WebServlet("/emailModify")
public class EmailModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uEmail = ((Member)request.getSession().getAttribute("loginUser")).getEmail();
		
		request.setAttribute("uEmail", uEmail);
		
		request.getRequestDispatcher("/WEB-INF/views/memberModifyForm/emailModifyForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mac = request.getParameter("mac");
		String newEmail = request.getParameter("newEmail");
		String userMac = request.getParameter("userMac");
		
		if(!mac.equals(userMac)) {
			String uEmail = ((Member)request.getSession().getAttribute("loginUser")).getEmail();
			request.setAttribute("result", "fail");
			request.setAttribute("uEmail", uEmail);
			request.setAttribute("userMac", userMac);
			request.setAttribute("newEmail", newEmail);
			request.setAttribute("mac", mac);
			request.getRequestDispatcher("/WEB-INF/views/memberModifyForm/emailModifyForm.jsp").forward(request, response);
			return;
		}
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		Member updateMember = new MemberService().updateEmail(userNo, newEmail);
		
		
		if(updateMember != null) {
			request.setAttribute("result", "success");
			request.getSession().setAttribute("loginUser", updateMember);
		} else {
			String uEmail = ((Member)request.getSession().getAttribute("loginUser")).getEmail();
			request.setAttribute("uEmail", uEmail);
			request.setAttribute("result", "fail");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/memberModifyForm/emailModifyForm.jsp").forward(request, response);
	}

}
