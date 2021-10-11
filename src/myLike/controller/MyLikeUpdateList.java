package myLike.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.model.vo.Member;
import myLike.model.service.MyLikeService;

/**
 * Servlet implementation class MyLikeUpdateList
 */
@WebServlet("/like/updateList")
public class MyLikeUpdateList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyLikeUpdateList() {
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
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		String[] likeNos = request.getParameterValues("lno");
		
		int result = new MyLikeService().deleteLikeList(userNo, likeNos);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "선택한 좋아요가 삭제되었습니다.");
			response.sendRedirect(request.getContextPath() + "/myLike/list");
		} else {
			request.setAttribute("msg", "선택한 좋아요를 삭제하는데 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
