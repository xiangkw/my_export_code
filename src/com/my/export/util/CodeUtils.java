package com.my.export.util;

import java.util.HashMap;
import java.util.Map;

public class CodeUtils {

	public static Map<String, String> getFilePackageMap(String basePakeage) {
		if (basePakeage.endsWith(".")) {
			basePakeage = basePakeage.substring(0, basePakeage.length() - 1);
		}
		Map<String, String> fileMap = new HashMap<String, String>();

		fileMap.put("Mapper.xml", "mapper");

		fileMap.put("Model.java", basePakeage + ".model");
		fileMap.put("Vo.java", basePakeage + ".entity");

		fileMap.put("Service.java", basePakeage + ".service");
		fileMap.put("ServiceExport.java", basePakeage + ".service.export");
		fileMap.put("ServiceImpl.java", basePakeage + ".service.impl");

		fileMap.put("Dao.java", basePakeage + ".dao");

		fileMap.put("Controller.java", basePakeage + ".{model_name}.controller");
		fileMap.put("AO.java", basePakeage + ".{model_name}.ao");
		fileMap.put("AOImpl.java", basePakeage + ".{model_name}.ao.impl");
		fileMap.put("UrlUtil.java", basePakeage + ".{model_name}");

		return fileMap;
	}

	/**
	 * 首字母变小写
	 */
	public static String getLowAttr(String attr) {
		return attr.substring(0, 1).toLowerCase() + attr.substring(1);
	}

	/**
	 * 首字母变大写
	 */
	public static String getUpAttr(String attr) {
		return attr.substring(0, 1).toUpperCase() + attr.substring(1);
	}

	/**
	 * 表名变成类名
	 */
	public static String getClassName(String tableName) {
		tableName = tableName.toUpperCase();
		if (tableName.startsWith("TB_") || tableName.startsWith("DB_")) {
			tableName = tableName.substring(3);
		}
		if (tableName.startsWith("VIP_") || tableName.startsWith("VIP_")) {
			tableName = tableName.substring(4);
		}

		String ts[] = tableName.split("_");
		String result = "";
		for (String xs : ts) {
			result += xs.substring(0, 1).toUpperCase() + xs.substring(1).toLowerCase();
		}
		return result;
	}

	/**
	 * 表字段名变属性名
	 */
	public static String getAttrName(String columnName) {
		columnName = columnName.toUpperCase();

		String ts[] = columnName.split("_");
		String result = "";
		for (String xs : ts) {
			result += xs.substring(0, 1).toUpperCase() + xs.substring(1).toLowerCase();
		}

		return result.substring(0, 1).toLowerCase() + result.substring(1);
	}

	/**
	 * 数据库数据类型映射java数据类型
	 * 
	 * @param jdbcType
	 * @return
	 */
	public static String getJavaType(String jdbcType) {
		jdbcType = jdbcType.toUpperCase();
		if (jdbcType.startsWith("VARCHAR") || jdbcType.startsWith("TEXT") || jdbcType.startsWith("MEDIUMTEXT")
				|| jdbcType.startsWith("CHAR")) {
			return "String";
		} else if (jdbcType.startsWith("BIGINT")) {
			return "Long";
		} else if (jdbcType.startsWith("INT") || jdbcType.startsWith("TINYINT")) {
			return "Integer";
		} else if (jdbcType.startsWith("DOUBLE")) {
			return "Double";
		}

		return "";
	}

	/**
	 * 获取java类属性的定义
	 * 
	 * @param attrName
	 *            属性名
	 * @param javaType
	 *            java类型
	 * @param desc
	 *            注释
	 * @return
	 */
	public static String getJavaProperty(String attrName, String javaType, String desc) {
		StringBuilder sbl = new StringBuilder();
		sbl.append("\t/**").append("\n");
		sbl.append("\t * ").append(desc).append("\n");
		sbl.append("\t*/").append("\n");
		sbl.append("\tprivate " + javaType + " " + attrName).append(";\n");
		sbl.append("\n");

		return sbl.toString();
	}

	/**
	 * 获取java类属性的get&set方法
	 * 
	 * @param attrName
	 *            属性名
	 * @param javaType
	 *            java类型
	 * @param desc
	 *            注释
	 * @return
	 */
	public static String getJavaPropertyGetSet(String attrName, String javaType, String desc) {

		StringBuilder sbl = new StringBuilder();
		sbl.append("\t/**").append("\n");
		sbl.append("\t * ").append("GET.").append(desc).append("\n");
		sbl.append("\t*/").append("\n");
		sbl.append("\tpublic " + javaType + " get" + getUpAttr(attrName)).append("(){\n");
		sbl.append("\t\treturn this.").append(attrName).append(";\n");
		sbl.append("\t}").append("\n").append("\n");

		sbl.append("\t/**").append("\n");
		sbl.append("\t * ").append("SET.").append(desc).append("\n");
		sbl.append("\t*/").append("\n");
		sbl.append("\tpublic void set" + getUpAttr(attrName)).append("(" + javaType + " " + attrName + "){\n");
		sbl.append("\t\t this.").append(attrName).append("=").append(attrName).append(";\n");
		sbl.append("\t}").append("\n").append("\n");

		return sbl.toString();
	}

	public static String getSqlInsertColumn(String attrName, String columnName) {
		StringBuilder sbl = new StringBuilder();
		sbl.append("\t\t").append("<if test=\"" + attrName + " != null\" >").append("\n\t\t\t").append(columnName)
				.append(",\n\t\t</if>\n");
		return sbl.toString();
	}

	public static String getSqlInsertValue(String attrName, String columnName, String jdbcType) {
		StringBuilder sbl = new StringBuilder();
		sbl.append("\t\t").append("<if test=\"" + attrName + " != null\" >").append("\n\t\t\t")
				.append("#{" + attrName + ",jdbcType=" + jdbcType + "}").append(",\n\t\t</if>\n");
		return sbl.toString();
	}

	public static String getSqlUpdateColumn(String attrName, String columnName, String jdbcType) {
		StringBuilder sbl = new StringBuilder();
		sbl.append("\t\t").append("<if test=\"" + attrName + " != null\" >").append("\n\t\t\t")
				.append(columnName + " = #{" + attrName + ",jdbcType=" + jdbcType + "}").append(",\n\t\t</if>\n");
		return sbl.toString();
	}

	public static String getSqlWhere(String attrName, String columnName, String jdbcType) {
		StringBuilder sbl = new StringBuilder();
		if (jdbcType.equals("VARCHAR")) {
			sbl.append("\t\t").append("<if test=\"" + attrName + " != null and " + attrName + " != ''\" >")
					.append("\n\t\t\t").append("AND " + columnName + " LIKE CONCAT('%',TRIM(#{" + attrName + "}),'%' )")
					.append("\n\t\t</if>\n");
		} else {
			sbl.append("\t\t").append("<if test=\"" + attrName + " != null\" >").append("\n\t\t\t")
					.append("AND " + columnName + " = #{" + attrName + ",jdbcType=" + jdbcType + "}")
					.append("\n\t\t</if>\n");
		}
		return sbl.toString();
	}
}
