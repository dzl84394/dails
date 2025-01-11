package cn.dails.controller;


import cn.dails.base.bean.BaseRequest;
import cn.dails.base.bean.BaseResponse;
import cn.dails.bean.vo.DeviceCenterRequestVo;
import cn.dails.bean.vo.DeviceCenterResponseVo;
import cn.dails.dao.entity.DeviceCenterEntity;
import cn.dails.service.IDeviceCenterService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping({ "/deviceCenter" })
@Slf4j
public class DeviceCenterController {


	@Autowired																					
	private IDeviceCenterService service;
	

	
	@RequestMapping(value = { "","indexView" }, method = { RequestMethod.GET })
	public ModelAndView indexView(@RequestParam(value = "deviceType", required = false) String deviceType,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("deviceCenter/index");
		if("dc".equals(deviceType)){
			mav = new ModelAndView("deviceCenter/index_dc");
		}else if("room".equals(deviceType)){
			mav = new ModelAndView("deviceCenter/index_room");
		}
		return mav;																				
	}																							
																								
	@RequestMapping(value = { "show/{id}" }, method = { RequestMethod.GET })						
	public ModelAndView showObj(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("deviceCenter/show");
		return mav;
	}

	@RequestMapping(value = { "showView" }, method = { RequestMethod.GET })
    public ModelAndView showViewById(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("deviceCenter/show");
        mav.addObject("id", id);
        DeviceCenterEntity obj =  service.getById(id);
        mav.addObject("obj", obj);
        return mav;
    }
																								
	@RequestMapping(value = { "addView" }, method = { RequestMethod.GET })
	public ModelAndView newObj() {																
		ModelAndView mav = new ModelAndView("deviceCenter/new");

		DeviceCenterEntity obj =  new DeviceCenterEntity();
		obj.setDeviceType("rack");
        mav.addObject("obj", obj);
		return mav;
	}
	@RequestMapping(value = { "addDcView" }, method = { RequestMethod.GET })
	public ModelAndView addDcView() {
		ModelAndView mav = new ModelAndView("deviceCenter/new_dc");

		DeviceCenterEntity obj =  new DeviceCenterEntity();
		obj.setDeviceType("dc");
		mav.addObject("obj", obj);
		return mav;
	}
	@RequestMapping(value = { "addRoomView" }, method = { RequestMethod.GET })
	public ModelAndView addRoomView() {
		ModelAndView mav = new ModelAndView("deviceCenter/new_room");

		DeviceCenterEntity obj =  new DeviceCenterEntity();
		obj.setDeviceType("room");
		mav.addObject("obj", obj);
		return mav;
	}


	@RequestMapping(value = { "editView" }, method = { RequestMethod.GET })
    public ModelAndView editView(@RequestParam("id") Long id) {
		DeviceCenterEntity obj =  service.getById(id);
		ModelAndView mav = new ModelAndView("deviceCenter/edit");
		if ("dc".equals(obj.getDeviceType())){
			mav = new ModelAndView("deviceCenter/edit_dc");
		} else if ("room".equals(obj.getDeviceType())) {
			mav = new ModelAndView("deviceCenter/edit_room");
		}
		mav.addObject("id", id);

        mav.addObject("obj", obj);
        return mav;
    }


																								



	@RequestMapping(value = { "findPage" }, method = { RequestMethod.POST })
	public BaseResponse<IPage<DeviceCenterResponseVo>> findPage(@RequestBody BaseRequest<JSONObject> obj) {
		DeviceCenterRequestVo vo = JSONObject.toJavaObject(obj.getData(), DeviceCenterRequestVo.class);
		if (Strings.isNullOrEmpty(vo.getDeviceType())){
			 vo.setDeviceType("rack");
		}
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		IPage<DeviceCenterEntity> page = service.findPage(vo);

		response.setData(page);
		return response;
	}
	@RequestMapping(value = { "findList" }, method = { RequestMethod.POST })
	public BaseResponse<List<DeviceCenterResponseVo>> findList(@RequestBody BaseRequest<JSONObject> obj) {
		DeviceCenterRequestVo vo = JSONObject.toJavaObject(obj.getData(), DeviceCenterRequestVo.class);
		if (Strings.isNullOrEmpty(vo.getDeviceType())){
			vo.setDeviceType("rack");
		}
		List<DeviceCenterEntity> objs = service.findList(vo);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		response.setData(objs);
		return response;
	}
	@RequestMapping(value = { "findById" }, method = { RequestMethod.GET })
	public BaseResponse<DeviceCenterResponseVo> findById(@RequestParam("id") Long id,HttpServletRequest request) {
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
        if (id==null){
            response.buildFaild(5001,"miss parameter： id");
            return response;
        }
        DeviceCenterEntity obj =  service.getById(id);
        if (obj==null){
            response.buildFaild(5002,"not found entity byid:"+id);
            return response;
        }
		response.setData(obj);
		return response;
	}

	@RequestMapping(value = { "deleteById" }, method = { RequestMethod.POST })
	public BaseResponse deleteObj(@RequestBody BaseRequest<JSONObject> obj) {
	    DeviceCenterEntity entity = JSONObject.toJavaObject(obj.getData(), DeviceCenterEntity.class);
        Long id = entity.getId();

		service.removeById(id);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		return response;
	}
	/**
	@RequestMapping(value = { "save" }, method = { RequestMethod.POST })
	public BaseResponse saveObj(@RequestBody BaseRequest<JSONObject> obj)  {
	    DeviceCenterEntity entity = JSONObject.toJavaObject(obj.getData(), DeviceCenterEntity.class);
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
    public void saveObj(@ModelAttribute("obj") DeviceCenterEntity obj, HttpServletResponse response) throws IOException {
        Long id = obj.getId();
        if (id == null ){
            service.save(obj);
        }else {
            service.saveOrUpdate(obj);
        }

        response.sendRedirect("indexView");
    }
	
	
}
