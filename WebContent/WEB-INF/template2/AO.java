package {export_package};


import com.github.pagehelper.PageInfo;
import {base_pakeage}.model.{model_name};

public interface {model_name}AO {
	
	/**
	 * 新增操作
	 */
	public Boolean insert{model_name}({model_name} {model_name_attr});
	

	/**
	 * 删除操作
	 */
	public Boolean delete{model_name}(Long id);
	
	/**
	 * 修改操作
	 */
	public Boolean update{model_name}({model_name} {model_name_attr});
	
	/**
	 * 根据ID查询
	 */
	public {model_name} queryById(Long id);
	
	
	/**
	 * 列表查询
	 */
	public PageInfo<{model_name}> queryByPage({model_name} {model_name_attr},Integer pageNo,Integer pageSize,String orderBy);

}
