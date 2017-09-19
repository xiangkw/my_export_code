package {export_package};

import com.mysteel.common.BaseURL;

public class {model_name}UrlUtil  extends BaseURL{
	
	//列表
	public static final String LIST = "/{model_name_attr}/list"+ DYNAMIC_WEB_SUFFIX ;
	public static final String LIST_DATA = "/{model_name_attr}/listData"+ DYNAMIC_WEB_SUFFIX ;
	
	//新增
	public static final String TO_ADD = "/{model_name_attr}/toAdd"+ DYNAMIC_WEB_SUFFIX ;
	public static final String DO_ADD = "/{model_name_attr}/doAdd"+ DYNAMIC_WEB_SUFFIX ;
	
	//修改
	public static final String TO_MODIFY = "/{model_name_attr}/toModify"+ DYNAMIC_WEB_SUFFIX ;
	public static final String DO_MODIFY = "/{model_name_attr}/doModify"+ DYNAMIC_WEB_SUFFIX ;
	
	//删除
	public static final String DO_DELETE = "/{model_name_attr}/delete"+ DYNAMIC_WEB_SUFFIX ;
}
