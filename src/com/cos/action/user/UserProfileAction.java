package com.cos.action.user;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.Script;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserProfileAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getRealPath("media");


	 	MultipartRequest multi = new MultipartRequest(
				request,
				path,
				1024*1024*2, //2mb
				"UTF-8",
				new DefaultFileRenamePolicy() //동일한 파일명이 들어오면 파일명 뒤에 숫자를 붙힘
				
				
			);
	 	
	 	ServletContext context = request.getSession().getServletContext();
	 	
	 	String username = multi.getParameter("username");
	 	String filename = multi.getFilesystemName("userProfile"); // 정책에 의해서 변경된 이름
	 	
	 	String contextPath = context.getContextPath();
	 	String filepath = contextPath+"/media/"+filename;
	 	
	 	
	 	
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		user.setUserProfile(filepath);
		
		UserDao dao = new UserDao();
		int result = dao.profileUpdate(user);
		
		if(result == 1) {
			
			response.sendRedirect("/blog/index.jsp");
		}else {
			Script.back(response);
		}
	}
}
