package cn.dails.controller;


import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import cn.dails.base.bean.ResultCode;
import cn.dails.base.bean.BaseRequest;
import cn.dails.bean.vo.SubProjectRequestVo;
import cn.dails.dao.entity.SubProjectEntity;
import cn.dails.dao.entity.SubServiceEntity;
import cn.dails.service.ISubServiceService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import cn.dails.base.bean.BaseResponse;
import cn.dails.bean.vo.DependencyServiceRequestVo;
import cn.dails.bean.vo.DependencyServiceResponseVo;
import cn.dails.dao.entity.DependencyServiceEntity;
import cn.dails.service.IDependencyServiceService;

@RestController
@RequestMapping({ "/dependencyService" })
@Slf4j
public class DependencyServiceController {


	@Autowired																					
	private IDependencyServiceService service;

	@Autowired
	private ISubServiceService serviceService;


	@RequestMapping(value = { "","indexView" }, method = { RequestMethod.GET })
	public ModelAndView indexView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("dependencyService/index");
		return mav;																				
	}																							
																								
	@RequestMapping(value = { "show/{id}" }, method = { RequestMethod.GET })						
	public ModelAndView showObj(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("dependencyService/show");
		return mav;
	}

	@RequestMapping(value = { "showView" }, method = { RequestMethod.GET })
    public ModelAndView showViewById(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("dependencyService/show");
        mav.addObject("id", id);
        DependencyServiceEntity obj =  service.getById(id);
        mav.addObject("obj", obj);
        return mav;
    }

	@RequestMapping(value = { "addView" }, method = { RequestMethod.GET })
	public ModelAndView newObj(@RequestParam(value = "id", required = false)  Long id) {
		ModelAndView mav = new ModelAndView("dependencyService/new");
		DependencyServiceEntity obj =  new DependencyServiceEntity();
		SubServiceEntity subService = serviceService.getById(id);
		obj.setAid(subService.getId());
		obj.setSubProjectSna(subService.getSubProjectSn());
		obj.setSubServiceSna(subService.getServiceSn());
		obj.setServiceTypea(subService.getSubType());
		obj.setNamea(subService.getServiceName());
		mav.addObject("obj", obj);
		return mav;
	}
	@RequestMapping(value = { "editView" }, method = { RequestMethod.GET })
    public ModelAndView editView(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("dependencyService/edit");
        mav.addObject("id", id);
        DependencyServiceEntity obj =  service.getById(id);
        mav.addObject("obj", obj);
        return mav;
    }


																								



	@RequestMapping(value = { "findPage" }, method = { RequestMethod.POST })
	public BaseResponse<IPage<DependencyServiceResponseVo>> findPage(@RequestBody BaseRequest<JSONObject> obj) {
		DependencyServiceRequestVo vo = JSONObject.toJavaObject(obj.getData(), DependencyServiceRequestVo.class);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		IPage<DependencyServiceEntity> page = service.findPage(vo);

		response.setData(page);
		return response;
	}
	@RequestMapping(value = { "findList" })
	public BaseResponse<List<DependencyServiceResponseVo>> findList(HttpServletRequest request,@RequestBody BaseRequest<JSONObject> obj) {
		DependencyServiceRequestVo vo = JSONObject.toJavaObject(obj.getData(), DependencyServiceRequestVo.class);
		List<DependencyServiceEntity> objs = service.findList(vo);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		response.setData(objs);
		return response;
	}
	@RequestMapping(value = { "findById" }, method = { RequestMethod.GET })
	public BaseResponse<DependencyServiceResponseVo> findById(@RequestParam("id") Long id,HttpServletRequest request) {
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
        if (id==null){
            response.buildFaild(5001,"miss parameter： id");
            return response;
        }
        DependencyServiceEntity obj =  service.getById(id);
        if (obj==null){
            response.buildFaild(5002,"not found entity byid:"+id);
            return response;
        }
		response.setData(obj);
		return response;
	}

	@RequestMapping(value = { "deleteById" }, method = { RequestMethod.POST })
	public BaseResponse deleteObj(@RequestBody BaseRequest<JSONObject> obj) {
	    DependencyServiceEntity entity = JSONObject.toJavaObject(obj.getData(), DependencyServiceEntity.class);
        Long id = entity.getId();

		service.removeById(id);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		return response;
	}
	/**
	@RequestMapping(value = { "save" }, method = { RequestMethod.POST })
	public BaseResponse saveObj(@RequestBody BaseRequest<JSONObject> obj)  {
	    DependencyServiceEntity entity = JSONObject.toJavaObject(obj.getData(), DependencyServiceEntity.class);
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
    public void saveObj(@ModelAttribute("obj") DependencyServiceEntity obj, HttpServletResponse response) throws IOException {
        Long id = obj.getId();
        if (id == null ){
            service.save(obj);
        }else {
            service.saveOrUpdate(obj);
        }

        response.sendRedirect("indexView");
    }
	
	
}
