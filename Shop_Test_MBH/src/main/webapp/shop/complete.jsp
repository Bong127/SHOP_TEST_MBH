<%@page import="shop.dao.ProductRepository"%>
<%@page import="shop.dao.ProductIORepository"%>
<%@page import="shop.dao.OrderRepository"%>
<%@page import="shop.dto.Order"%>
<%@page import="shop.dto.Ship"%>
<%@page import="shop.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Complete</title>
	<%@ include file="/layout/meta.jsp" %>
	<%@ include file="/layout/link.jsp" %>
</head>
<body>
	<%
		String loginId = (String) session.getAttribute("loginId");
		List<Product> productList = null;
		Ship ship = (Ship)application.getAttribute("ship");
		Order order = new Order();
		OrderRepository orderRepository = new OrderRepository();
		order.setShipName(ship.getShipName());
		order.setZipCode(ship.getZipCode());
		order.setCountry(ship.getCountry());
		order.setAddress(ship.getAddress());
		order.setDate(ship.getDate());
		order.setTotalPrice(Integer.parseInt(request.getParameter("total_price")));
		order.setPhone(ship.getPhone());
		if(loginId!=null){
			productList = (List<Product>) session.getAttribute(loginId);
			order.setUserId(loginId);
		}
		else{
			productList = (List<Product>) session.getAttribute("user");
			order.setOrderPw(request.getParameter("password"));
		}
		orderRepository.insert(order);
		ProductIORepository ioRepository = new ProductIORepository();
		ProductRepository productRepository = new ProductRepository();
		for(Product product : productList){
			product.setUserId(loginId);
			ioRepository.insert(product);
			product.setUnitsInStock(product.getUnitsInStock()-product.getQuantity());
			productRepository.update(product);
		}
		productList.clear();
		int orderNo = orderRepository.lastOrderNo();
	%>
	<jsp:include page="/layout/header.jsp" />
	<div class="px-4 py-5 my-5 text-center">
		<h1 class="display-5 fw-bold text-body-emphasis">주문 완료</h1>
	</div>
	<div class="container order mb-5 p-5">
		
		<h2 class="text-center">주문이 완료되었습니다.</h2>
		<!-- 주문정보 -->
		<div class="ship-box">
			<table class="table ">
				<tbody><tr>
					<td>주문번호 :</td>
					<td><%=orderNo %></td>
				</tr>
				<tr>
					<td>배송지 :</td>
					<td><%=ship.getAddress() %></td>
				</tr>
			</tbody></table>
			
			<div class="btn-box d-flex justify-content-center">
				<a href="<%=root %>/user/order.jsp" class="btn btn-primary btn-lg px-4 gap-3">주문내역</a>
			</div>
		</div>
	</div>
	<jsp:include page="/layout/footer.jsp" />
	<jsp:include page="/layout/script.jsp" />
</body>
</html>