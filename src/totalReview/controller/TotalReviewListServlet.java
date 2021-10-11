package totalReview.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.model.vo.Member;
import totalReview.common.TotalReviewCommon;
import totalReview.model.service.TotalReviewService;
import totalReview.model.vo.Review;

/**
 * Servlet implementation class totalReviewListServlet
 */
@WebServlet("/totalReview/list")
public class TotalReviewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalReviewListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int listCount = 0;
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		int userNo = request.getSession().getAttribute("loginUser") != null ? ((Member)request.getSession().getAttribute("loginUser")).getUserNo() : 0;
		TotalReviewService trs = new TotalReviewService();
		
		String categoryParameter = request.getParameter("categoryList");
		List<Integer> categoryNumberList = new ArrayList<>();
		String[] categoryList = null;

		if(categoryParameter != null && categoryParameter.length() > 0) {
			categoryList = categoryParameter.split(",");
			
			for(String category : categoryList) {
				categoryNumberList.add(TotalReviewCommon.CATEGORY_MAP.get(category));
			}
		} else {
			listCount = trs.getListCount(categoryNumberList, keyword);
		}
		
		List<Review> reviewList = trs.selectList(page, categoryNumberList, "recent", keyword, userNo);
		Map<String, Integer> categoryCountMap = new HashMap<>();

		if(categoryList != null) {
			Map<Integer, Integer> categoryCountMapTemp = trs.getCategoryListCount(categoryNumberList, keyword);

			for(String category : categoryList) {
				int count = categoryCountMapTemp.get(TotalReviewCommon.CATEGORY_MAP.get(category));

				categoryCountMap.put(category, count);
				listCount += count;
			}
		}

		request.setAttribute("listCount", listCount);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("categoryCountInfo", categoryCountMap);
		request.getRequestDispatcher("/WEB-INF/views/totalReview/reviewListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
