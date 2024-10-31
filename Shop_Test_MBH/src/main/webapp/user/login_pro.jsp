<!-- 로그인 처리 -->
<%@page import="shop.dto.PersistentLogin"%>
<%@page import="java.util.UUID"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="shop.dto.User"%>
<%@page import="shop.dao.UserRepository"%>
<%@ include file="/layout/meta.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	
	UserRepository userDAO = new  UserRepository();
	User loginUser = userDAO.login(id, pw);
	
	// 로그인 실패
	if( loginUser == null ) {
		// 에러코드를 들고 다시 로그인 페이지로
		response.sendRedirect( root + "/user/login.jsp");
		return;
	}
	
	// 로그인 성공
	String rememberID 		= request.getParameter("remember-id");
	Cookie cookieRememberID = new Cookie("rememberId","");
	Cookie cookieUserid		= new Cookie("loginId","");
	
	// - 세션에 아이디 등록
	if( loginUser != null ) {
		// 세션에 사용자 정보 등록
		session.setAttribute("loginId", loginUser.getId());
	}
	
	// 아이디 저장
	if( rememberID != null && rememberID.equals("on")) {
		// 쿠키 생성
		cookieRememberID.setValue( URLEncoder.encode(rememberID, "UTF-8") );
		cookieUserid.setValue( URLEncoder.encode(id, "UTF-8") );
	}
	
	// 자동 로그인
	String rememberMe = request.getParameter("remember-me");
	Cookie cookieRememberMe	= new Cookie("rememberMe","");
	Cookie cookieToken = new Cookie("token", "");
	
	// 쿠키 전달
	cookieRememberMe.setPath("/");
	cookieToken.setPath("/");
	cookieRememberMe.setMaxAge(60*60*24*7); 
	cookieToken.setMaxAge(60*60*24*7);
	
	// 자동 로그인 체크 여부
	if( rememberMe != null && rememberMe.equals("on") ) {
		// 자동 로그인 체크 시
		// - 토큰 발행
		UserRepository userRepository = new UserRepository(); 
		String token = userRepository.refreshToken(loginUser.getId());
		// - 쿠키 생성
		cookieRememberMe.setValue( URLEncoder.encode( rememberMe, "UTF-8" ) );
		cookieToken.setValue( URLEncoder.encode( token, "UTF-8" ) );
	}
	
	// 응답에 쿠키 등록
	response.addCookie(cookieRememberID);
	response.addCookie(cookieUserid);
	response.addCookie(cookieRememberMe);
	response.addCookie(cookieToken);
	
	// 로그인 성공 페이지로 이동
	response.sendRedirect("complete.jsp?msg=0");
%>





