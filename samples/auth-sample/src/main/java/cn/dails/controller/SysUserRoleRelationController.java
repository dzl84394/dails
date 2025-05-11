package cn.dails.controller;


import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import cn.dails.base.bean.ResultCode;
import cn.dails.base.bean.BaseRequest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import cn.dails.base.bean.BaseResponse;
import cn.dails.bean.vo.SysUserRoleRelationSearch;
import cn.dails.bean.vo.SysUserRoleRelationResponseVo;
import cn.dails.dao.entity.SysUserRoleRelationEntity;
import cn.dails.service.ISysUserRoleRelationService;

@RestController
@RequestMapping({ "/sysUserRoleRelation" })
@Slf4j
public class SysUserRoleRelationController {


	@Autowired																					
	private ISysUserRoleRelationService service;
	

	
	@RequestMapping(value = { "","indexView" }, method = { RequestMethod.GET })
	public ModelAndView indexView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("sysUserRoleRelation/index");
		return mav;																				
	}																							
																								
	@RequestMapping(value = { "show/{id}" }, method = { RequestMethod.GET })						
	public ModelAndView showObj(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("sysUserRoleRelation/show");
		return mav;
	}

	@RequestMapping(value = { "showView" }, method = { RequestMethod.GET })
    public ModelAndView showViewById(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("sysUserRoleRelation/show");
        mav.addObject("id", id);
        SysUserRoleRelationEntity obj =  service.getById(id);
        mav.addObject("obj", obj);
        return mav;
    }
																								
	@RequestMapping(value = { "addView" }, method = { RequestMethod.GET })
	public ModelAndView newObj() {																
		ModelAndView mav = new ModelAndView("sysUserRoleRelation/new");
		SysUserRoleRelationEntity obj =  new SysUserRoleRelationEntity();
        mav.addObject("obj", obj);
		return mav;
	}
	@RequestMapping(value = { "editView" }, method = { RequestMethod.GET })
    public ModelAndView editView(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("sysUserRoleRelation/edit");
        mav.addObject("id", id);
        SysUserRoleRelationEntity obj =  service.getById(id);
        mav.addObject("obj", obj);
        return mav;
    }


																								



	@RequestMapping(value = { "findPage" }, method = { RequestMethod.POST })
	public BaseResponse<IPage<SysUserRoleRelationEntity>> findPage(@RequestBody BaseRequest<JSONObject> obj) {
		SysUserRoleRelationSearch vo = JSONObject.toJavaObject(obj.getData(), SysUserRoleRelationSearch.class);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		IPage<SysUserRoleRelationEntity> page = service.findPage(vo);

		response.setData(page);
		return response;
	}
	@RequestMapping(value = { "findList" }, method = { RequestMethod.GET })
	public BaseResponse<List<SysUserRoleRelationEntity>> findList(HttpServletRequest request) {
		SysUserRoleRelationSearch vo = new SysUserRoleRelationSearch();
		List<SysUserRoleRelationEntity> objs = service.findList(vo);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		response.setData(objs);
		return response;
	}
	@RequestMapping(value = { "findById" }, method = { RequestMethod.GET })
	public BaseResponse<SysUserRoleRelationEntity> findById(@RequestParam("id") Long id,HttpServletRequest request) {
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
        if (id==null){
            response.buildFaild(5001,"miss parameter： id");
            return response;
        }
        SysUserRoleRelationEntity obj =  service.getById(id);
        if (obj==null){
            response.buildFaild(5002,"not found entity byid:"+id);
            return response;
        }
		response.setData(obj);
		return response;
	}

	@RequestMapping(value = { "deleteById" }, method = { RequestMethod.POST })
	public BaseResponse deleteObj(@RequestBody BaseRequest<JSONObject> obj) {
	    SysUserRoleRelationEntity entity = JSONObject.toJavaObject(obj.getData(), SysUserRoleRelationEntity.class);
        Long id = entity.getId();

		service.removeById(id);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		return response;
	}
	/**
	@RequestMapping(value = { "save" }, method = { RequestMethod.POST })
	public BaseResponse saveObj(@RequestBody BaseRequest<JSONObject> obj)  {
	    SysUserRoleRelationEntity entity = JSONObject.toJavaObject(obj.getData(), SysUserRoleRelationEntity.class);
    	Long id = entity.getId();
        if (id == null ){
            service.save(entity);
        }else {
            service.saveOrUpdate(entity);
        }
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		return response;
	}
	*/

    @RequestMapping(value = { "save" }, method = { RequestMethod.POST })
    public void saveObj(@ModelAttribute("obj") SysUserRoleRelationEntity obj, HttpServletResponse response) throws IOException {
        Long id = obj.getId();
        if (id == null ){
            service.save(obj);
        }else {
            service.saveOrUpdate(obj);
        }

        response.sendRedirect("indexView");
    }
	
	
}
