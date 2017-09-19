package com.my.export.model;

public class Column {

	/**
	 * 字段名
	 */
	private String name;
	
	/**
	 * 字段类型
	 */
	private String jdbcType;
	
	/**
	 * 备注
	 */
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
