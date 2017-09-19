package {export_package};


import com.github.pagehelper.PageInfo;
import com.mysteel.spider.entity.{model_name}Vo;

public interface {model_name}AO {
	
	/**
	 * 新增操作
	 */
	public Boolean insert{model_name}({model_name}Vo {model_name_attr}Vo);
	

	/**
	 * 删除操作
	 */
	public Boolean delete{model_name}(Long id);
	
	/**
	 * 修改操作
	 */
	public Boolean update{model_name}({model_name}Vo {model_name_attr}Vo);
	
	/**
	 * 根据ID查询
	 */
	public {model_name}Vo queryById(Long id);
	
	
	/**
	 * 列表查询
	 */
	public PageInfo<{model_name}Vo> queryByPage({model_name}Vo {model_name_attr}Vo,Integer pageNo,Integer pageSize,String orderBy);

}
