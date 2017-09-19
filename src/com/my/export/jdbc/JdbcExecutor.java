package com.my.export.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.export.model.DbConfig;
import com.my.export.util.ColumnNameConstants;
import com.my.export.util.DbConfigUtils;

public class JdbcExecutor {
	private final static String sql1 = "SELECT DISTINCT TABLE_NAME,TABLE_COMMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA=(select DATABASE()) ";
	private final static String sql2 = "SELECT DISTINCT COLUMN_NAME,DATA_TYPE,COLUMN_TYPE,COLUMN_COMMENT,COLUMN_DEFAULT,CHARACTER_MAXIMUM_LENGTH as LENGTH FROM  INFORMATION_SCHEMA.COLUMNS" 
        	+ " WHERE TABLE_NAME='{tableName}' and TAble_schema=(select DATABASE())";
	
	private DBHelper dBHelper;
	
	public JdbcExecutor(String dbId){
		DbConfig dbConfig = DbConfigUtils.getDbConfigById(dbId);
		String url = dbConfig.getUrl();
		String username = dbConfig.getUsername();
		String password = dbConfig.getPassword();
		
		//创建DBHelper对象  
		dBHelper = new DBHelper(url, username, password);
	}
	
	/**
	 * 获取表名和注释
	 * @return
	 */
	public List<Map<String, Object>> getTables(){
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		ResultSet ret = null; 
		PreparedStatement pst = null;
        try {  
        	pst = dBHelper.getStatement(sql1);
        	ret = pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
            	Map<String,Object> map = new HashMap<>();
            	for(String name : ColumnNameConstants.TABLE_LIST){
            		map.put(name, ret.getString(name));
            	}
            	resultList.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        }finally {
        	//关闭连接  
        	try {
				if(ret != null){
					ret.close();
				}
				if(pst != null){
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}


	public List<Map<String, Object>> getTableColumnInfo(String tableName){
		String sql = sql2.replace("{tableName}", tableName);
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		ResultSet ret = null; 
		PreparedStatement pst = null;
        try {  
        	pst = dBHelper.getStatement(sql);
        	ret = pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
            	Map<String,Object> map = new HashMap<>();
            	
            	for(String name : ColumnNameConstants.COLUMN_LIST){
            		map.put(name, ret.getString(name));
            	}
            	
            	resultList.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        }finally {
        	//关闭连接  
        	try {
				if(ret != null){
					ret.close();
				}
				if(pst != null){
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}


    public void close() {  
    	this.dBHelper.close();  
    } 
	
	public static void main(String[] args) {
		/*List<Map<String, Object>> resultList = getTables();
		
		for(Map<String,Object> map : resultList){
			for(String key : map.keySet()){
				System.out.print(key + "=" + map.get(key) + ";\t");
			}
			System.out.println("");
		}*/
	}
}
