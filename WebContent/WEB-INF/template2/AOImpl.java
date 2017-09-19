package {export_package};


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import {base_pakeage}.model.{model_name};
import com.mysteel.util.PageVo;
import {base_pakeage}.service.{model_name}Service;
import com.mysteel.web.{model_name_attr_l}.ao.{model_name}AO;

@Service("{model_name_attr}AO")
public class {model_name}AOImpl implements {model_name}AO {


    @Autowired
    private {model_name}Service {model_name_attr}Service;

    
    /**
	 * 新增操作
	 */
    @Override
	public Boolean insert{model_name}({model_name} {model_name_attr}){
		if({model_name_attr} == null){
			return false;
		}
		int result = {model_name_attr}Service.insert({model_name_attr});
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}
	

	/**
	 * 删除操作
	 */
	@Override
	public Boolean delete{model_name}(Long id){
		if(id == null){
			return false;
		}
		
		int result = {model_name_attr}Service.delete(id);
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改操作
	 */
	@Override
	public Boolean update{model_name}({model_name} {model_name_attr}){
		if({model_name_attr} == null || {model_name_attr}.getId() == null){
			return false;
		}
		
		int result = {model_name_attr}Service.update({model_name_attr});
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据ID查询
	 */
	@Override
	public {model_name} queryById(Long id){
		if(id == null){
			return null;
		}
		
		return  {model_name_attr}Service.findById(id);
	}
	
	/**
	 * 列表查询
	 */
	@Override
	public PageInfo<{model_name}> queryByPage({model_name} {model_name_attr},Integer pageNo,Integer pageSize,String orderBy){
		return {model_name_attr}Service.findByPage({model_name_attr},new PageVo(pageNo, pageSize, orderBy));
	}
}
