<%@page import="java.io.PrintWriter"%>
<%@page import="com.cos.util.SHA256"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	//code 값 받기
	String code = request.getParameter("code");
	String to = request.getParameter("email");

	//DB에서 id값으로 email과 salt 가져오기

	//return code 값이랑 send code값을 비교해서 동일하면
	boolean rightCode = SHA256.getEncrypt(to, "cos").equals(code) ? true : false;

	PrintWriter script = response.getWriter();
	if (rightCode) {
		//DB에 칼럼 emailCheck(Number) 1 = 인증 , 0 = 미인증 1을 update 해준다.
		script.println("<script>");
		script.println("alert('이메일 인증에 성공하였습니다.')");
		// 인증 완료 로그인 페이지 이동 
		script.println("location.href='/blog/user?cmd=check&email="+to+"'");
		script.println("</script>"); 

	}else{
		script.println("<script>");
		script.println("alert('이메일 인증을 실패하였습니다.')");
		//미인증 error 페이지 이동
		script.println("location.href='error.jsp'");
		script.println("</script>");
	}



	

	
%>