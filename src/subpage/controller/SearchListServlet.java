package subpage.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import subpage.model.service.ProductService;
import subpage.model.vo.Search;


/**
 * Servlet implementation class SearchListServlet
 */
@WebServlet("/search/list")
public class SearchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		String searchBox = request.getParameter("searchBox");
		int cno = 0;
		String cName = new ProductService().selectCategoryName(cno);
		String st = "recent";
		
		if(searchBox == null) {
			request.getSession().setAttribute("msg", "잘못된 접근입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		} else {
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			if(request.getParameter("st") != null) {
				st = request.getParameter("st");
			}

			Map<String, Object> map = new ProductService().searchList(page, cno, st, new Search());

			request.setAttribute("searchBox", searchBox);
			request.setAttribute("cno", cno);
			request.setAttribute("cName", cName);
			request.setAttribute("pi", map.get("pi"));
			request.setAttribute("productList", map.get("productList"));
			request.getRequestDispatcher("/WEB-INF/views/subpage/searchListView.jsp").forward(request, response);		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	

}
