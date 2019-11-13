package com.cos.action.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.CommentDao;
import com.cos.model.Comment;
import com.cos.model.User;
import com.cos.util.Script;
import com.google.gson.Gson;

public class CommentWriteAction implements Action{

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String content = request.getParameter("content");
		
		System.out.println(userId+" "+boardId+" "+content);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Comment commentForm = new Comment();
		commentForm.setUserId(user.getId());
		commentForm.setBoardId(boardId);
		commentForm.setContent(content);
		
		
		CommentDao dao = new CommentDao();
		int result = dao.save(commentForm);
		
		if(result == 1) {
			Comment comment = dao.findByMaxId();
			comment.getResponseData().setStatusCode(1);
			comment.getResponseData().setStatus("ok");
			comment.getResponseData().setStatusMessage("write success");
//			response.sendRedirect("/blog/board?cmd=detail&id="+boardId);
			
			//Gson을 이용해서 Json으로 변환
			Gson gson = new Gson();
			String commentJson = gson.toJson(comment);
			
			System.out.println(commentJson);
			
			//데이터 응답
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(commentJson);
			out.flush();
		}else {
			Script.back(response);
		}
		
	}
}
