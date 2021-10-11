package review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.model.vo.Member;
import productInfo.model.service.ProductService;
import productInfo.model.vo.Product;
import review.model.service.ReviewService;
import review.model.vo.Review;

/**
 * Servlet implementation class MypageReveiwSortDesc
 */
@WebServlet("/mypage/reviewDesc")
public class MypageReveiwSortDesc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageReveiwSortDesc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 매개변수로 로그인한 사용자 아이디 보내기
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		List<Review> review = new ReviewService().selectMyReviewProductListDesc(userNo);
		request.setAttribute("review", review);
		System.out.println(review);
		
		request.getRequestDispatcher("/WEB-INF/views/review_management/review_management.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
