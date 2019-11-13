package com.cos.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class UserUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//목적 - form 태그에 있는 name값을 받아서 DB에 insert 하고 나서 페이지 이동
		
		// 나중에 null값 처리하기, 유효성 검사
		int id = Integer.parseInt(request.getParameter("id")); //회원정보 수정 추가
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String profile = request.getParameter("profile");
		String password = SHA256.getEncrypt(rawPassword, "cos"); //Encryption 암호화 함
		
		User user = new User();
		
		user.setId(id); //회원정보 수정 추가
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setAddress(address);
		user.setUserProfile(profile);
		
		//DAO 연결하기
		UserDao dao = new UserDao();
		int result = dao.update(user);
		
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("/blog/index.jsp");
		}else {
			Script.back(response);
		}
	}
}
