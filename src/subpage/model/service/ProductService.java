package subpage.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.paging.model.vo.PageInfo;
import subpage.model.dao.ProductDao;
import subpage.model.vo.Product;
import subpage.model.vo.Search;

public class ProductService {
	private ProductDao pd = new ProductDao();
	
	public String selectCategoryName(int cno) {
		Connection conn = getConnection();
		
		String result = pd.selectCategoryName(conn, cno);
		
		close(conn);
		
		return result;
	}


	public Map<String, Object> selectList(int page, int cno, String st) {
		Connection conn = getConnection();
		Map<String, Object> map = new HashMap<>();
		int itemCount = pd.getListCount(conn, cno);
		PageInfo pi = new PageInfo(page, itemCount, 10, 20);
		List<Product> productList = pd.selectList(conn, pi, cno, st);
		
		map.put("pi", pi);
		map.put("productList", productList);
		
		close(conn);
		
		return map;
	}
	//================================================================
	public Map<String, Object> searchList(int page, int cno, String st, Search s) {
		Connection conn = getConnection();
		Map<String, Object> map = new HashMap<>();
		int itemCount = pd.getSearchListCount(conn, cno, s);
		PageInfo pi = new PageInfo(page, itemCount, 10, 20);
		List<Product> productList = pd.searchList(conn, pi, cno, st, s);
		
		map.put("pi", pi);
		map.put("productList", productList);
		
		close(conn);
		
		return map;
	}
	


}
