<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="userDAO" class="shop.dao.UserRepository" />

<%
	String id = request.getParameter("id");
	int result = userDAO.delete(id);
	if(result == 1){
		// 자동 로그인, 토큰 쿠키 삭제
		Cookie cookieRememberMe = new Cookie("rememberMe","");
		Cookie cookieToken = new Cookie("token","");
		cookieRememberMe.setPath("/");
		cookieToken.setPath("/");
		cookieRememberMe.setMaxAge(0);
		cookieToken.setMaxAge(0);
		String loginId = (String) session.getAttribute("loginId");
		if( loginId != null ) {
			// 토큰 삭제
			int deleted = userDAO.deleteToken(loginId);
			if( deleted==1 ) 	out.println("인증 토큰 데이터 삭제 성공");
			else			out.println("인증 토큰 데이터 삭제 실패");
		}
		// 세션 무효화
		session.invalidate();
		// 쿠키 전달
		response.addCookie(cookieRememberMe);
		response.addCookie(cookieToken);
		response.sendRedirect("complete.jsp?msg=3");
	}else{
		response.sendRedirect("join.jsp");
	}
%>
