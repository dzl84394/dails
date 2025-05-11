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
import cn.dails.bean.vo.SysRolePermissionRelationSearch;
import cn.dails.bean.vo.SysRolePermissionRelationResponseVo;
import cn.dails.dao.entity.SysRolePermissionRelationEntity;
import cn.dails.service.ISysRolePermissionRelationService;

@RestController
@RequestMapping({ "/sysRolePermissionRelation" })
@Slf4j
public class SysRolePermissionRelationController {


	@Autowired																					
	private ISysRolePermissionRelationService service;
	

	
	@RequestMapping(value = { "","indexView" }, method = { RequestMethod.GET })
	public ModelAndView indexView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("sysRolePermissionRelation/index");
		return mav;																				
	}																							
																								
	@RequestMapping(value = { "show/{id}" }, method = { RequestMethod.GET })						
	public ModelAndView showObj(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("sysRolePermissionRelation/show");
		return mav;
	}

	@RequestMapping(value = { "showView" }, method = { RequestMethod.GET })
    public ModelAndView showViewById(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("sysRolePermissionRelation/show");
        mav.addObject("id", id);
        SysRolePermissionRelationEntity obj =  service.getById(id);
        mav.addObject("obj", obj);
        return mav;
    }
																								
	@RequestMapping(value = { "addView" }, method = { RequestMethod.GET })
	public ModelAndView newObj() {																
		ModelAndView mav = new ModelAndView("sysRolePermissionRelation/new");
		SysRolePermissionRelationEntity obj =  new SysRolePermissionRelationEntity();
        mav.addObject("obj", obj);
		return mav;
	}
	@RequestMapping(value = { "editView" }, method = { RequestMethod.GET })
    public ModelAndView editView(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("sysRolePermissionRelation/edit");
        mav.addObject("id", id);
        SysRolePermissionRelationEntity obj =  service.getById(id);
        mav.addObject("obj", obj);
        return mav;
    }


																								



	@RequestMapping(value = { "findPage" }, method = { RequestMethod.POST })
	public BaseResponse<IPage<SysRolePermissionRelationEntity>> findPage(@RequestBody BaseRequest<JSONObject> obj) {
		SysRolePermissionRelationSearch vo = JSONObject.toJavaObject(obj.getData(), SysRolePermissionRelationSearch.class);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		IPage<SysRolePermissionRelationEntity> page = service.findPage(vo);

		response.setData(page);
		return response;
	}
	@RequestMapping(value = { "findList" }, method = { RequestMethod.GET })
	public BaseResponse<List<SysRolePermissionRelationEntity>> findList(HttpServletRequest request) {
		SysRolePermissionRelationSearch vo = new SysRolePermissionRelationSearch();
		List<SysRolePermissionRelationEntity> objs = service.findList(vo);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		response.setData(objs);
		return response;
	}
	@RequestMapping(value = { "findById" }, method = { RequestMethod.GET })
	public BaseResponse<SysRolePermissionRelationEntity> findById(@RequestParam("id") Long id,HttpServletRequest request) {
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
        if (id==null){
            response.buildFaild(5001,"miss parameter： id");
            return response;
        }
        SysRolePermissionRelationEntity obj =  service.getById(id);
        if (obj==null){
            response.buildFaild(5002,"not found entity byid:"+id);
            return response;
        }
		response.setData(obj);
		return response;
	}

	@RequestMapping(value = { "deleteById" }, method = { RequestMethod.POST })
	public BaseResponse deleteObj(@RequestBody BaseRequest<JSONObject> obj) {
	    SysRolePermissionRelationEntity entity = JSONObject.toJavaObject(obj.getData(), SysRolePermissionRelationEntity.class);
        Long id = entity.getId();

		service.removeById(id);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		return response;
	}
	/**
	@RequestMapping(value = { "save" }, method = { RequestMethod.POST })
	public BaseResponse saveObj(@RequestBody BaseRequest<JSONObject> obj)  {
	    SysRolePermissionRelationEntity entity = JSONObject.toJavaObject(obj.getData(), SysRolePermissionRelationEntity.class);
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
    public void saveObj(@ModelAttribute("obj") SysRolePermissionRelationEntity obj, HttpServletResponse response) throws IOException {
        Long id = obj.getId();
        if (id == null ){
            service.save(obj);
        }else {
            service.saveOrUpdate(obj);
        }

        response.sendRedirect("indexView");
    }
	
	
}
