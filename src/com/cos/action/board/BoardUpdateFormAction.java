package com.cos.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.model.Board;
import com.cos.util.Script;
import com.cos.util.Utils;

public class BoardUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("id") == null || request.getParameter("id").equals(""))
			return;

		int id = Integer.parseInt(request.getParameter("id"));

		BoardDao dao = new BoardDao();
		Board board = dao.findById(id);

		if (board != null) {
			// 조회수 증가 - 쿠키를 저장해서(1일) 쿠키가 있을 때 새로고침 방지
			int result = dao.increaseReadCount(id);

			if (result == 1) {

				// board를 request에 담고 dispatcher 이동
				request.setAttribute("board", board);
				RequestDispatcher dis = request.getRequestDispatcher("board/updateForm.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response);
			}

		} else {
			Script.back(response);
		}

	}
}
