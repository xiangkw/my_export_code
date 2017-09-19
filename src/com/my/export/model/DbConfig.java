package com.my.export.model;

import java.util.Map;

public class DbConfig {

	private String dbId;
	
	private String desc;
	
	private String url;
	
	private String username;
	
	private String password;
	
	private String template;
	
	private String basePakeage;

	public DbConfig(String dbId, String desc) {
		super();
		this.dbId = dbId;
		this.desc = desc;
	}

	public String getDbId() {
		return dbId;
	}

	public void setDbId(String dbId) {
		this.dbId = dbId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getBasePakeage() {
		return basePakeage;
	}

	public void setBasePakeage(String basePakeage) {
		this.basePakeage = basePakeage;
	}
	
	public void setAttr(Map<String,String> map){
		if(map.get("url") != null){
			this.url = map.get("url");
		}
		
		if(map.get("username") != null){
			this.username = map.get("username");
		}
		
		if(map.get("password") != null){
			this.password = map.get("password");
		}
		
		if(map.get("template") != null){
			this.template = map.get("template");
		}
		
		if(map.get("basePakeage") != null){
			this.basePakeage = map.get("basePakeage");
		}
	}
}
