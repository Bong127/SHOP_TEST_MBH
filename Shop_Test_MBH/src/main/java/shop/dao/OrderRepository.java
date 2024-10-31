package shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import shop.dto.Order;
import shop.dto.Product;

public class OrderRepository extends JDBConnection {
	public Order map(ResultSet rs) throws Exception {
		Order order = new Order();
		order.setOrderNo( rs.getInt("order_no")); 
		order.setShipName( rs.getString("ship_name")); 
		order.setZipCode( rs.getString("zip_code")); 
		order.setCountry( rs.getString("country")); 
		order.setAddress( rs.getString("address")); 
		order.setDate( rs.getString("date")); 
		order.setOrderPw( rs.getString("order_pw")); 
		order.setUserId( rs.getString("user_id")); 
		order.setTotalPrice( rs.getInt("total_price")); 
		order.setPhone( rs.getString("phone")); 
		return order;
	}
	/**
	 * 주문 등록
	 * @param user
	 * @return
	 */
	public int insert(Order order) {
		int result = 0;
		String sql = "insert into `order` values(?,?,?,?,?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
		    psmt.setString(1, order.getShipName());
		    psmt.setString(2, order.getZipCode());
		    psmt.setString(3, order.getCountry());
		    psmt.setString(4, order.getAddress());
		    psmt.setString(5, order.getDate());
		    psmt.setString(6, order.getOrderPw());
		    psmt.setString(7, order.getUserId());
		    psmt.setInt(8, order.getTotalPrice());
		    psmt.setString(9, order.getPhone());
		    result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 최근 등록한 orderNo 
	 * @return
	 * @throws Exception 
	 */
	public int lastOrderNo() throws Exception {
		String sql = "select * from `order` order by order_no desc limit 1";
		Order order = null;
	    try {
	        stmt = con.createStatement();
	        rs = stmt.executeQuery(sql);
	        if (rs.next()) {
	        	order=map(rs);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        System.err.println("마지막 주문번호 조회중, 에러 발생!");
	        e.printStackTrace();
	    }
	    return order.getOrderNo();
	}

	
	/**
	 * 주문 내역 조회 - 회원
	 * @param userId
	 * @return
	 */
	public List<Product> list(String userId) {
		List<Product> productList = new ArrayList<Product>();
		Product product = new Product();
		String sql = "select o.order_no, p.name, p.unit_price, i.amount "
				+ " from product p, product_io i, `order` o "
				+ " where p.product_id = i.product_id and o.order_no = i.order_no "
				+ " and o.user_id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				product.setOrderNo(rs.getInt("order_no"));
				product.setName(rs.getString("name"));
				product.setUnitPrice(rs.getInt("unit_price"));
				product.setAmount(rs.getInt("amount"));
				productList.add(product);
			}
		} catch (Exception e) {
	        System.err.println("주문 내역 조회 중, 에러 발생!");
	        e.printStackTrace();
		}
		return productList;

	}
	
	/**
	 * 주문 내역 조회 - 비회원
	 * @param phone
	 * @param orderPw
	 * @return
	 */
	public List<Product> list(String phone, String orderPw) {
		List<Product> productList = new ArrayList<Product>();
		Product product = new Product();
		String sql = "select o.order_no, p.name, p.unit_price, i.amount "
				+ " from product p, product_io i, `order` o "
				+ " where p.product_id = i.product_id and o.order_no = i.order_no "
				+ " and o.phone=? and o.order_pw=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, phone);
			psmt.setString(2, orderPw);
			rs = psmt.executeQuery();
			if(rs.next()) {
				product.setOrderNo(rs.getInt("order_no"));
				product.setName(rs.getString("name"));
				product.setUnitPrice(rs.getInt("unit_price"));
				product.setAmount(rs.getInt("amount"));
				productList.add(product);
			}
		} catch (Exception e) {
	        System.err.println("주문 내역 조회 중, 에러 발생!");
	        e.printStackTrace();
		}
		return productList;
	}
	
}






























