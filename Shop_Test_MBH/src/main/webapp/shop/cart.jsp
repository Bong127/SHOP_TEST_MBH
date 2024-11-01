<%@page import="shop.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>cart.jsp</title>
	<%@ include file="/layout/meta.jsp" %>
	<%@ include file="/layout/link.jsp" %>
</head>
<body>
	<%
		String loginId = (String) session.getAttribute("loginId");
		List<Product> productList = null;
		String cartId = null;
		if(loginId!=null){
			cartId = loginId;
			productList = (List<Product>) session.getAttribute(loginId);
		}
		else{
			cartId = "user";
			productList = (List<Product>) session.getAttribute("user");
		}
		int size = 1;
		if (productList == null || productList.size()==0) {
			size=0;
		}
		int sum = 0;
	%>
	<jsp:include page="/layout/header.jsp" />
	<div class="px-4 py-5 my-5 text-center">
		<h1 class="display-5 fw-bold text-body-emphasis">장바구니</h1>
		<div class="col-lg-6 mx-auto">
			<p class="lead mb-4">장바구니 목록 입니다.</p>
		</div>
	</div>
	<div class="container order">
		<!-- 장바구니 목록 -->
		<table class="table table-striped table-hover table-bordered text-center align-middle">
			<thead>
				<tr class="table-primary">
					<th>상품</th>
					<th>가격</th>
					<th>수량</th>
					<th>소계</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				<%
				if(productList != null){
					for(Product product:productList) {
						int total = product.getUnitPrice() * product.getQuantity();
						sum += total;
					%>
					<tr>
						<td><%=product.getName()%></td>
						<td><%=product.getUnitPrice()%></td>
						<td><%=product.getQuantity()%></td>
						<td><%=total%></td>
						<td><a href="deleteCart.jsp?productId=<%=product.getProductId()%>" class="btn btn-danger ">삭제</a></td>
					</tr>
				<%} }%>
			</tbody>
			<%
			if (productList == null || productList.size()==0) {
			%>
			<tfoot>
				<tr>
					<td colspan="5">추가된 상품이 없습니다.</td>	
				</tr>
			</tfoot>
			<%
			}else{
			%>
			<tfoot>
				<tr>
					<td></td>
					<td></td>
					<td>총액</td>
					<td id="cart-sum"><%=sum%></td>
					<td></td>
				</tr>
			</tfoot>
			<%} %>
		</table>
		<!-- 버튼 영역 -->
		<div class="d-flex justify-content-between align-items-center p-3">
			<a href="deleteCart.jsp?cartId=<%=cartId%>" class="btn btn-lg btn-danger ">전체삭제</a>

			<a href="javascript:;" class="btn btn-lg btn-primary" onclick="order('<%=size%>','<%=root%>')">주문하기</a>
		</div>
	</div>
	<script type="text/javascript">
		function order(size,root){
			if(size==0){
				alert("장바구니에 담긴 상품이 없습니다.");
			}
			else{
				window.location.href = root+"/shop/ship.jsp";
			}
		}
	</script>
	<jsp:include page="/layout/footer.jsp" />
	<jsp:include page="/layout/script.jsp" />
</body>
</html>