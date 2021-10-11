package memberModify.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.vo.Member;
import memberModify.model.service.MemberService;

/**
 * Servlet implementation class AccountDeleteServlet
 */
@WebServlet("/accountDelete")
public class AccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// userNo 기준으로 로그인 유저 탈퇴 (DB의 status 컬럼 값을 Y에서 N으로 변경함)
		String view = "";
		
		if(request.getSession().getAttribute("loginUser") != null) {
			
			// userNo - > loginUser에서 가져옴
			int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
			
			// 2. 비즈니스 로직 수행
			Member deleteMember = new MemberService().deleteMember(userNo);
			
			// 성공 시 세션의 로그인 유저 정보 삭제, 성공 메세지 alert 처리, 메인 페이지로 이동
			request.setAttribute("loginUser", deleteMember);
			request.getSession().removeAttribute("loginUser");
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", "회원탈퇴가 성공적으로 진행되었습니다.");
			
			response.sendRedirect(request.getContextPath());
		} else {
			
			// 실패 시 실패 메세지를 가지고 errorpage forward			
			request.setAttribute("msg", "올바르지 않은 요청입니다.");
			view = "WEB-INF/views/common/errorpage.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
