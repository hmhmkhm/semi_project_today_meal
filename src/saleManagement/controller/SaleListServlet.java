package saleManagement.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saleManagement.common.SaleManagementCommon;
import saleManagement.model.service.SaleManagementService;

/**
 * Servlet implementation class saleListServlet
 */
@WebServlet("/sale/list")
public class SaleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		String userId = request.getParameter("id") == null ? "" : request.getParameter("id");
		String orderNumber = request.getParameter("orderNumber") == null ? "" : request.getParameter("orderNumber");
		String orderStatus = request.getParameter("orderStatus") == null || request.getParameter("orderStatus").equals("") ? "default" : request.getParameter("orderStatus");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		Calendar today = new GregorianCalendar();
		Calendar startDate = new GregorianCalendar();
		Calendar endDate = new GregorianCalendar();
		Map<String, Integer> todayMap = new HashMap<>();
		Map<String, Object> startMap = new HashMap<>();
		Map<String, Object> endMap = new HashMap<>();
		
		if(request.getParameter("start_date") != null && request.getParameter("start_date").length() > 0) {
			String startDateString = request.getParameter("start_date");
			startDate.set(Calendar.YEAR, Integer.parseInt(startDateString.substring(0, 4)));
			startDate.set(Calendar.MONTH, Integer.parseInt(startDateString.substring(4, 6))-1);
			startDate.set(Calendar.DATE, Integer.parseInt(startDateString.substring(6, 8)));
		}
		
		if(request.getParameter("end_date") != null && request.getParameter("end_date").length() > 0) {
			String endDateString = request.getParameter("end_date");
			endDate.set(Calendar.YEAR, Integer.parseInt(endDateString.substring(0, 4)));
			endDate.set(Calendar.MONTH, Integer.parseInt(endDateString.substring(4, 6))-1);
			endDate.set(Calendar.DATE, Integer.parseInt(endDateString.substring(6, 8)));
		}
		Map<String, Object> map = new SaleManagementService().selectList(page, startDate.getTime(), endDate.getTime(), userId, orderNumber, SaleManagementCommon.ORDER_STATUS_MAP.get(orderStatus));

		todayMap.put("year", today.get(Calendar.YEAR));
		todayMap.put("month", today.get(Calendar.MONTH)+1);
		todayMap.put("day", today.get(Calendar.DATE));
		todayMap.put("lastDay", today.getActualMaximum(Calendar.DATE));
		startMap.put("date", df.format(startDate.getTime()));
		startMap.put("year", startDate.get(Calendar.YEAR));
		startMap.put("month", startDate.get(Calendar.MONTH)+1);
		startMap.put("day", startDate.get(Calendar.DATE));
		startMap.put("lastDay", startDate.getActualMaximum(Calendar.DATE));
		endMap.put("date", df.format(endDate.getTime()));
		endMap.put("year", endDate.get(Calendar.YEAR));
		endMap.put("month", endDate.get(Calendar.MONTH)+1);
		endMap.put("day", endDate.get(Calendar.DATE));
		endMap.put("lastDay", endDate.getActualMaximum(Calendar.DATE));
		
		
		request.setAttribute("today", todayMap);
		request.setAttribute("startDate", startMap);
		request.setAttribute("endDate", endMap);
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("receiptList", map.get("receiptList"));
		request.getRequestDispatcher("/WEB-INF/views/saleManagement/saleListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
