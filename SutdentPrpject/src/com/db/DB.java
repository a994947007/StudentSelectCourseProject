package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.xml.internal.ws.Closeable;
/**
 * 数据库驱动操作类
 * @author Lenovo
 *
 */
public class DB {
	
	public static Connection getConnection(){
		Connection connection = null;
		try{
			Class.forName(Config.getInstance().getConfig("driver"));
			connection = DriverManager.getConnection(Config.getInstance().getConfig("url")
					,Config.getInstance().getConfig("username")
					,Config.getInstance().getConfig("password"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;
	}

	//创建数据库上下文
	public static Statement createStatement(Connection connection){
		Statement s = null;
		try {
			 s = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
		
	}
	
	
	public static PreparedStatement prePareStatement(Connection connection,String sql){
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static void close(Closeable closeable){
		if(closeable != null){
			closeable.close();
			closeable = null;
		}
	}
	
	//关闭连接
	public static void close(Connection connection){
		try {
			if(connection != null){
				connection.close();
				connection = null;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//关闭statament
	public static void close(Statement stmt){
		try {
			if(stmt != null){
				stmt.close();
				stmt = null;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//关闭数据集
	public static void close(ResultSet rs){
		try {
			if(rs != null){
				rs.close();
				rs = null;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
