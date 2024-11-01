<%@page import="java.util.Iterator"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="shop.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/meta.jsp" %>
<jsp:useBean id="productDAO" class="shop.dao.ProductRepository" />
<%
	String productId = request.getParameter("productId");

	int result = productDAO.delete(productId);
	
	if (result > 0) {
		response.sendRedirect(root + "/shop/products.jsp");
	} else {
		response.sendRedirect(root + "/shop/editProducts.jsp");
	}
		

%>