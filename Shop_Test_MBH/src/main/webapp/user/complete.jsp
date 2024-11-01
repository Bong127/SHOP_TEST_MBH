<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Shop</title>
	<%@ include file="/layout/meta.jsp" %>
	<%@ include file="/layout/link.jsp" %>
</head>
<body>   
	<% 
		int msg = Integer.parseInt(request.getParameter("msg"));
		String loginId = (String) session.getAttribute("loginId");
	%>
	<jsp:include page="/layout/header.jsp" />
	<div class="px-4 py-5 my-5 text-center">
		<h1 class="display-5 fw-bold text-body-emphasis">회원 정보</h1>
		
	</div>
	<!-- 회원 가입/수정/탈퇴 완료 -->
	<div class="container mb-5 text-center">
		<%
			switch (msg) {
			case 0:
		%>
			<h2><%=loginId%>님 환영합니다.</h2>
			<a href="<%= root %>/index.jsp" class="btn btn-primary w-20 py-2 mt-5">메인화면</a>
		<%
			break;
			case 1:
		%>
			<h2 style="text-align:center;">회원가입이 완료되었습니다.</h2>	
			<a href="<%= root %>/index.jsp" class="btn btn-primary w-20 py-2 mt-5">메인화면</a>
		<%
			break;
			case 2:
		%>
			<h2 style="text-align:center;">회원정보가 수정되었습니다..</h2>
			<a href="<%= root %>/index.jsp" class="btn btn-primary w-20 py-2 mt-5">메인화면</a>
		<%
			break;
			case 3:
		%>
			<h2 style="text-align:center;">회원정보가 삭제되었습니다..</h2>
			<a href="<%= root %>/index.jsp" class="btn btn-primary w-20 py-2 mt-5">메인화면</a>
		<%
			break;
			}
		%>
	</div>
	
	<jsp:include page="/layout/footer.jsp" />
	<jsp:include page="/layout/script.jsp" />
</body>
</html>







