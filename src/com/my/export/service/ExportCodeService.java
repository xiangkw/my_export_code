package com.my.export.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.export.jdbc.JdbcExecutor;
import com.my.export.model.Column;
import com.my.export.model.Table;
import com.my.export.util.CodeUtils;
import com.my.export.util.DateUtil;
import com.my.export.util.FileUtils;
import com.my.export.util.ZipCompressor;

@Service
public class ExportCodeService {
	
	@Autowired
	private TableMessageService tableMessageService;

	//输出所有表名和注释
	public List<Map<String,Object>> listTables(String dbId){
		JdbcExecutor jdbcExecutor = new JdbcExecutor(dbId);
		List<Map<String,Object>> list =  jdbcExecutor.getTables();
		jdbcExecutor.close();
		return list;
	}
	
	public String doExport(String dbId, String tableNames, String template, String basePakeage){
		
		//获取表格的信息
		List<Table> tables = tableMessageService.getTableMsg(dbId, tableNames);
		
		//模板路径
		String templatePath = this.getTemplateFolderPath() + File.separator + template + File.separator;
		
		//输出路径
		String outPath = templatePath + DateUtil.formatDateTime(DateUtil.DATE_FORMAT4, new Date());
		
		for(Table table : tables){
			
			//构造模板中需要用到的关键字
			Map<String,String> map  = getKeywordsMap(table,basePakeage);
			
			Map<String, String> fileMap = CodeUtils.getFilePackageMap(basePakeage);
			
			for(String file : fileMap.keySet()){
				try {
					
					String packageStr = fileMap.get(file);
					if(packageStr.indexOf("{") != -1){
						packageStr = this.replaceLine(map, packageStr).toLowerCase();
					}
					map.put("export_package", packageStr);
					
					File f = new File(templatePath + file);
					if(!f.exists()){
						continue;
					}
					
					List<String> lines = FileUtils.readFile(f);
					
					StringBuilder sbl = new StringBuilder();
					
					for(String line : lines){
						sbl.append(this.replaceLine(map, line)).append("\n");
					}
					
					String fullPath = outPath  + File.separator + packageStr.replace(".", File.separator) + File.separator + map.get("model_name");
					if("Model.java".equals(file)){
						fullPath += ".java";
					}else{
						fullPath += file;
					}
					
					FileUtils.writeToFile(fullPath, sbl.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		//压缩文件
		String zipFilePath = outPath + ".zip";
		ZipCompressor compress = new ZipCompressor(zipFilePath);
		compress.compress(outPath);
		
		//删除文件
		FileUtils.deleteFile(outPath);
		
		return zipFilePath;
	}
	
	//封装模板中需要用到的关键字
	private Map<String,String> getKeywordsMap(Table table,String basePakeage){
		Map<String,String> map = new HashMap<String,String>();
		
		//表名
		map.put("Base_TableName", table.getName());
		
		//表注释
		map.put("table_comment", table.getComment());
		
		//类名
		String className = CodeUtils.getClassName(table.getName());
		map.put("model_name", className);
		map.put("vo_name", className + "Vo");
		
		map.put("model_name_attr", CodeUtils.getLowAttr(className));
		map.put("model_name_attr_l", className.toLowerCase());
		map.put("vo_name_attr", CodeUtils.getLowAttr(className+ "Vo"));
		
		
		StringBuilder Base_Column_Type = new StringBuilder();
		StringBuilder Base_Column_List = new StringBuilder();
		
		StringBuilder Insert_Columns = new StringBuilder();
		StringBuilder Insert_Values = new StringBuilder();
		StringBuilder Update_Columns = new StringBuilder();
		
		StringBuilder Where_Columns = new StringBuilder();
		
		StringBuilder java_properties = new StringBuilder();
		StringBuilder java_properties_getset = new StringBuilder();
		
		List<Column> columns =  table.getColumns();
		
		int n = 0;
		for(Column column  : columns){
			
			String columnName = column.getName();
			String attrName = CodeUtils.getAttrName(columnName);
			
			String jdbcType = column.getJdbcType();
			String javaType = CodeUtils.getJavaType(jdbcType);
			String desc = column.getComment();
			
			if(jdbcType.equalsIgnoreCase("int")){
				jdbcType = "INTEGER";
			}
			if(Base_Column_Type.length() > 0){
				Base_Column_Type.append("\n");
			}
			
			Base_Column_Type.append("\t<result column=\""+columnName+"\" property=\""+attrName+"\" jdbcType=\""+jdbcType+"\" />");
			
			if(Base_Column_List.length() > 0){
				Base_Column_List.append(", ");
			}
			if(n > 0 && n % 5 == 0){
				Base_Column_List.append("\n\t");
			}
			Base_Column_List.append(columnName);
			
			if(columnName.equals("id")){
				continue;
			}
			//insert
			Insert_Columns.append(CodeUtils.getSqlInsertColumn(attrName, columnName));
			Insert_Values.append(CodeUtils.getSqlInsertValue(attrName, columnName, jdbcType));
			
			//update
			Update_Columns.append(CodeUtils.getSqlUpdateColumn(attrName, columnName, jdbcType));
			
			//where
			Where_Columns.append(CodeUtils.getSqlWhere(attrName, columnName, jdbcType));
			
			if(!attrName.equalsIgnoreCase("updateTime")){
				java_properties.append(CodeUtils.getJavaProperty(attrName, javaType, desc));
	        	
				java_properties_getset.append(CodeUtils.getJavaPropertyGetSet(attrName, javaType, desc));
			}
			
			
			n ++;
		}
		
		map.put("Base_Column_Type", Base_Column_Type.toString());
		map.put("Base_Column_List", Base_Column_List.toString());
		
		map.put("Insert_Columns", Insert_Columns.toString());
		map.put("Insert_Values", Insert_Values.toString());
		
		map.put("Update_Columns", Update_Columns.toString());
		map.put("Where_Columns", Where_Columns.toString());
		
		map.put("java_properties", java_properties.toString());
		map.put("java_properties_getset", java_properties_getset.toString());
		
		map.put("base_pakeage", basePakeage);
		
		return map;
	}
	
	private String getTemplateFolderPath(){
		String key = "WEB-INF";
		String path = this.getClass().getResource("/").getPath();
		path = path.substring(0, path.indexOf(key) + key.length());
		return path;
	}
	
	private String replaceLine(Map<String,String> map,String line){
		if(line.trim().length() == 0){
			return line;
		}
		
		for(String key : map.keySet()){
			if(line.indexOf(key) != -1){
				line = line.replaceAll("\\{"+key+"\\}", map.get(key));
			}
		}
		
		return line;
	}
}
