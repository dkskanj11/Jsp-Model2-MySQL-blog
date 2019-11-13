package com.cos.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.model.Comment;
import com.google.gson.Gson;


@WebServlet("/test")
public class AjaxTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public AjaxTest() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8"); //MIME 타입
		
		//요청 데이터 처리
		BufferedReader in = request.getReader();
		String msg = in.readLine();
		System.out.println("요청 데이터 : "+msg);
		
		Gson gson = new Gson();
		Comment reply = gson.fromJson(msg, Comment.class);
		
		System.out.println(reply.getId());
		System.out.println(reply.getUserId());
		System.out.println(reply.getBoardId());
		System.out.println(reply.getContent());
		System.out.println(reply.getCreateDate());
		//응답 데이터 처리
		String jsonData = "{\"name\" : \"손흥민\", \"sal\" : 100}";
		PrintWriter out = response.getWriter();
		out.println(jsonData);
		out.flush();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
