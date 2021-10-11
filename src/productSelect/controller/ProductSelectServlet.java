package productSelect.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import productSelect.model.service.ProductSelectService;
import productSelect.model.vo.OptionBasket;
import productSelect.model.vo.OrderBasket;

/**
 * Servlet implementation class ProductSelectServlet
 */
@WebServlet("/product/select")
public class ProductSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getSession().getAttribute("loginUser") == null) { // 세션에 로그인 요저 객체가 없다면
			request.setAttribute("msg", "올바르지 않은 요청입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			return;
		}
		
		int product_no = Integer.parseInt(request.getParameter("pno"));
		
		OrderBasket ob = new ProductSelectService().selectProduct(product_no);
		
		request.setAttribute("orderBasket", ob);
		
		request.getRequestDispatcher("/WEB-INF/views/productSelect/productSelect.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String ajaxType = request.getParameter("ajaxType");
		if(ajaxType != null) {
			String[] optNameArr = request.getParameterValues("optNameArr");
			String[] optPriceArr = request.getParameterValues("optPriceArr");
			String[] optQtyArr = request.getParameterValues("optQtyArr");
			List<OptionBasket> optListJson = new ArrayList<>();
			
			for(int i=0; i<optNameArr.length;i++) {
				if(!optQtyArr[i].equals("")) {
					optListJson.add(new OptionBasket(optNameArr[i], Integer.parseInt(optQtyArr[i]), Integer.parseInt(optPriceArr[i]), Integer.parseInt(optPriceArr[i])*Integer.parseInt(optQtyArr[i])));
				}
			}
			
			response.setContentType("application/jos; charset=utf-8");
			new Gson().toJson(optListJson, response.getWriter());
			return;
			
		} else {
			OrderBasket obPost = null;
			List<OptionBasket> optListPost = new ArrayList<>();
			
			String productName = request.getParameter("productName");
			String productImg = request.getParameter("productImg");
			String productQty = request.getParameter("productQty");
			String productPrice = request.getParameter("productPrice");
			String total = request.getParameter("total");
			String delivery = request.getParameter("delivery");
			String limCnt = request.getParameter("limCnt");
			if(limCnt != null) {
				int limitCnt = Integer.parseInt(limCnt);
				
				for(int i=0; i<limitCnt; i++) {
					String optionName = request.getParameter("optionName"+i);
					String optionQty = request.getParameter("optionQty"+i);
					String optCalcPrice = request.getParameter("optCalcPrice"+i);
					if(optCalcPrice != null) {
						
						optListPost.add(new OptionBasket(Integer.parseInt(optCalcPrice), optionName, Integer.parseInt(optionQty)));
					}
				}
				obPost = new OrderBasket(productName, productImg, Integer.parseInt(productPrice), 
										 Integer.parseInt(productQty), Integer.parseInt(total), optListPost, delivery);
				
			} else {
				
				obPost = new OrderBasket(productName, productImg, Integer.parseInt(productPrice), 
						 Integer.parseInt(productQty), Integer.parseInt(total), delivery);
			}
			
			request.setAttribute("orderBasket", obPost);
			request.getRequestDispatcher("/WEB-INF/views/payment/paymentView.jsp").forward(request, response);
		}
		
		
	}

}
