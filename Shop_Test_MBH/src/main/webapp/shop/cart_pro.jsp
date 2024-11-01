<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="shop.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/meta.jsp" %>
<jsp:useBean id="productDAO" class="shop.dao.ProductRepository" />
<%
	String loginId = (String) session.getAttribute("loginId");
	String productId = request.getParameter("id");
	Product product = productDAO.getProductById(productId);
	List<Product> productList = null;
	
	if (loginId != null) {
	    productList = (List<Product>) session.getAttribute(loginId);
	} else {
	    productList = (List<Product>) session.getAttribute("user");
	}
	
	boolean productExists = false;
	
	if (productList != null) {
	    for (Product test : productList) {
	        if (test.getProductId().equals(product.getProductId())) {
	            test.setQuantity(test.getQuantity() + 1);
	            productExists = true; // 상품이 이미 존재함을 표시
	            break; // 상품을 찾았으니 루프 종료
	        }
	    }
	} else {
	    productList = new ArrayList<>();
	}
	
	if (!productExists) {
	    product.setQuantity(1);
	    productList.add(product);
	}
	
	// 업데이트된 상품 목록을 세션에 저장
	if (loginId != null) {
	    session.setAttribute(loginId, productList);
	} else {
	    session.setAttribute("user", productList);
	}
	
	// 장바구니 페이지로 리디렉션
	response.sendRedirect(root + "/shop/cart.jsp");
%>