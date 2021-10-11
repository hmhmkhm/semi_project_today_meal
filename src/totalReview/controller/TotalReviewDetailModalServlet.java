package totalReview.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import login.model.vo.Member;
import totalReview.model.service.TotalReviewService;
import totalReview.model.vo.Review;

/**
 * Servlet implementation class TotalReviewDetailModalServlet
 */
@WebServlet("/totalReview/detailModal")
public class TotalReviewDetailModalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalReviewDetailModalServlet() {
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
		int rno = Integer.parseInt(request.getParameter("rno"));
		int userNo = request.getSession().getAttribute("loginUser") != null ? ((Member)request.getSession().getAttribute("loginUser")).getUserNo() : 0;
		
		Review r = new TotalReviewService().selectReview(rno, userNo);
		
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
		gson.toJson(r, response.getWriter());
	}

}
