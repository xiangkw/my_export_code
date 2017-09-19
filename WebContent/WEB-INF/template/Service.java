package {export_package};

import {base_pakeage}.entity.{vo_name};

import {base_pakeage}.entity.response.ResponseVo;

import {base_pakeage}.entity.response.ResponsePageVo;
import {base_pakeage}.entity.response.CommErrorVo;
import com.mysteel.common.reponse.SoaResponse;
import {base_pakeage}.entity.PageVo;

/**
 * “{table_comment}”	业务接口类
*/
public interface {model_name}Service{

	/**
	 * 新增接口
	*/
	public SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> insert({vo_name} {vo_name_attr});

	/**
	 * 根据ID删除接口
	*/
	public SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> delete(Long id);

	/**
	 * 根据ID更新接口
	*/
	public SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> update({vo_name} {vo_name_attr});

	/**
	 * 根据ID查找接口
	*/
	public SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> findById(Long id);

	/**
	 * 分页查找接口
	*/
	public SoaResponse<ResponsePageVo<{vo_name}>, CommErrorVo> findByPage({vo_name} {vo_name_attr},int pageNo,int pageSize);
	
	/**
	 * 分页查找接口（可带分页条件）
	*/
	public SoaResponse<ResponsePageVo<{vo_name}>, CommErrorVo> findByPage({vo_name} {vo_name_attr},PageVo pageVo);

}
