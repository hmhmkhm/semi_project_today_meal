package productBuy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import productBuy.model.service.ProductBuyService;
import productBuy.model.vo.Option;
import productBuy.model.vo.Product;

/**
 * Servlet implementation class BasketInsertServlet
 */
@WebServlet("/basketInsert")
public class BasketInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasketInsertServlet() {
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
		// 1. 요청에 담긴 데이터 추출 
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
		String[] optionNoArr = request.getParameterValues("optionNo");
		String[] optionQuantityArr = request.getParameterValues("optionQuantity");
		
		Product pro = new Product();
		pro.setPno(productNo);
		pro.setQuantity(productQuantity);
		List<Option> optionList = new ArrayList<>();
		for(int i = 0; i < optionNoArr.length; i++) {
			Option opt = new Option();
			opt.setOptionNo(Integer.parseInt(optionNoArr[i]));
			opt.setQuantity(Integer.parseInt(optionQuantityArr[i]));
			optionList.add(opt);
		}
		pro.setOptionList(optionList);
		System.out.println(pro);
		
		// 2. 서비스 클래스의 삽입 메소드 호출
		int result = new ProductBuyService().insertBasket(pro);
		
		// 3. 응답 페이지 결정
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
