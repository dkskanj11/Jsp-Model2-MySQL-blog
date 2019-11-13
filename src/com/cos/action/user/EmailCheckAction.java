package com.cos.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.util.Script;

public class EmailCheckAction implements Action{

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		
		UserDao dao = new UserDao();
		
		int result = dao.ECheck(email);
		
				if(result == 1) {
					response.sendRedirect("/blog/index.jsp");
				}else {
					Script.back(response);
				}
	}
}
