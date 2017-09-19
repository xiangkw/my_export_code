package {export_package};

import java.util.List;

import {base_pakeage}.model.{model_name};

/**
 * “{table_comment}”	 DAO类
*/
public interface {model_name}Dao{

	/**
	 * 插入接口
	*/
	public int insert({model_name} {model_name_attr});

	/**
	 * 根据ID删除接口
	*/
	public int delete(Long id);

	/**
	 * 根据ID更新接口
	*/
	public int update({model_name} {model_name_attr});

	/**
	 * 根据ID查找接口
	*/
	public {model_name} findById(Long id);

	/**
	 * 分页查找接口
	*/
	public List<{model_name}> findList({model_name} {model_name_attr});

}
