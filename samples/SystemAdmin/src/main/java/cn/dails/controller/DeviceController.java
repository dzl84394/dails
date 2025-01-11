package cn.dails.controller;


import cn.dails.base.bean.BaseRequest;
import cn.dails.base.bean.BaseResponse;
import cn.dails.bean.vo.DeviceRequestVo;
import cn.dails.bean.vo.DeviceResponseVo;
import cn.dails.dao.entity.DeviceEntity;
import cn.dails.service.IDeviceService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping({ "/device" })
@Slf4j
public class DeviceController {


	@Autowired																					
	private IDeviceService service;
	

	
	@RequestMapping(value = { "","indexView" }, method = { RequestMethod.GET })
	public ModelAndView indexView(@RequestParam(value = "deviceType", required = false) String deviceType
			,@RequestParam(value = "currentPage", required = false) Long currentPage
			,@RequestParam(value = "size", required = false) Long size,  HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("device/index");
//		if ("physical".equals(deviceType)) {
//			mav = new ModelAndView("device/rackIndex");
//		}else if ("virtual".equals(deviceType)) {
//			mav = new ModelAndView("device/virtualIndex");
//		}else if ("pks".equals(deviceType)) {
//			mav = new ModelAndView("device/pksIndex");
//		}
//		DeviceRequestVo vo = new DeviceRequestVo();
//		vo.setDeviceType(deviceType);
//		if(size == null) size=10L;
//		if(currentPage == null) currentPage=10L;
//		vo.setSize(size);
//		vo.setCurrentPage(currentPage);
//		IPage<DeviceEntity> page = service.findPage(vo);
//		mav.addObject("page", page);
		return mav;																				
	}																							
																								
	@RequestMapping(value = { "show/{id}" }, method = { RequestMethod.GET })						
	public ModelAndView showObj(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("device/show");
		DeviceEntity obj =  service.getById(id);
		mav.addObject("obj", obj);
		return mav;
	}

	@RequestMapping(value = { "showView" }, method = { RequestMethod.GET })
    public ModelAndView showViewById(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("device/show");
        mav.addObject("id", id);
		DeviceEntity obj =  service.getById(id);
		mav.addObject("obj", obj);
        return mav;
    }
																								
	@RequestMapping(value = { "addView" }, method = { RequestMethod.GET })
	public ModelAndView newObj() {																
		ModelAndView mav = new ModelAndView("device/new");
		DeviceEntity obj =  new DeviceEntity();
		mav.addObject("obj", obj);
		return mav;
	}
	@RequestMapping(value = { "editView" }, method = { RequestMethod.GET })
    public ModelAndView editView(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("device/edit");
        mav.addObject("id", id);
		DeviceEntity obj =  service.getById(id);
		mav.addObject("obj", obj);
        return mav;
    }


																								



	@RequestMapping(value = { "findPage" }, method = { RequestMethod.POST })
	public BaseResponse<IPage<DeviceResponseVo>> findPage(@RequestBody BaseRequest<JSONObject> obj) {
		DeviceRequestVo vo = JSONObject.toJavaObject(obj.getData(), DeviceRequestVo.class);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		IPage<DeviceEntity> page = service.findPage(vo);

		response.setData(page);
		return response;
	}
	@RequestMapping(value = { "findList" }, method = { RequestMethod.GET })
	public BaseResponse<List<DeviceResponseVo>> findList(HttpServletRequest request) {
		DeviceRequestVo vo = new DeviceRequestVo();
		List<DeviceEntity> objs = service.findList(vo);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		response.setData(objs);
		return response;
	}
	@RequestMapping(value = { "findById" }, method = { RequestMethod.GET })
	public BaseResponse<DeviceResponseVo> findById(@RequestParam("id") Long id,HttpServletRequest request) {
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
        if (id==null){
            response.buildFaild(5001,"miss parameter： id");
            return response;
        }
        DeviceEntity obj =  service.getById(id);
        if (obj==null){
            response.buildFaild(5002,"not found entity byid:"+id);
            return response;
        }
		response.setData(obj);
		return response;
	}

	@RequestMapping(value = { "deleteById" }, method = { RequestMethod.POST })
	public BaseResponse deleteObj(@RequestBody BaseRequest<JSONObject> obj) {
	    DeviceEntity entity = JSONObject.toJavaObject(obj.getData(), DeviceEntity.class);
        Long id = entity.getId();

		service.removeById(id);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		return response;
	}
//	@RequestMapping(value = { "save" }, method = { RequestMethod.POST })
//	public BaseResponse saveObj(@RequestBody BaseRequest<JSONObject> obj)  {
//	    DeviceEntity entity = JSONObject.toJavaObject(obj.getData(), DeviceEntity.class);
//    	Long id = entity.getId();
//        if (id == null ){
//            service.save(entity);
//        }else {
//            service.saveOrUpdate(entity);
//        }
//		BaseResponse response = new BaseResponse();
//		response.buildSuccess();
//		return response;
//	}

	@RequestMapping(value = { "save" }, method = { RequestMethod.POST })
	public void saveObj(@ModelAttribute("obj") DeviceEntity obj, HttpServletResponse response) throws IOException {
		Long id = obj.getId();
		obj.setDeviceSn(obj.getDeviceSn().toUpperCase());
		if (id == null ){
			service.save(obj);
		}else {
			service.saveOrUpdate(obj);
		}

		response.sendRedirect("indexView");
	}




}
