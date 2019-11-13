package com.cos.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class UserJoinAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//목적 - form 태그에 있는 name값을 받아서 DB에 insert 하고 나서 페이지 이동
		
		// 나중에 null값 처리하기, 유효성 검사
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String password = SHA256.getEncrypt(rawPassword, "cos"); //Encryption 암호화 함
		
		User user = new User();
		
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setAddress(address);
		
		//DAO 연결하기
		UserDao dao = new UserDao();
		int result = dao.save(user);
		
		System.out.println("ffffff");
		if(result == 1) {
			System.out.println("Ddddd");
			response.sendRedirect("/blog/user/emailSend.jsp?email="+email);
		}else {
			Script.back(response);
		}
	}
}
