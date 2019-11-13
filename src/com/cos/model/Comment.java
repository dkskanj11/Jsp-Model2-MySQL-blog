package com.cos.model;

import java.sql.Timestamp;

public class Comment {
	private ResponseData responseData = new ResponseData();
	private int id;
	private int userId;
	private int boardId;
	private String content;
	private Timestamp createDate;
	private User user = new User(); //DB 와 상관없음
	
	
	
	public Comment(ResponseData responseData) {
		super();
		this.responseData = responseData;
	}

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment(User user) {
		super();
		this.user = user;
	}

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(int id, int userId, int boardId, String content, Timestamp createDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.boardId = boardId;
		this.content = content;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
	
}
