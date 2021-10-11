package saleManagement.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saleManagement.model.service.SaleManagementService;

/**
 * Servlet implementation class SaleChangeStatusServlet
 */
@WebServlet("/sale/changeStatus")
public class SaleChangeStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleChangeStatusServlet() {
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
		String[] receiptNos = request.getParameterValues("rno");
		int chageStatus = Integer.parseInt(request.getParameter("chageStatus"));
		
		int result = new SaleManagementService().changeStatus(receiptNos, chageStatus);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", receiptNos.length +  "개의 주문의 상태가 변경되었습니다.");
			String query = (request.getParameter("page") != null ? "page=" + request.getParameter("page") : "");
			query += (query.length() > 0 ? "&" : "") + (request.getParameter("id") != null ? "id=" + request.getParameter("id") : "");
			query += (query.length() > 0 ? "&" : "") + (request.getParameter("orderNumber") != null ? "orderNumber=" + request.getParameter("orderNumber") : "");
			query += (query.length() > 0 ? "&" : "") + (request.getParameter("start_date") != null ? "start_date=" + request.getParameter("start_date") : "");
			query += (query.length() > 0 ? "&" : "") + (request.getParameter("end_date") != null ? "end_date=" + request.getParameter("end_date") : "");
					 	 
			response.sendRedirect(request.getContextPath() + "/sale/list" + (query.length() > 0 ? "?" + query : ""));
		} else {
			request.setAttribute("msg", "error가 발생했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
