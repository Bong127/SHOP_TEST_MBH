<!-- 
	회원 가입 처리
 -->
<%@page import="shop.dao.UserRepository"%>
<%@page import="shop.dto.User"%>
<%@ include file="/layout/meta.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	User user = new User();
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String birth = request.getParameter("year")+"/"+request.getParameter("month")+"/"+request.getParameter("day");
	String email = request.getParameter("email1")+"@"+request.getParameter("email2");
	String phone = request.getParameter("phone");
	String address = request.getParameter("address");
	
	user.setId(id);
	user.setPassword(pw);
	user.setName(name);
	user.setGender(gender);
	user.setBirth(birth);
	user.setMail(email);
	user.setPhone(phone);
	user.setAddress(address);
	
	UserRepository repository = new UserRepository();
	
	int result = repository.insert(user);
	
	if(result == 1){
		response.sendRedirect("complete.jsp?msg=1");
	}else{
		response.sendRedirect("join.jsp");
	}
%>
    
    

    
    
    
    
    
    