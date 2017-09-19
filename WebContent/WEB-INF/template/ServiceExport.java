package {export_package};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.mysteel.common.reponse.SoaResponse;
import {base_pakeage}.entity.PageVo;
import {base_pakeage}.entity.{vo_name};
import {base_pakeage}.entity.response.CommErrorVo;
import {base_pakeage}.entity.response.ResponsePageVo;
import {base_pakeage}.entity.response.ResponseVo;
import {base_pakeage}.exception.SystemErrorCode;
import {base_pakeage}.exception.SystemException;
import {base_pakeage}.model.{model_name};
import {base_pakeage}.service.{model_name}Service;
import {base_pakeage}.service.impl.{model_name}ServiceImpl;
import {base_pakeage}.utils.LogCvt;
import {base_pakeage}.utils.ObjectCopyUtils;


/**
 * {table_comment} 业务处理类
 */
@Service("{model_name_attr}ServiceExport")
public class {model_name}ServiceExport extends BaseServiceExport<{vo_name}> implements {model_name}Service {

	@Autowired
	private {model_name}ServiceImpl {model_name_attr}Service;	/* 自动注入Service对象 */

	/**
	 * 新增操作
	 */
	@Override
	public SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> insert({vo_name} {vo_name_attr}) {
		SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> soaResponse = super.createSoaResponseVo();

		try {
			{model_name} {model_name_attr} = ({model_name}) ObjectCopyUtils.copyProperties({vo_name_attr}, {model_name}.class);

			int n = {model_name_attr}Service.insert({model_name_attr});
			if({model_name_attr}.getId() != null){
				{vo_name_attr}.setId({model_name_attr}.getId());
			}
			
			ResponseVo<{vo_name}> response = null;
			if(n == 1){
				response = super.createResponseVo({vo_name_attr}, true, "insert {model_name} success");
            }else {
            	response = super.createResponseVo({vo_name_attr}, false, "insert {model_name} fail");
            }

			soaResponse.setResponseVo(response);
			
			super.setSoaResponse(soaResponse, SystemErrorCode.SUCCESS, true);
		} catch (SystemException e) {
			super.setSoaResponse(soaResponse, e.getErrorCode(),e.getErrorMsg(), false);
			LogCvt.error("insert {model_name} Exception",e);
		} catch (Exception e) {
			super.setSoaResponse(soaResponse, SystemErrorCode.SYSTEM_EXCETION, false);
			LogCvt.error("insert {model_name} Exception",e);
		}
		return soaResponse;
	}

	/**
	 * 根据ID删除操作
	 */
	@Override
	public SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> delete(Long id) {
		SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> soaResponse = super.createSoaResponseVo();
		
		try {

			int n = {model_name_attr}Service.delete(id);
			
			ResponseVo<{vo_name}> response = null;
			if(n == 1){
				response = super.createResponseVo(null, true, "delete {model_name} success");
            }else {
            	response = super.createResponseVo(null, false, "delete {model_name} fail");
            }

			soaResponse.setResponseVo(response);
			
			super.setSoaResponse(soaResponse, SystemErrorCode.SUCCESS, true);
		} catch (SystemException e) {
			super.setSoaResponse(soaResponse, e.getErrorCode(),e.getErrorMsg(), false);
			LogCvt.error("delete {model_name} Exception",e);
		} catch (Exception e) {
			super.setSoaResponse(soaResponse, SystemErrorCode.SYSTEM_EXCETION, false);
			LogCvt.error("delete {model_name} Exception",e);
		}
		return soaResponse;
	}

	/**
	 * 根据ID更新操作
	 */
	@Override
	public SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> update({vo_name} {vo_name_attr}) {
		SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> soaResponse = super.createSoaResponseVo();
		
		try {
			{model_name} {model_name_attr} = ({model_name}) ObjectCopyUtils.copyProperties({vo_name_attr}, {model_name}.class);

			int n = {model_name_attr}Service.update({model_name_attr});
			
			ResponseVo<{vo_name}> response = null;
			if(n == 1){
				response = super.createResponseVo({vo_name_attr}, true, "update {model_name} success");
            }else {
            	response = super.createResponseVo({vo_name_attr}, false, "update {model_name} fail");
            }

			soaResponse.setResponseVo(response);
			
			super.setSoaResponse(soaResponse, SystemErrorCode.SUCCESS, true);
		} catch (SystemException e) {
			super.setSoaResponse(soaResponse, e.getErrorCode(),e.getErrorMsg(), false);
			LogCvt.error("update {model_name} Exception",e);
		} catch (Exception e) {
			super.setSoaResponse(soaResponse, SystemErrorCode.SYSTEM_EXCETION, false);
			LogCvt.error("update {model_name} Exception",e);
		}
		return soaResponse;
	}

