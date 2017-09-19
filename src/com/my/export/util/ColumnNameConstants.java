package com.my.export.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 列名常量
 * 
 * @author xiangkaiwei
 *
 */
public class ColumnNameConstants {

	public static final String TABLE_NAME = "TABLE_NAME"; // 表名
	public static final String TABLE_COMMENT = "TABLE_COMMENT"; // 表名注释

	public static final String COLUMN_NAME = "COLUMN_NAME"; // 字段名
	public static final String COLUMN_TYPE = "COLUMN_TYPE"; // 字段类型
	public static final String COLUMN_COMMENT = "COLUMN_COMMENT"; // 字段注释

	public static List<String> TABLE_LIST = new ArrayList<>();
	public static List<String> COLUMN_LIST = new ArrayList<>();

	static {
		TABLE_LIST.add(TABLE_NAME);
		TABLE_LIST.add(TABLE_COMMENT);

		COLUMN_LIST.add("COLUMN_NAME");
		COLUMN_LIST.add("COLUMN_TYPE");
		COLUMN_LIST.add("COLUMN_COMMENT");
	}
}
