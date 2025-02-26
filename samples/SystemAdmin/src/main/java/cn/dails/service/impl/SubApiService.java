package cn.dails.service.impl;


import cn.dails.bean.vo.SubApiRequestVo;
import cn.dails.dao.SubApiDao;
import cn.dails.dao.entity.SubApiEntity;
import cn.dails.dao.entity.SubServiceEntity;
import cn.dails.service.ISubApiService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Slf4j
public class SubApiService extends ServiceImpl<SubApiDao, SubApiEntity> implements ISubApiService {

    @Autowired
    private SubApiDao dao;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public IPage<SubApiEntity> findPage(SubApiRequestVo vo) {
        IPage<SubApiEntity> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getSize());

        LambdaQueryWrapper<SubApiEntity> wrapper = new LambdaQueryWrapper<>();
        if (!Strings.isNullOrEmpty(vo.getPath())){
            wrapper.like(SubApiEntity::getPath,vo.getPath());
        }
        if (!Strings.isNullOrEmpty(vo.getProjectSn())){
            wrapper.eq(SubApiEntity::getProjectSn,vo.getProjectSn());
        }

        if (!Strings.isNullOrEmpty(vo.getServiceSn())){
            wrapper.eq(SubApiEntity::getServiceSn,vo.getServiceSn());
        }
        if (!Strings.isNullOrEmpty(vo.getScope())){
            wrapper.eq(SubApiEntity::getScope,vo.getScope());
        }

        wrapper.orderByAsc(SubApiEntity::getServiceSn).orderByAsc(SubApiEntity::getPath);
        page = dao.selectPage(page, wrapper);

        return page;
    }

    @Override
    public List<SubApiEntity> findList(SubApiRequestVo vo) {
        LambdaQueryWrapper<SubApiEntity> wrapper = new LambdaQueryWrapper<>();
        if (!Strings.isNullOrEmpty(vo.getPath())){
            wrapper.like(SubApiEntity::getPath,vo.getPath());
        }
        if (!Strings.isNullOrEmpty(vo.getProjectSn())){
            wrapper.eq(SubApiEntity::getProjectSn,vo.getProjectSn());
        }

        if (!Strings.isNullOrEmpty(vo.getServiceSn())){
            wrapper.eq(SubApiEntity::getServiceSn,vo.getServiceSn());
        }
        if (!Strings.isNullOrEmpty(vo.getScope())){
            wrapper.eq(SubApiEntity::getScope,vo.getScope());
        }
        List<SubApiEntity> list = dao.selectList(wrapper);
        return list;
    }

    public void checkMappings(SubServiceEntity subServiceEntity) {
        //先删除
//        QueryWrapper<SubApiEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("project_sn", subServiceEntity.getSubProjectSn())
//                .eq("service_sn", subServiceEntity.getServiceSn());
//        this.remove(queryWrapper);

        String url = subServiceEntity.getActuatorMappingUrl();//"http://127.0.0.1:38081/actuator/mappings"; // 替换为你的应用地址
        String jsonResponse = restTemplate.getForObject(url, String.class);
        JSONObject object = JSONObject.parseObject(jsonResponse);
        JSONArray jsonArray = object.getJSONObject("contexts").getJSONObject("SystemAdmin").getJSONObject("mappings")
                .getJSONObject("dispatcherServlets").getJSONArray("dispatcherServlet");

        for (Object json : jsonArray) {
            JSONObject jsonObject = (JSONObject) json;
            String handler = jsonObject.getString("handler");
            JSONObject details = jsonObject.getJSONObject("details");
            if (details == null) {
                continue;
            }
            JSONObject handlerMethod = details.getJSONObject("handlerMethod");
            String className = handlerMethod.getString("className");
            String methodName = handlerMethod.getString("name");
            JSONObject requestMappingConditions = details.getJSONObject("requestMappingConditions");
            String methods = requestMappingConditions.getString("methods");
            String patterns = requestMappingConditions.getString("patterns");

            List<String> list = JSON.parseArray(patterns, String.class);




            for (String path: list){
                QueryWrapper<SubApiEntity> queryWrapper2= new QueryWrapper<>();
                queryWrapper2.eq("project_sn", subServiceEntity.getSubProjectSn())
                        .eq("service_sn", subServiceEntity.getServiceSn())
                        .eq("handler",handler);
                SubApiEntity apiEntity = dao.selectOne(queryWrapper2);
                if (apiEntity==null){
                    apiEntity = new SubApiEntity();
                    apiEntity.setProjectSn(subServiceEntity.getSubProjectSn());
                    apiEntity.setServiceSn(subServiceEntity.getServiceSn());
                    apiEntity.setHandler(handler);

                    apiEntity.setClassName(className);
                    apiEntity.setMethodName(methodName);
                    apiEntity.setMethod(methods);
                    apiEntity.setPath(path);
                    save(apiEntity);
                }else {

                    apiEntity.setClassName(className);
                    apiEntity.setMethodName(methodName);
                    apiEntity.setMethod(methods);
                    apiEntity.setPath(path);
                    saveOrUpdate(apiEntity);
                }


            }



        }

    }

    @Override
    public void updateScope(String projectSn, String serviceSn, List<String> ids, String scope) {
//        LambdaQueryWrapper<SubApiEntity> wrapper = new LambdaQueryWrapper<>();
        UpdateWrapper<SubApiEntity> wrapper = new UpdateWrapper<>();
        if (!ids.isEmpty()){
            wrapper.in("id", ids); // 根据 ID 条件更新
        }
        // 创建要更新的实体对象
        SubApiEntity updateEntity = new SubApiEntity();
        updateEntity.setScope(scope); // 设置新的 scope 值
        // 执行更新
        this.update(updateEntity, wrapper);
    }


}
