package com.cos.action.board;

import com.cos.action.Action;

public class BoardFactory {
	public static Action getAction(String cmd) {
		if(cmd.equals("write")) {
			return new BoardWriteAction();
		}if(cmd.equals("list")) {
			return new BoardListAction();
		}if(cmd.equals("detail")) {
			 return new BoardDetailAction();
		}if(cmd.equals("delete")) {
			 return new BoardDeleteAction();
		}if(cmd.equals("updateForm")) {
			return new BoardUpdateFormAction();
		}if(cmd.equals("update")) {
			return new BoardUpdateAction();
		}
		return null;
	}
}

