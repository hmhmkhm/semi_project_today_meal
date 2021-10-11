package productSelect.model.service;
import java.sql.Connection;

import productSelect.model.dao.ProductSelectDao;
import productSelect.model.vo.OrderBasket;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class ProductSelectService {
	private ProductSelectDao pd = new ProductSelectDao();

	public OrderBasket selectProduct(int product_no) {
		
		Connection conn = getConnection();
		OrderBasket ob = pd.selectProduct(conn, product_no);
		
		close(conn);
		
		return ob;
	}
	
}
