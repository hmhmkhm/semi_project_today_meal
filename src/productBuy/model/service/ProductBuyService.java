package productBuy.model.service;

import java.sql.Connection;

import static common.JDBCTemplate.*;

import productBuy.model.dao.ProductBuyDao;
import productBuy.model.vo.Product;

public class ProductBuyService {
	private ProductBuyDao pbDao = new ProductBuyDao();

	public int insertBasket(Product pro) {
		Connection conn = getConnection();
		int result = pbDao.insertBasket(conn, pro);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	

}
