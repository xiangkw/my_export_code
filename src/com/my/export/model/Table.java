package com.my.export.model;

import java.util.List;

public class Table {

	/**
	 * 表名
	 */
	private String name;
	
	/**
	 * 备注
	 */
	private String comment;
	
	/**
	 * 字段集合
	 */
	private List<Column> columns;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	
}