	/**
	 * 根据ID查询
	 */
	@Override
	public SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> findById(Long id) {
		SoaResponse<ResponseVo<{vo_name}>, CommErrorVo> soaResponse = super.createSoaResponseVo();
		try {
			{model_name} {model_name_attr} = {model_name_attr}Service.findById(id);
			
			ResponseVo<{vo_name}> response = null;
			if({model_name_attr} != null){
				{vo_name} {vo_name_attr} = ({vo_name}) ObjectCopyUtils.copyProperties({model_name_attr}, {vo_name}.class);
				response = super.createResponseVo({vo_name_attr}, true, "findById {vo_name} success");
			}else{
				response = super.createResponseVo(null, false, "findById {vo_name} is null");
			}
			
			soaResponse.setResponseVo(response);
			
			super.setSoaResponse(soaResponse, SystemErrorCode.SUCCESS, true);
		} catch (SystemException e) {
			super.setSoaResponse(soaResponse, e.getErrorCode(),e.getErrorMsg(), false);
			LogCvt.error("findById {model_name} Exception",e);
		} catch (Exception e) {
			super.setSoaResponse(soaResponse, SystemErrorCode.SYSTEM_EXCETION, false);
			LogCvt.error("findById {model_name} Exception",e);
		}
		return soaResponse;
	}
	
	/**
	 * 根据条件分页查询
	 */
	@Override
	public SoaResponse<ResponsePageVo<{vo_name}>, CommErrorVo> findByPage({vo_name} {vo_name_attr}, int pageNo,int pageSize) {
		SoaResponse<ResponsePageVo<{vo_name}>, CommErrorVo> soaResponse = super.createSoaResponsePageVo();
		try {
			{model_name} {model_name_attr} = ({model_name}) ObjectCopyUtils.copyProperties({vo_name_attr}, {model_name}.class);

			PageInfo<{model_name}> pages = {model_name_attr}Service.findByPage({model_name_attr}, pageNo, pageSize,null);
			
			ResponsePageVo<{vo_name}> response = super.createResponsePageVo(super.copyPageInfo(pages, {vo_name}.class));
			
			soaResponse.setResponseVo(response);
			
			super.setSoaResponse(soaResponse, SystemErrorCode.SUCCESS, true);
		} catch (SystemException e) {
			super.setSoaResponse(soaResponse, e.getErrorCode(),e.getErrorMsg(), false);
			LogCvt.error("findByPage {model_name} Exception",e);
		} catch (Exception e) {
			super.setSoaResponse(soaResponse, SystemErrorCode.SYSTEM_EXCETION, false);
			LogCvt.error("findByPage {model_name} Exception",e);
		}
		return soaResponse;
	}

	/**
	 * 根据条件分页查询（可带分页条件）
	 */
	@Override
	public SoaResponse<ResponsePageVo<{vo_name}>, CommErrorVo> findByPage({vo_name} {vo_name_attr}, PageVo pageVo) {
		SoaResponse<ResponsePageVo<{vo_name}>, CommErrorVo> soaResponse = super.createSoaResponsePageVo();
		try {
			{model_name} {model_name_attr} = ({model_name}) ObjectCopyUtils.copyProperties({vo_name_attr}, {model_name}.class);

			PageInfo<{model_name}> pages = {model_name_attr}Service.findByPage({model_name_attr}, pageVo.getPageNo(), pageVo.getPageSize(),pageVo.getOrderBy());
			
			ResponsePageVo<{vo_name}> response = super.createResponsePageVo(super.copyPageInfo(pages, {vo_name}.class));
			
			soaResponse.setResponseVo(response);
			
			super.setSoaResponse(soaResponse, SystemErrorCode.SUCCESS, true);
		} catch (SystemException e) {
			super.setSoaResponse(soaResponse, e.getErrorCode(),e.getErrorMsg(), false);
			LogCvt.error("findByPage {model_name} Exception",e);
		} catch (Exception e) {
			super.setSoaResponse(soaResponse, SystemErrorCode.SYSTEM_EXCETION, false);
			LogCvt.error("findByPage {model_name} Exception",e);
		}
		return soaResponse;
	}
}
