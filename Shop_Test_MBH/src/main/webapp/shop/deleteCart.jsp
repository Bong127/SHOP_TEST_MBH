<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="shop.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/meta.jsp" %>
<jsp:useBean id="productDAO" class="shop.dao.ProductRepository" />
<%
	String loginId = (String) session.getAttribute("loginId");
	String productId = request.getParameter("productId");
	String cartId = request.getParameter("cartId");
	List<Product> productList = null;
	if(loginId!=null){
		productList = (List<Product>) session.getAttribute(loginId);
		if(productId!=null){
			for(Product product: productList){
				if(productId.equals(product.getProductId())){
					productList.remove(product);
					break;
				}
			}
		}else{
			productList.clear();
		}
	}else{
		productList = (List<Product>) session.getAttribute("user");
		if(productId!=null){
			for(Product product: productList){
				if(productId.equals(product.getProductId())){
					productList.remove(product);
					break;
				}
			}
		}else{
			productList.clear();
		}
	}

	
	// 장바구니 페이지로 리디렉션
	response.sendRedirect(root + "/shop/cart.jsp");
%>