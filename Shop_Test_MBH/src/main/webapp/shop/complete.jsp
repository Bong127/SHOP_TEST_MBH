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
					<td>E7DFB7E21FCEE66B899987D7B3EAD39B</td>
				</tr>
				<tr>
					<td>배송지 :</td>
					<td>테스트동 테스트대로217</td>
				</tr>
			</tbody></table>
			
			<div class="btn-box d-flex justify-content-center">
				<a href="/Shop/user/order.jsp" class="btn btn-primary btn-lg px-4 gap-3">주문내역</a>
			</div>
		</div>
	</div>
	<jsp:include page="/layout/footer.jsp" />
</body>
</html>