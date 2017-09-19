package {export_package};

import {base_pakeage}.model.{model_name};

import com.mysteel.util.PageVo;
import com.github.pagehelper.PageInfo;

/**
 * “{table_comment}”	业务接口类
*/
public interface {model_name}Service{

	/**
	 * 新增接口
	*/
	public int insert({model_name} {vo_name_attr});

	/**
	 * 根据ID删除接口
	*/
	public int delete(Long id);

	/**
	 * 根据ID更新接口
	*/
	public int update({model_name} {vo_name_attr});

	/**
	 * 根据ID查找接口
	*/
	public {model_name} findById(Long id);

	/**
	 * 分页查找接口
	*/
	public PageInfo<{model_name}> findByPage({model_name} {vo_name_attr},int pageNo,int pageSize);
	
	/**
	 * 分页查找接口（可带分页条件）
	*/
	public PageInfo<{model_name}> findByPage({model_name} {vo_name_attr},PageVo pageVo);

}
