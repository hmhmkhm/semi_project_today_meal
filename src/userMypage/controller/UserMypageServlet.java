package userMypage.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.model.vo.Member;
import salesAnalysis.model.service.ReceiptService;

/**
 * Servlet implementation class UserMypageServlet
 */
@WebServlet("/user/mypage")
public class UserMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String orderInfo = request.getParameter("orderNo");
		if(orderInfo != null) {
			int orderNo = Integer.parseInt(orderInfo);
			
			int result = new ReceiptService().cancelProduct(orderNo);
			
			if(result > 0) {
				String sOrderNo = Integer.toString(orderNo);
				request.setAttribute("cancelMsg", sOrderNo);
			}
			
		}
		
		
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		// * page : 현재 요청하는 페이지 값 (기본적으로 게시판은 1페이지부터 시작)
		int page = 1;
		
		// 하지만 페이지 전환 시 전달받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 검색 조건과 검색값 추가하기
		String searchStart = request.getParameter("searchStart");
		String searchEnd = request.getParameter("searchEnd");

		// 요청 페이지 값을 매개변수로 넘기고 조회된 게시글 리스트 + 페이징 처리에 대한 객체 값 Map 타입에 담아 리턴
		Map<String, Object> map = new ReceiptService().selectReceipts(userNo, page, searchStart, searchEnd);
		// 응답 페이지 구성 시 사용할 데이터 설정
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("receiptList", map.get("receiptList"));
		request.getRequestDispatcher("/WEB-INF/views/mypageMain/mypageMain.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
