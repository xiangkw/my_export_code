package com.my.export.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.my.export.jdbc.JdbcExecutor;
import com.my.export.model.Column;
import com.my.export.model.Table;
import com.my.export.util.ColumnNameConstants;

@Service
public class TableMessageService {

	public List<Table> getTableMsg(String dbId, String tableNames) {
		List<Table> tableList = new ArrayList<Table>();

		String[] tables = tableNames.split(",");
		Set<String> tableNameSet = new HashSet<String>();
		for (String s : tables) {
			tableNameSet.add(s.toUpperCase());
		}

		JdbcExecutor jdbcExecutor = new JdbcExecutor(dbId);

		// 查询所有的表格信息
		List<Map<String, Object>> allTableList = jdbcExecutor.getTables();

		for (Map<String, Object> map : allTableList) {

			// 表名
			String tableName = map.get(ColumnNameConstants.TABLE_NAME) == null ? ""
					: String.valueOf(map.get(ColumnNameConstants.TABLE_NAME)).toUpperCase();
			if (!tableNameSet.contains(tableName)) {
				continue;
			}

			// 表注释
			String tableComment = map.get(ColumnNameConstants.TABLE_COMMENT) == null ? ""
					: String.valueOf(map.get(ColumnNameConstants.TABLE_COMMENT));

			Table table = new Table();
			table.setName(tableName);
			table.setComment(tableComment);

			tableList.add(table);

			// 查询表的字段属性
			List<Column> columnList = new ArrayList<Column>();

			// 列名和列数据类型
			List<Map<String, Object>> tableColumnInfo = this.getTableColumnInfo(jdbcExecutor, tableName);

			Set<String> cSet = new HashSet<String>();
			for (int i = 0; i < tableColumnInfo.size(); i++) {
				Map<String, Object> tmap = tableColumnInfo.get(i);

				// 字段名
				String columnName = String.valueOf(tmap.get(ColumnNameConstants.COLUMN_NAME));
				if (cSet.contains(columnName)) {
					continue;
				} else {
					cSet.add(columnName);
				}
				// 字段类型
				String columnType = String.valueOf(tmap.get(ColumnNameConstants.COLUMN_TYPE)).toUpperCase();
				if (columnType.indexOf("(") > 0) {
					columnType = columnType.substring(0, columnType.indexOf("("));
				}

				// 字段注释
				String columnComment = tmap.get(ColumnNameConstants.COLUMN_COMMENT) == null ? ""
						: String.valueOf(tmap.get(ColumnNameConstants.COLUMN_COMMENT));

				Column c = new Column();
				c.setName(columnName);
				c.setJdbcType(columnType);
				c.setComment(columnComment);

				columnList.add(c);
			}
			table.setColumns(columnList);
		}
		jdbcExecutor.close();
		return tableList;
	}

	private List<Map<String, Object>> getTableColumnInfo(JdbcExecutor jdbcExecutor, String tableName) {
		List<Map<String, Object>> tableColumnInfo = null;
		try {
			tableColumnInfo = jdbcExecutor.getTableColumnInfo(tableName);
			if (tableColumnInfo.size() == 0) {
				tableColumnInfo = jdbcExecutor.getTableColumnInfo(tableName.toLowerCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tableColumnInfo;
	}
}
