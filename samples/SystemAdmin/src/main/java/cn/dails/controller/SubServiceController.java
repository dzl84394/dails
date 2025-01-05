package cn.dails.controller;


import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dails.base.bean.ResultCode;
import cn.dails.base.bean.BaseRequest;
import cn.dails.dao.entity.SubApiEntity;
import cn.dails.dao.entity.SubProjectEntity;
import cn.dails.service.ISubApiService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import cn.dails.base.bean.BaseResponse;
import cn.dails.bean.vo.SubServiceRequestVo;
import cn.dails.bean.vo.SubServiceResponseVo;
import cn.dails.dao.entity.SubServiceEntity;
import cn.dails.service.ISubServiceService;

@RestController
@RequestMapping({ "/subService" })
@Slf4j
public class SubServiceController {


	@Autowired																					
	private ISubServiceService service;

	@Autowired
	private ISubApiService apiService;
	
	@RequestMapping(value = { "","indexView" }, method = { RequestMethod.GET })
	public ModelAndView indexView(HttpServletRequest request,
								  @RequestParam(value = "clsType", required = false) String clsType) {
		ModelAndView mav = new ModelAndView("subService/index");
		mav.addObject("clsType", clsType);
		return mav;																				
	}																							
																								
	@RequestMapping(value = { "show/{id}" }, method = { RequestMethod.GET })						
	public ModelAndView showObj(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("subService/show");
		return mav;
	}

	@RequestMapping(value = { "showView" }, method = { RequestMethod.GET })
    public ModelAndView showViewById(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("subService/show");
        mav.addObject("id", id);
		SubServiceEntity obj =  service.getById(id);
		mav.addObject("obj", obj);
        return mav;
    }
																								
	@RequestMapping(value = { "addView" }, method = { RequestMethod.GET })
	public ModelAndView newObj( @RequestParam(value = "clsType", required = false) String clsType) {
		ModelAndView mav = new ModelAndView("subService/new");
		SubServiceEntity obj =  new SubServiceEntity();
		obj.setClsType(clsType);
		mav.addObject("obj", obj);
		return mav;
	}
	@RequestMapping(value = { "editView" }, method = { RequestMethod.GET })
    public ModelAndView editView(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("subService/edit");
        mav.addObject("id", id);
		SubServiceEntity obj =  service.getById(id);
		mav.addObject("obj", obj);
        return mav;
    }


																								



	@RequestMapping(value = { "findPage" }, method = { RequestMethod.POST })
	public BaseResponse<IPage<SubServiceResponseVo>> findPage(@RequestBody BaseRequest<JSONObject> obj) {
		log.info("入参：{}",JSONObject.toJSONString(obj));
		SubServiceRequestVo vo = JSONObject.toJavaObject(obj.getData(), SubServiceRequestVo.class);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		IPage<SubServiceEntity> page = service.findPage(vo);

		response.setData(page);
		return response;
	}
	@RequestMapping(value = { "findList" })
	public BaseResponse<List<SubServiceResponseVo>> findList(HttpServletRequest request,@RequestBody BaseRequest<JSONObject> obj) {

		SubServiceRequestVo vo = JSONObject.toJavaObject(obj.getData(), SubServiceRequestVo.class);
		log.info(JSONObject.toJSONString(vo));
		List<SubServiceEntity> objs = service.findList(vo);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		response.setData(objs);
		return response;
	}
	@RequestMapping(value = { "findMqList" })
	public BaseResponse<List<SubServiceResponseVo>> findMqList(HttpServletRequest request) {
		SubServiceRequestVo vo = new SubServiceRequestVo();
		vo.setClsType("mq");
		List<SubServiceEntity> objs = service.findList(vo);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		response.setData(objs);
		return response;
	}

	@RequestMapping(value = { "findById" }, method = { RequestMethod.GET })
	public BaseResponse<SubServiceResponseVo> findById(@RequestParam("id") Long id,HttpServletRequest request) {
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
        if (id==null){
            response.buildFaild(5001,"miss parameter： id");
            return response;
        }
		SubServiceEntity obj =  service.getById(id);
        if (obj==null){
            response.buildFaild(5002,"not found entity byid:"+id);
            return response;
        }
		response.setData(obj);
		return response;
	}

	@RequestMapping(value = { "deleteById" }, method = { RequestMethod.POST })
	public BaseResponse deleteObj(@RequestBody BaseRequest<JSONObject> obj) {
	    SubServiceEntity entity = JSONObject.toJavaObject(obj.getData(), SubServiceEntity.class);
        Long id = entity.getId();

		service.removeById(id);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		return response;
	}
	@RequestMapping(value = { "save" }, method = { RequestMethod.POST })
	public void saveObj(@ModelAttribute("obj") SubServiceEntity obj, HttpServletResponse response) throws IOException {
	    SubServiceEntity entity = obj;
		entity.setServiceSn(entity.getServiceSn().toUpperCase());
    	Long id = entity.getId();
        if (id == null ){
			entity.setServiceSn(entity.getServiceSn().toUpperCase());
            service.save(entity);
        }else {
            service.saveOrUpdate(entity);
        }
		response.sendRedirect("indexView");
	}
	@RequestMapping(value = { "saveMappings" })
	public BaseResponse saveMappings(@RequestBody BaseRequest<JSONObject> obj){
		JSONObject object = obj.getData();
		Long id = object.getLong("id");
		SubServiceEntity entity = service.getById(id);
		apiService.saveMappings(entity);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		response.setMessage("获取mapping成功");
		return  response;
	}





}
