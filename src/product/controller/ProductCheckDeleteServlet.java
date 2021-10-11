package product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;

/**
 * Servlet implementation class ProductCheckDeleteServlet
 */
@WebServlet("/product/checkDelete")
public class ProductCheckDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductCheckDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkArr = request.getParameterValues("pNo");
		int results = new ProductService().checkDeleteProduct(checkArr);
		
		if(checkArr.length == results) {
			response.sendRedirect(request.getContextPath() + "/product/listView");
		} else {
			request.setAttribute("msg", "상품 삭제에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
