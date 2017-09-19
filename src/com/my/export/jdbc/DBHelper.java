package com.my.export.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//jdbc链接和执行工具类
public class DBHelper {

	private static final String driverName = "com.mysql.jdbc.Driver";  
  
    private Connection conn = null;  
    
    public DBHelper(String url,String username,String password) {
        try {  
            Class.forName(driverName);//指定连接类型  
            conn = DriverManager.getConnection(url, username, password);//获取连接  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
  
    public PreparedStatement getStatement(String sql){
    	PreparedStatement pst = null;  
    	try {
    		//准备执行语句  
    		pst = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return pst;
    }
    
    public void close() {  
        try {  
            this.conn.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    } 
}
