package com.cos.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class UserLoginAction implements Action{

	private static final String TAG = "UserLoginAction : ";	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//유효성 검사 해야됨
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		String password = SHA256.getEncrypt(rawPassword, "cos");
		
		System.out.println(TAG+"username : "+username+"  "+"password : "+password);
		
		UserDao dao = new UserDao();
		 int result  = dao.FindByUsernameAndPassword(username, password);
		
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; charset=UTF-8");
		 
		 PrintWriter out = response.getWriter();
		 
		 
		 if(result == 1 ) {
			 //쿠키 저장
			 if(rememberMe != null) {
				 System.out.println(TAG+"쿠키저장");
				 Cookie c = new Cookie("username", username);
				 c.setMaxAge(6000);
				 response.addCookie(c);  
			 }else {
				 System.out.println(TAG+"쿠키 삭제");
				 Cookie c = new Cookie("username", null);
				 c.setMaxAge(0);
				 response.addCookie(c);
			 }
			 
			 //세션 등록
			 HttpSession session = request.getSession();
			 
			 //User 객체 가져오기
			 User user = dao.findByUsername(username);
			 session.setAttribute("user", user);
			 response.sendRedirect("/blog/index.jsp");
			 
		 }else {
			 
			 	out.println("<script>");
				out.println("alert('이메일 인증을 해주세요.')");
				out.println("location.href='/blog/user/login.jsp'");
				out.println("</script>");
				out.flush();
//			 Script.back(response); 
		 }
		
	}
}
