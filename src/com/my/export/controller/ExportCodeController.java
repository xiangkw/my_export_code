package com.my.export.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.export.model.DbConfig;
import com.my.export.service.ExportCodeService;
import com.my.export.util.DbConfigUtils;

/**
 * 导出模板对应的代码
 * @author xiangkaiwei
 *
 */
@Controller
public class ExportCodeController extends CommonController{

	@Autowired
	private ExportCodeService exportCodeService;
	
	@RequestMapping(value="export")
	public ModelAndView toExport(HttpServletRequest request,HttpServletResponse response,String dbId){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("export/index");
		
		List<DbConfig> dbList = DbConfigUtils.getAllDbConfig();
		
		DbConfig dbConfig = DbConfigUtils.getDbConfigById(dbId);
		if(dbConfig == null && dbList != null && dbList.size() > 0){
			dbConfig = dbList.get(0);
		}
		if(dbConfig != null){
			mav.addObject("tables", exportCodeService.listTables(dbConfig.getDbId()));
		}
		
		mav.addObject("dbList", dbList);
		
		mav.addObject("dbConfig", dbConfig);
		
		return mav;
	}
	
	@RequestMapping(value="doExport")
	public void doExport(HttpServletRequest request,HttpServletResponse response,String dbId,String tableNames,String template,String basePakeage) throws Exception{
		DbConfig dbConfig = DbConfigUtils.getDbConfigById(dbId);
		if(dbConfig == null){
			  response.setHeader("Content-type", "text/html;charset=UTF-8");  
			response.getWriter().write("错误提示：没有选择要导出的库");
			return;
		}
		
		if(tableNames == null || tableNames.length() == 0){
			  response.setHeader("Content-type", "text/html;charset=UTF-8");  
			response.getWriter().write("错误提示：没有选择要导出的表");
			return;
		}
		
		if(template == null || template.length() == 0){
			template = dbConfig.getTemplate();
		}
		if(basePakeage == null || basePakeage.length() == 0){
			basePakeage = dbConfig.getBasePakeage();
		}
		String filePath =  exportCodeService.doExport(dbId, tableNames, template, basePakeage);
		
		String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
		super.downLoadFile(filePath, response, "code_db" + dbId + "_" + fileName, "zip", true);
	}
	
}
