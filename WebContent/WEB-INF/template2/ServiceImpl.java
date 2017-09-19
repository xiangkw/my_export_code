package {export_package};

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysteel.util.PageVo;

import {base_pakeage}.model.{model_name};
import {base_pakeage}.service.{model_name}Service;
import {base_pakeage}.dao.{model_name}Dao;

import {base_pakeage}.exception.SystemErrorCode;
import {base_pakeage}.exception.SystemException;


/**
 * “{table_comment}”	业务类
*/
@Service("{model_name_attr}Service")
public class {model_name}ServiceImpl implements {model_name}Service{

	@Autowired
	private {model_name}Dao {model_name_attr}Dao;

	/**
	 * 新增操作
	*/
	public int insert({model_name} {model_name_attr}){
		if({model_name_attr} == null){
			throw  new SystemException(SystemErrorCode.REQUEST_PARAM_ERROR);
		}
		{model_name_attr}.setUpdateTime(System.currentTimeMillis());
		return this.{model_name_attr}Dao.insert({model_name_attr});
	}

	/**
	 * 根据ID删除操作
	*/
	public int delete(Long id){
		if(id == null || id <= 0){
			throw  new SystemException(SystemErrorCode.REQUEST_PARAM_ERROR);
		}
		return this.{model_name_attr}Dao.delete(id);
	}

	/**
	 * 根据ID更新操作
	*/
	public int update({model_name} {model_name_attr}){
		if({model_name_attr} == null || {model_name_attr}.getId() == null){
			throw  new SystemException(SystemErrorCode.REQUEST_PARAM_ERROR);
		}
		{model_name_attr}.setUpdateTime(System.currentTimeMillis());
		return this.{model_name_attr}Dao.update({model_name_attr});
	}

	/**
	 * 根据ID查找操作
	*/
	public {model_name} findById(Long id){
		if(id == null || id <= 0){
			throw  new SystemException(SystemErrorCode.REQUEST_PARAM_ERROR);
		}
		return this.{model_name_attr}Dao.findById(id);
	}

	/**
	 * 分页查找接口
	*/
	public PageInfo<{model_name}> findByPage({model_name} {model_name_attr},int pageNo,int pageSize){
		return this.findByPage({model_name_attr},pageNo,pageSize,null);
	}

	/**
	 * 分页查找接口
	*/
	public PageInfo<{model_name}> findByPage({model_name} {model_name_attr},PageVo pageVo){
		return this.findByPage({model_name_attr}, pageVo.getPageNo(), pageVo.getPageSize(),pageVo.getOrderBy());
	}
	
	/**
	 * 分页查找
	*/
	private PageInfo<{model_name}> findByPage({model_name} {model_name_attr},int pageNo,int pageSize,String orderBy){
		PageHelper.startPage(pageNo, pageSize);
		if (StringUtils.isNotBlank(orderBy)) {
			PageHelper.orderBy(orderBy);
		}
		List<{model_name}> list = this.{model_name_attr}Dao.findList({model_name_attr});
		PageInfo<{model_name}> pageinfo = new PageInfo<{model_name}>(list);
		return pageinfo;
	}
}
