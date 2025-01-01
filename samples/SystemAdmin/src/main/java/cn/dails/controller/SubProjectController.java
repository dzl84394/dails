package cn.dails.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import cn.dails.bean.vo.SubProjectRequestVo;
import cn.dails.bean.vo.SubProjectResponseVo;
import cn.dails.dao.entity.SubProjectEntity;
import cn.dails.service.ISubProjectService;

@RestController
@RequestMapping({ "/subProject" })
@Slf4j
public class SubProjectController {


	@Autowired																					
	private ISubProjectService service;
	

	
	@RequestMapping(value = { "","indexView" }, method = { RequestMethod.GET })
	public ModelAndView indexView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("subProject/index");
		return mav;																				
	}																							
																								
	@RequestMapping(value = { "show/{id}" }, method = { RequestMethod.GET })						
	public ModelAndView showObj(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("subProject/show");
		return mav;
	}

	@RequestMapping(value = { "showView" }, method = { RequestMethod.GET })
    public ModelAndView showViewById(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("subProject/show");
        mav.addObject("id", id);
        SubProjectEntity obj =  service.getById(id);
        mav.addObject("obj", obj);
        return mav;
    }
																								
	@RequestMapping(value = { "addView" }, method = { RequestMethod.GET })
	public ModelAndView newObj() {																
		ModelAndView mav = new ModelAndView("subProject/new");
		SubProjectEntity obj =  new SubProjectEntity();
        mav.addObject("obj", obj);
		return mav;
	}
	@RequestMapping(value = { "editView" }, method = { RequestMethod.GET })
    public ModelAndView editView(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("subProject/edit");
        mav.addObject("id", id);
        SubProjectEntity obj =  service.getById(id);
        mav.addObject("obj", obj);
        return mav;
    }


																								



	@RequestMapping(value = { "findPage" }, method = { RequestMethod.POST })
	public BaseResponse<IPage<SubProjectResponseVo>> findPage(@RequestBody BaseRequest<JSONObject> obj) {
		SubProjectRequestVo vo = JSONObject.toJavaObject(obj.getData(), SubProjectRequestVo.class);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		IPage<SubProjectEntity> page = service.findPage(vo);

		response.setData(page);
		return response;
	}
	@RequestMapping(value = { "findList" })
	public BaseResponse<List<SubProjectEntity>> findList(HttpServletRequest request,@RequestBody BaseRequest<JSONObject> obj) {
		SubProjectRequestVo vo = JSONObject.toJavaObject(obj.getData(), SubProjectRequestVo.class);
		List<SubProjectEntity> objs = service.findList(vo);
		log.info(JSONObject.toJSONString(objs));
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		response.setData(objs);
		return response;
	}
	@RequestMapping(value = { "findById" }, method = { RequestMethod.GET })
	public BaseResponse<SubProjectResponseVo> findById(@RequestParam("id") Long id,HttpServletRequest request) {
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
        if (id==null){
            response.buildFaild(5001,"miss parameter： id");
            return response;
        }
        SubProjectEntity obj =  service.getById(id);
        if (obj==null){
            response.buildFaild(5002,"not found entity byid:"+id);
            return response;
        }
		response.setData(obj);
		return response;
	}

	@RequestMapping(value = { "deleteById" }, method = { RequestMethod.POST })
	public BaseResponse deleteObj(@RequestBody BaseRequest<JSONObject> obj) {
	    SubProjectEntity entity = JSONObject.toJavaObject(obj.getData(), SubProjectEntity.class);
        Long id = entity.getId();

		service.removeById(id);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		return response;
	}
	/**
	@RequestMapping(value = { "save" }, method = { RequestMethod.POST })
	public BaseResponse saveObj(@RequestBody BaseRequest<JSONObject> obj)  {
	    SubProjectEntity entity = JSONObject.toJavaObject(obj.getData(), SubProjectEntity.class);
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
    public void saveObj(@ModelAttribute("obj") SubProjectEntity obj, HttpServletResponse response) throws IOException {
        Long id = obj.getId();
        if (id == null ){
            service.save(obj);
        }else {
            service.saveOrUpdate(obj);
        }

        response.sendRedirect("indexView");
    }
	
	
}
