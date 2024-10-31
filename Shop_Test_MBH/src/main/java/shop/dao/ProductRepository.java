package shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import shop.dto.Product;

public class ProductRepository extends JDBConnection {
	
	public Product map(ResultSet rs) throws Exception {
		Product product = new Product();
		product.setProductId( rs.getString("product_id")); 
		product.setName(rs.getString("name"));
		product.setUnitPrice(rs.getInt("unit_price"));
		product.setDescription(rs.getString("description"));
		product.setCategory(rs.getString("category"));
		product.setUnitsInStock(rs.getInt("units_in_stock"));
		product.setCondition(rs.getString("condition"));
		product.setFile(rs.getString("file"));
		product.setQuantity(rs.getInt("quantity"));
		return product;
	}
	
	/**
	 * 상품 목록
	 * @return
	 * @throws Exception 
	 */
	public List<Product> list() throws Exception {
		String sql = "select * from product";
		List<Product> product = new ArrayList<Product>();
	    try {
	        stmt = con.createStatement();
	        rs = stmt.executeQuery(sql);
	        if (rs.next()) {
	        	product.add(map(rs));
	        }
	        rs.close();
	    } catch (SQLException e) {
	        System.err.println("상품 목록 조회 중, 에러 발생!");
	        e.printStackTrace();
	    }
	    return product;
	}
	
	
	/**
	 * 상품 목록 검색
	 * @param keyword
	 * @return
	 * @throws Exception 
	 */
	public List<Product> list(String keyword) throws Exception {
		String sql = "select * from product where name like concat('%',?,'%')";
		List<Product> product = new ArrayList<Product>();
	    try {
	    	psmt = con.prepareStatement(sql);
	    	psmt.setString(1, keyword);
	        rs = psmt.executeQuery();
	        if (rs.next()) {
	        	product.add(map(rs));
	        }
	        rs.close();
	    } catch (SQLException e) {
	        System.err.println("상품 목록 검색 중, 에러 발생!");
	        e.printStackTrace();
	    }
	    return product;
		
	}
	
	/**
	 * 상품 조회
	 * @param productId
	 * @return
	 * @throws Exception 
	 */
	public Product getProductById(String productId) throws Exception {
		String sql = "select * from product where product_id = ?";
		Product product = null;
	    try {
	    	psmt = con.prepareStatement(sql);
	    	psmt.setString(1, productId);
	        rs = psmt.executeQuery();
	        if (rs.next()) {
	        	product = map(rs);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        System.err.println("상품 조회 중, 에러 발생!");
	        e.printStackTrace();
	    }
	    return product;
		
	}
	
	
	/**
	 * 상품 등록
	 * @param product
	 * @return
	 */
	public int insert(Product product) {
		int result = 0;
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
		    psmt.setString(1, product.getProductId());
		    psmt.setString(2, product.getName());
		    psmt.setInt(3, product.getUnitPrice());
		    psmt.setString(4, product.getDescription());
		    psmt.setString(5, product.getManufacturer());
		    psmt.setString(6, product.getCategory());
		    psmt.setLong(7, product.getUnitsInStock());
		    psmt.setString(8, product.getCondition());
		    psmt.setString(9, product.getFile());
		    psmt.setInt(10, product.getQuantity());
		    result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 상품 수정
	 * @param product
	 * @return
	 */
	public int update(Product product) {
		int result = 0;
		String sql = "update product set "
					+" name=?, unit_price=?, description=?, manufacturer=?, category=?, "
					+" units_in_stock=?, condition=?, file=?, quantity=? "
					+ "where product_id=?";
		try {
			psmt = con.prepareStatement(sql);
		    psmt.setString(1, product.getName());
		    psmt.setInt(2, product.getUnitPrice());
		    psmt.setString(3, product.getDescription());
		    psmt.setString(4, product.getManufacturer());
		    psmt.setString(5, product.getCategory());
		    psmt.setLong(6, product.getUnitsInStock());
		    psmt.setString(7, product.getCondition());
		    psmt.setString(8, product.getFile());
		    psmt.setInt(9, product.getQuantity());
		    psmt.setString(10, product.getProductId());
		    result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	
	/**
	 * 상품 삭제
	 * @param product
	 * @return
	 */
	public int delete(String productId) {
		int result = 0;
		String sql = "delete from product where product_id = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, productId);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("product - delete 도중 에러");
			e.printStackTrace();
		}
		return result;
	}

}





























