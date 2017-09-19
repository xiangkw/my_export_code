package {export_package};

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.mysteel.common.BaseControllerSupport;
import com.mysteel.utils.DataTablesParameters;
import com.mysteel.common.UserLoginContext;

import {base_pakeage}.model.{model_name};
import com.mysteel.web.{model_name_attr}.ao.{model_name}AO;
import com.mysteel.web.{model_name_attr}.{model_name}UrlUtil;


/**
 * “{table_comment}”	 Controller类
*/
@Controller
public class {model_name}Controller extends BaseControllerSupport{
	
	@Autowired
	private {model_name}AO {model_name_attr}AO;		
	
	
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping(value = {model_name}UrlUtil.LIST)
	public ModelAndView list() throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("{model_name_attr}/list");
		return modelAndView;
	}
	
	/**
	 * 获取列表页面的数据
	 */
	@RequestMapping(value = {model_name}UrlUtil.LIST_DATA)
	@ResponseBody
	public Object listData(HttpServletRequest request,{model_name} {model_name_attr}) throws Exception{
		
		DataTablesParameters tables = DataTablesParameters.newInstance();
		
		PageInfo<{model_name}> pageInfo = {model_name_attr}AO.queryByPage({model_name_attr}, tables.getPage(), tables.getLength(),null);
		
		return  tables.getDataTablesReply(pageInfo);
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value = {model_name}UrlUtil.DO_DELETE)
	@ResponseBody
	public Object doDelete(Long id) throws Exception{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		boolean result = {model_name_attr}AO.delete{model_name}(id);
		
		resultMap.put("msg", result);
		return resultMap;
	}
	
	/**
	 * 跳转到新增页面
	 */
	@RequestMapping(value = {model_name}UrlUtil.TO_ADD)
	public ModelAndView toAdd() throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("{model_name_attr}/edit");
		return modelAndView;
	}
	
	/**
	 * 跳转到修改页面
	 */
	@RequestMapping(value = {model_name}UrlUtil.TO_MODIFY)
	public ModelAndView toModify(Long id) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		
		{model_name} {model_name_attr} = {model_name_attr}AO.queryById(id);
		
		modelAndView.addObject("{model_name_attr}", {model_name_attr});
		modelAndView.setViewName("{model_name_attr}/edit");
		return modelAndView;
	}
	
	/**
	 * 新增操作
	 */
	@RequestMapping(value = {model_name}UrlUtil.DO_ADD)
	@ResponseBody
	public Object doAdd(HttpServletRequest request,{model_name} {model_name_attr}) throws Exception{
		return doAddOrModify(request,{model_name_attr});
	}
	
	/**
	 * 编辑操作
	 */
	@RequestMapping(value = {model_name}UrlUtil.DO_MODIFY)
	@ResponseBody
	public Object doModify(HttpServletRequest request,{model_name} {model_name_attr}) throws Exception{
		return doAddOrModify(request,{model_name_attr});
	}
	
	/**
	 * 新增或者编辑操作
	 */
	private Object doAddOrModify(HttpServletRequest request,{model_name} {model_name_attr}){
		boolean result = false;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if({model_name_attr} != null){
			UserLoginContext user = super.getSessionUser(request);
			{model_name_attr}.setUpdaterId(user.getUserId());
			{model_name_attr}.setUpdaterName(user.getName());
			if({model_name_attr}.getId() != null){
				result = {model_name_attr}AO.update{model_name}({model_name_attr});
			}else{
				result = {model_name_attr}AO.insert{model_name}({model_name_attr});
			}
		}
		resultMap.put("msg", result);
		return resultMap;
	}
	
}
