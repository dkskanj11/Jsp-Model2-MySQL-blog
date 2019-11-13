package com.cos.action.reply;

import com.cos.action.Action;

public class ReplyFactory {
	public static Action getAction(String cmd) {
		if(cmd.equals("delete") ) {
			return new ReplyDeleteAction();
		}if(cmd.equals("write")) {
			return new ReplyWriteAction();
		}if(cmd.equals("list")) {
			return new ReplyListAction();
		}
		
		return null;
	}
}
