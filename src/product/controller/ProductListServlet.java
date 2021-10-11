package product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.Product;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/product/listView")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색된 상품 리스트 조회
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		String searchCategory = request.getParameter("searchCategory");
		List<Product> productList = null;
	
		if(searchCondition != null && searchValue != null) {
			productList = new ProductService().selectProductList(searchCondition, searchValue);
		} else if(searchCategory != null) {
			productList = new ProductService().selectProductList(searchCategory);
		} else {
			productList = new ProductService().selectProductList();
		}

		request.setAttribute("productList", productList);
		request.getRequestDispatcher("/WEB-INF/views/productList/productListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
