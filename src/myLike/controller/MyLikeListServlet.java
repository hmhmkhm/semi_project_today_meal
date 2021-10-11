package myLike.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.model.vo.Member;
import myLike.model.service.MyLikeService;
import myLike.model.vo.MyLike;
import subpage.model.service.ProductService;

/**
 * Servlet implementation class MyLikeList
 */
@WebServlet("/myLike/list")
public class MyLikeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyLikeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		Map<String, Object> map = new MyLikeService().selectList(page, userNo);
		
		request.setAttribute("myLikeList", map.get("myLikeList"));
		request.setAttribute("pi", map.get("pi"));
		request.getRequestDispatcher("/WEB-INF/views/myLike/myLikeListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
