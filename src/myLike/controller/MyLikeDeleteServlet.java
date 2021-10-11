package myLike.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import myLike.model.service.MyLikeService;

/**
 * Servlet implementation class MyLikeDeleteServlet
 */
@WebServlet("/like/delete")
public class MyLikeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyLikeDeleteServlet() {
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
		int userNo = Integer.parseInt(request.getParameter("userNo")),
			reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		MyLikeService mls = new MyLikeService();
			
		int result = mls.deleteLike(userNo, reviewNo);
			
		if(result  > 0) {
			int count = mls.getLikeCount(reviewNo);

			response.setContentType("application/json; charset=utf-8");
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("count", count);

			new Gson().toJson(jsonObject, response.getWriter());
		} else {
			request.setAttribute("msg", "error가 발생했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common.errpage.jsp").forward(request, response);
		}
	}

}
