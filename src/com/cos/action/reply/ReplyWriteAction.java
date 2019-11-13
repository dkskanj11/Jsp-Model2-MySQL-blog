package com.cos.action.reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.ReplyDao;
import com.cos.model.Comment;
import com.cos.model.Reply;
import com.cos.model.User;
import com.cos.util.Script;
import com.google.gson.Gson;

public class ReplyWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String content = request.getParameter("content");
		
		System.out.println(userId+" "+commentId+" "+content);
		
		
		Reply replyForm = new Reply();
		replyForm.setUserId(userId);
		replyForm.setCommentId(commentId);
		replyForm.setContent(content);
		
		ReplyDao dao = new ReplyDao();
		
		int result = dao.save(replyForm);
		
		if(result == 1) {
			Reply reply = dao.findByMaxId();
			reply.getResponseData().setStatusCode(1);
			reply.getResponseData().setStatus("ok");
			reply.getResponseData().setStatusMessage("write success");
			
			//Gson을 이용해서 Json으로 변환
			Gson gson = new Gson();
			String replyJson = gson.toJson(reply);
			
			System.out.println(replyJson);
			
			//데이터 응답
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(replyJson);
			out.flush();
		}else {
			Script.back(response);
		}
		
	}
}
