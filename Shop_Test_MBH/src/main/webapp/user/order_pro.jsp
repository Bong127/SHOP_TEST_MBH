<%@page import="shop.dto.Product"%>
<%@page import="java.util.List"%>
<%@page import="shop.dao.OrderRepository"%>
<%@ include file="/layout/meta.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="orderDAO" class="shop.dao.OrderRepository" />
<%
	
	// 비회원 주문 내역 세션에 등록 처리
	StringBuffer sb = new StringBuffer();
	String phone = request.getParameter("phone");
	sb.append(phone);
	sb.insert(3,"-");
	sb.insert(8,"-");
	phone = sb.toString();
	String orderPw = request.getParameter("orderPw");
	List<Product> orderList = orderDAO.list(phone, orderPw);
	session.setAttribute("tempOrderList", orderList);
	session.setAttribute("orderPhone", phone);
	response.sendRedirect(root + "/user/order.jsp");
%>