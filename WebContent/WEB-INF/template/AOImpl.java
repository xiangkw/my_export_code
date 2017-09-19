package {export_package};


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.mysteel.common.reponse.SoaResponse;
import com.mysteel.spider.entity.{model_name}Vo;
import com.mysteel.spider.entity.PageVo;
import com.mysteel.spider.entity.response.CommErrorVo;
import com.mysteel.spider.entity.response.ResponsePageVo;
import com.mysteel.spider.entity.response.ResponseVo;
import com.mysteel.spider.service.{model_name}Service;
import com.mysteel.web.{model_name_attr_l}.ao.{model_name}AO;

@Service("{model_name_attr}AO")
public class {model_name}AOImpl implements {model_name}AO {


    @Autowired
    private {model_name}Service {model_name_attr}Service;

    
    /**
	 * 新增操作
	 */
    @Override
	public Boolean insert{model_name}({model_name}Vo {model_name_attr}Vo){
		if({model_name_attr}Vo == null){
			return false;
		}
		SoaResponse<ResponseVo<{model_name}Vo>,CommErrorVo> response = {model_name_attr}Service.insert({model_name_attr}Vo);
		if(response != null && response.getResponseVo() != null){
			return response.getResponseVo().getResult();
		}
		return null;
	}
	

	/**
	 * 删除操作
	 */
	@Override
	public Boolean delete{model_name}(Long id){
		if(id == null){
			return false;
		}
		
		SoaResponse<ResponseVo<{model_name}Vo>, CommErrorVo> response =  {model_name_attr}Service.delete(id);
		if(response != null && response.getResponseVo() != null){
			return response.getResponseVo().getResult();
		}
		return false;
	}
	
	/**
	 * 修改操作
	 */
	@Override
	public Boolean update{model_name}({model_name}Vo {model_name_attr}Vo){
		if({model_name_attr}Vo == null || {model_name_attr}Vo.getId() == null){
			return false;
		}
		
		SoaResponse<ResponseVo<{model_name}Vo>,CommErrorVo> response = {model_name_attr}Service.update({model_name_attr}Vo);
		if(response != null && response.getResponseVo() != null){
			return response.getResponseVo().getResult();
		}
		return null;
	}
	
	/**
	 * 根据ID查询
	 */
	@Override
	public {model_name}Vo queryById(Long id){
		if(id == null){
			return null;
		}
		
		SoaResponse<ResponseVo<{model_name}Vo>, CommErrorVo> response =  {model_name_attr}Service.findById(id);
		
		if(response != null && response.getResponseVo() != null){
			return response.getResponseVo().getResultVo();
		}
		return null;
	}
	
	
	/**
	 * 列表查询
	 */
	@Override
	public PageInfo<{model_name}Vo> queryByPage({model_name}Vo {model_name_attr}Vo,Integer pageNo,Integer pageSize,String orderBy){
		SoaResponse<ResponsePageVo<{model_name}Vo>, CommErrorVo> response =  {model_name_attr}Service.findByPage({model_name_attr}Vo,new PageVo(pageNo, pageSize, orderBy));
		
		if(response != null && response.getResponseVo() != null){
			PageInfo<{model_name}Vo> pageInfo = response.getResponseVo().getPageList();
			if(pageInfo != null && pageInfo.getList() != null && pageInfo.getList().size() > 0){
				return pageInfo;
			}
			return null;
		}
			
		return null;
	}

}
