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


@WebServlet("/test/reply")
public class ReplyTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public ReplyTest() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=UTF-8");
		
		//json 데이터를 받기, getReader()
		BufferedReader in = request.getReader();
		String jData = in.readLine();
		//json 데이터 sysout으로 출력해보기
		System.out.println(jData);
		//json 데이터를 java 오브젝트로 변환 (Gson 라이브러리, fromJson())
		Gson gson = new Gson();
		Comment reply = gson.fromJson(jData, Comment.class);
		// java 오브젝트를 sysout으로 출력해보기
		System.out.println(reply.getId());
		System.out.println(reply.getUserId());
		System.out.println(reply.getBoardId());
		System.out.println(reply.getContent());
		System.out.println(reply.getCreateDate());
		
		
		PrintWriter out = response.getWriter();
		out.print("ok");
		out.flush();
	}

}
