package cn.dails.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import cn.dails.base.bean.ResultCode;
import cn.dails.base.bean.BaseRequest;
import cn.dails.dao.entity.SubProjectEntity;
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
	

	
	@RequestMapping(value = { "","indexView" }, method = { RequestMethod.GET })
	public ModelAndView indexView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("subService/index");
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
        return mav;
    }
																								
	@RequestMapping(value = { "addView" }, method = { RequestMethod.GET })
	public ModelAndView newObj() {																
		ModelAndView mav = new ModelAndView("subService/new");
		return mav;
	}
	@RequestMapping(value = { "editView" }, method = { RequestMethod.GET })
    public ModelAndView editView(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("subService/edit");
        mav.addObject("id", id);
        return mav;
    }


																								



	@RequestMapping(value = { "findPage" }, method = { RequestMethod.POST })
	public BaseResponse<IPage<SubServiceResponseVo>> findPage(@RequestBody BaseRequest<JSONObject> obj) {
		SubServiceRequestVo vo = JSONObject.toJavaObject(obj.getData(), SubServiceRequestVo.class);
		BaseResponse response = new BaseResponse();
		response.buildSuccess();
		IPage<SubServiceEntity> page = service.findPage(vo);

		response.setData(page);
		return response;
	}
	@RequestMapping(value = { "findList" }, method = { RequestMethod.GET })
	public BaseResponse<List<SubServiceResponseVo>> findList(HttpServletRequest request) {
		SubServiceRequestVo vo = new SubServiceRequestVo();
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
	public BaseResponse saveObj(@RequestBody BaseRequest<JSONObject> obj)  {
	    SubServiceEntity entity = JSONObject.toJavaObject(obj.getData(), SubServiceEntity.class);
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

	
	
	
}
