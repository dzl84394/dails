package cn.dails.controller;


import cn.dails.base.bean.BaseRequest;
import cn.dails.base.bean.BaseResponse;
import cn.dails.base.bean.ResultCode;
import cn.dails.bean.vo.SubApiRequestVo;
import cn.dails.bean.vo.SubApiResponseVo;
import cn.dails.dao.entity.SubApiEntity;
import cn.dails.service.ISubApiScopeService;
import cn.dails.service.ISubApiService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping({ "/subApi" })
@Slf4j
public class SubApiController {


	@Autowired																					
	private ISubApiService service;

	@Autowired
	private ISubApiScopeService scopeService;

	
	@RequestMapping(value = { "","indexView" }, method = { RequestMethod.GET })
	public ModelAndView indexView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("subApi/index");
		return mav;																				
	}																							
																								
	@RequestMapping(value = { "show/{id}" }, method = { RequestMethod.GET })						
	public ModelAndView showObj(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("subApi/show");
		return mav;
	}

	@RequestMapping(value = { "showView" }, method = { RequestMethod.GET })
    public ModelAndView showViewById(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("subApi/show");
        mav.addObject("id", id);
        SubApiEntity obj =  service.getById(id);
        mav.addObject("obj", obj);
        return mav;
    }
																								
	@RequestMapping(value = { "addView" }, method = { RequestMethod.GET })
	public ModelAndView newObj() {																
		ModelAndView mav = new ModelAndView("subApi/new");
		SubApiEntity obj =  new SubApiEntity();
        mav.addObject("obj", obj);
		return mav;
	}
	@RequestMapping(value = { "editView" }, method = { RequestMethod.GET })
    public ModelAndView editView(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("subApi/edit");
        mav.addObject("id", id);
        SubApiEntity obj =  service.getById(id);
        mav.addObject("obj", obj);
        return mav;
    }


																								



	@RequestMapping(value = { "findPage" }, method = { RequestMethod.POST })
	public BaseResponse<IPage<SubApiResponseVo>> findPage(@RequestBody BaseRequest<JSONObject> obj) {
		SubApiRequestVo vo = JSONObject.toJavaObject(obj.getData(), SubApiRequestVo.class);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		IPage<SubApiEntity> page = service.findPage(vo);

		response.setData(page);
		return response;
	}
	@RequestMapping(value = { "findList" }, method = { RequestMethod.GET })
	public BaseResponse<List<SubApiResponseVo>> findList(HttpServletRequest request) {
		SubApiRequestVo vo = new SubApiRequestVo();
		List<SubApiEntity> objs = service.findList(vo);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		response.setData(objs);
		return response;
	}
	@RequestMapping(value = { "findById" }, method = { RequestMethod.GET })
	public BaseResponse<SubApiResponseVo> findById(@RequestParam("id") Long id,HttpServletRequest request) {
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
        if (id==null){
            response.buildFaild(5001,"miss parameter： id");
            return response;
        }
        SubApiEntity obj =  service.getById(id);
        if (obj==null){
            response.buildFaild(5002,"not found entity byid:"+id);
            return response;
        }
		response.setData(obj);
		return response;
	}

	@RequestMapping(value = { "deleteById" }, method = { RequestMethod.POST })
	public BaseResponse deleteObj(@RequestBody BaseRequest<JSONObject> obj) {
	    SubApiEntity entity = JSONObject.toJavaObject(obj.getData(), SubApiEntity.class);
        Long id = entity.getId();

		service.removeById(id);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		return response;
	}
	/**
	@RequestMapping(value = { "save" }, method = { RequestMethod.POST })
	public BaseResponse saveObj(@RequestBody BaseRequest<JSONObject> obj)  {
	    SubApiEntity entity = JSONObject.toJavaObject(obj.getData(), SubApiEntity.class);
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
    public void saveObj(@ModelAttribute("obj") SubApiEntity obj, HttpServletResponse response) throws IOException {
        Long id = obj.getId();
        if (id == null ){
            service.save(obj);
        }else {
			scopeService.saveByName(obj);
            service.saveOrUpdate(obj);
        }

        response.sendRedirect("indexView");
    }

	@RequestMapping(value = { "updateScope" }, method = { RequestMethod.POST })
	public BaseResponse updateScope(@RequestBody BaseRequest<JSONObject> obj) throws IOException {
		String scope = obj.getData().getString("scope");
		JSONArray selectedIds = obj.getData().getJSONArray("selectedIds");

		String[] array2 = (String[]) selectedIds.toArray(new String[0]);
		List<String> list2 = new ArrayList<>(Arrays.asList(array2));
		if (list2.isEmpty()){
			return new BaseResponse().buildFaild(ResultCode.FAILED5001);
		}
		String projectSn = obj.getData().getString("projectSn");
		String serviceSn = obj.getData().getString("serviceSn");
		service.updateScope(projectSn,serviceSn,list2,scope);
		scopeService.saveByName(projectSn,serviceSn,scope);
		return new BaseResponse().ok();
	}




	
}
