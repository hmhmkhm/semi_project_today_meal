package salesAnalysis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import salesAnalysis.model.service.ReceiptService;
import salesAnalysis.model.vo.Ranking;
import salesAnalysis.model.vo.SalesData;

/**
 * Servlet implementation class SalesAnalysis
 */
@WebServlet("/admin/sales/analysis")
public class SalesAnalysisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesAnalysisServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Ranking> rankingList = new ReceiptService().selectRankingTop5();
		request.setAttribute("rankingList", rankingList);
		
		request.getRequestDispatcher("/WEB-INF/views/salesAnalysis/salesAnalysis.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String analysisType = request.getParameter("analysisType");
		String searchStart = null;
		String searchEnd = null;
		List<SalesData> salesList;
		
		if(analysisType.equals("date")) {
			
			String dailyStartYY = request.getParameter("dailyStartYY");
			String dailyStartMM = request.getParameter("dailyStartMM");
			String dailyStartDD = request.getParameter("dailyStartDD");
			String dailyEndYY = request.getParameter("dailyEndYY");
			String dailyEndMM = request.getParameter("dailyEndMM");
			String dailyEndDD = request.getParameter("dailyEndDD");
			
			searchStart = dailyStartYY.concat(dailyStartMM).concat(dailyStartDD);
			searchEnd = dailyEndYY.concat(dailyEndMM).concat(dailyEndDD);
			
		}
		if(analysisType.equals("month")) {
			
			String monthlyStartYY = request.getParameter("monthlyStartYY");
			String monthlyStartMM = request.getParameter("monthlyStartMM");
			String monthlyEndYY = request.getParameter("monthlyEndYY");
			String monthlyEndMM = request.getParameter("monthlyEndMM");
			
			searchStart = monthlyStartYY.concat(monthlyStartMM);
			searchEnd = monthlyEndYY.concat(monthlyEndMM);
		}
		
		salesList = new ReceiptService().selectSalesAnalysis(searchStart, searchEnd);
		
		response.setContentType("application/jos; charset=utf-8");
		new Gson().toJson(salesList, response.getWriter());
	}

}
