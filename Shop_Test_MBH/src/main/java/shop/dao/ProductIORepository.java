package shop.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.dto.Product;

public class ProductIORepository extends JDBConnection {

	/**
	 * 상품 입출고 등록
	 * @param product
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public int insert(Product product) throws Exception {
		
		int result = 0;
		OrderRepository order = new OrderRepository();
		int orderNo = order.lastOrderNo();
		String sql = "insert into product_io"
				+ "(product_id, order_no, amount, type, user_id)"
				+ " values(?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, product.getProductId());
			psmt.setInt(2, orderNo);
			psmt.setInt(3, product.getQuantity());
			psmt.setString(4, "out");
			psmt.setString(5, product.getUserId());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	

}