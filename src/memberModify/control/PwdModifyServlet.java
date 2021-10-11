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
 * Servlet implementation class PwdModifyServlet
 */
@WebServlet("/pwdModify")
public class PwdModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/memberModifyForm/pwdModifyForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 넘어온 값 추출
		// 현재 비밀번호
		String userPwd = request.getParameter("userPwd");
		// 수정할 비밀번호
		String newPwd = request.getParameter("newPwd");
		// userNo - > loginUser에서 가져옴
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();

		// 2. 비즈니스 로직 수행
		Member updateMember = new MemberService().updatePwd(userNo, userPwd, newPwd);
		
//		// 3. 결과에 따른 응답 처리
		if(updateMember != null) {
			// 비밀 번호 수정이 잘되었음을 result success로 표시
			request.setAttribute("result", "success");
			// 수정된 객체 다시 logiUser에 저장
			request.getSession().setAttribute("loginUser", updateMember);
		} else {
			// 비밀번호 수정이 실패 했음을 result fail로 표시
			request.setAttribute("result", "fail");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/memberModifyForm/pwdModifyForm.jsp").forward(request, response);
	}

}
