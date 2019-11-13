package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cos.model.Board;
import com.cos.model.User;
import com.cos.util.DBClose;

public class BoardDao {

	private Connection conn; // MySQL과 연결하기 위해 필요
	private PreparedStatement pstmt; // 쿼리문을 작성 - 실행하기 위해 필요
	private ResultSet rs; // 결과를 보관할 커서

	//인기리스트 3건 가져오기
	public List<Board> findOrderByReadCountDesc() {
		final String SQL = "SELECT * FROM board ORDER BY readCount DESC limit 3";
		conn = DBConn.getConnection();
		try {
			List<Board> boards = new ArrayList<Board>();
			
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				
				boards.add(board);
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	
	//조회수 증가
	public int increaseReadCount(int id) {

		final String SQL = "UPDATE board SET readCount = readCount + 1 WHERE id = ?";

		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return -1;
	}
	
	public int save(Board board) {

		final String SQL = "INSERT INTO board(userId, title, content, createDate) VALUES(?,?,?,now())";

		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return -1;
	}
	//리스트 보기
	public List<Board> findAll() {
		final String SQL = "SELECT * FROM board ORDER BY id DESC";
		conn = DBConn.getConnection();
		try {
			List<Board> boards = new ArrayList<Board>();
			
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				
				boards.add(board);
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public List<Board> findAll(int page) {
		final String SQL = "SELECT * FROM board b, user u WHERE b.userId = u.id ORDER BY b.id DESC limit ?, 3";
		conn = DBConn.getConnection();
		try {
			List<Board> boards = new ArrayList<Board>();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, (page-1)*3);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content")+" ");
				board.setReadCount(rs.getInt("b.readCount"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				
				board.getUser().setUsername(rs.getString("u.username"));
				boards.add(board);
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public List<Board> findAll(int page, String search) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT * FROM board b, user u ");
		sb.append("WHERE b.userId = u.id ");
		sb.append("and (b.content like ? or b.title like ?) ");
		sb.append("ORDER BY b.id DESC limit ?, 3");
		
		final String SQL = sb.toString();
		conn = DBConn.getConnection();
		try {
			List<Board> boards = new ArrayList<Board>();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, (page-1)*3);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content")+" ");
				board.setReadCount(rs.getInt("b.readCount"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				
				board.getUser().setUsername(rs.getString("u.username"));
				boards.add(board);
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	
	//상세 보기
	public Board findById(int id) {
		final String SQL = "SELECT * FROM board b, user u WHERE b.userId = u.id and b.id = ?"; 
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content"));
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				board.getUser().setUsername(rs.getString("u.username"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));
				
				return board;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	public int delete(int id) {
		final String SQL = "DELETE FROM board WHERE id = ?";
		conn = DBConn.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			
			int result = pstmt.executeUpdate();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}
	public int update(Board board) {

		final String SQL = "UPDATE board SET title = ?, content = ? WHERE id = ?";

		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return -1;
	}
}
