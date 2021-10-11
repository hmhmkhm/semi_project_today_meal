package totalReview.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import login.model.vo.Member;
import totalReview.common.TotalReviewCommon;
import totalReview.model.service.TotalReviewService;
import totalReview.model.vo.Review;

/**
 * Servlet implementation class TotalReviewListMoreServlet
 */
@WebServlet("/totalReview/listMore")
public class TotalReviewListMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalReviewListMoreServlet() {
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
		int page = Integer.parseInt(request.getParameter("page"));
		String st = request.getParameter("st");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		int userNo = request.getSession().getAttribute("loginUser") != null ? ((Member)request.getSession().getAttribute("loginUser")).getUserNo() : 0;

		String categoryParameter = request.getParameter("categoryList");
		List<Integer> categoryNumberList = new ArrayList<>();

		if(categoryParameter != null && categoryParameter.length() > 0) {
			String[] categoryList = categoryParameter.split(",");
			
			for(String category : categoryList) {
				categoryNumberList.add(TotalReviewCommon.CATEGORY_MAP.get(category));
			}
		}
		
		List<Review> reviewList = new TotalReviewService().selectList(page, categoryNumberList, st, keyword, userNo);

		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(reviewList, response.getWriter());
	}

}
