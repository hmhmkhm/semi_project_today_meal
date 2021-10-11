package searchOrders.controller;

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
 * Servlet implementation class SearchOrders
 */
@WebServlet("/user/search")
public class SearchOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchOrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		// * page : 현재 요청하는 페이지 값 (기본적으로 게시판은 1페이지부터 시작)
		int page = 1;
		
		// 하지만 페이지 전환 시 전달받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String startYY = request.getParameter("startYY");
		String startMM = request.getParameter("startMM");
		String startDD = request.getParameter("startDD");
		
		String endYY = request.getParameter("endYY");
		String endMM = request.getParameter("endMM");
		String endDD = request.getParameter("endDD");
		
		if(startYY == null || endDD == null) {
			request.getRequestDispatcher("/WEB-INF/views/searchOrders/searchOrders.jsp").forward(request, response);
			return;
		}
		String searchStart = startYY.concat(startMM).concat(startDD);
		String searchEnd = endYY.concat(endMM).concat(endDD);
		// 요청 페이지 값을 매개변수로 넘기고 조회된 게시글 리스트 + 페이징 처리에 대한 객체 값 Map 타입에 담아 리턴
		Map<String, Object> map = new ReceiptService().selectReceipts(userNo, page, searchStart, searchEnd);
		// 응답 페이지 구성 시 사용할 데이터 설정
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("receiptList", map.get("receiptList"));
				
		request.getRequestDispatcher("/WEB-INF/views/searchOrders/searchOrders.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
