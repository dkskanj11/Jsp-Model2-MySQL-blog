package com.cos.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBClose {
 public static void close(Connection conn, PreparedStatement pstmt) {
	 
	 /*
	  *  DML (INSERT, UPDATE, DELETE) CLOSE
	  */
	 try {
		conn.close();
		pstmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
 }
 
public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
	 
	 /*
	  *  DQL (INSERT, UPDATE, DELETE) CLOSE
	  */
	 try {
		conn.close();
		pstmt.close();
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
 }
}
