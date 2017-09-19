package {export_package};

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.mysteel.spider.entity.{model_name}Vo;
import com.mysteel.web.{model_name_attr_l}.{model_name}UrlUtil;
import com.mysteel.web.{model_name_attr_l}.ao.{model_name}AO;
import com.mysteel.web.utils.BaseControllerSupport;
import com.mysteel.web.utils.DataTablesParameters;


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
	public Object listData(HttpServletRequest request,{model_name}Vo {model_name_attr}Vo) throws Exception{
		
		DataTablesParameters tables = DataTablesParameters.newInstance();
		
		PageInfo<{model_name}Vo> pageInfo = {model_name_attr}AO.queryByPage({model_name_attr}Vo, tables.getPage(), tables.getLength(),null);
		
		return  tables.getDataTablesReply(pageInfo);
	}
}
