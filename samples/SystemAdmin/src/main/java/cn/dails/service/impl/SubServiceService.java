package cn.dails.service.impl;


import cn.dails.bean.vo.SubServiceRequestVo;
import cn.dails.dao.SubServiceDao;
import cn.dails.dao.entity.SubServiceEntity;
import cn.dails.service.ISubServiceService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Slf4j
public class SubServiceService extends ServiceImpl<SubServiceDao,SubServiceEntity> implements ISubServiceService {

    @Autowired
    private SubServiceDao dao;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public IPage<SubServiceEntity> findPage(SubServiceRequestVo vo) {
        IPage<SubServiceEntity> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getSize());

        LambdaQueryWrapper<SubServiceEntity> wrapper = new LambdaQueryWrapper<>();
        if (!Strings.isNullOrEmpty(vo.getClsType())){
          wrapper.eq(SubServiceEntity::getClsType,vo.getClsType());
        }
        if (!Strings.isNullOrEmpty(vo.getSubType())){
          wrapper.eq(SubServiceEntity::getSubType,vo.getSubType());
        }
        if (!Strings.isNullOrEmpty(vo.getSubProjectSn())){
          wrapper.eq(SubServiceEntity::getSubProjectSn,vo.getSubProjectSn());
        }
        wrapper.orderByDesc(SubServiceEntity::getCreateDate);
        page = dao.selectPage(page,wrapper);

        return page;
    }

    @Override
    public List<SubServiceEntity> findList(SubServiceRequestVo vo) {
        LambdaQueryWrapper<SubServiceEntity> wrapper = new LambdaQueryWrapper<>();
          if (!Strings.isNullOrEmpty(vo.getClsType())){
              wrapper.eq(SubServiceEntity::getClsType,vo.getClsType());
          }
          if (!Strings.isNullOrEmpty(vo.getSubType())){
              wrapper.eq(SubServiceEntity::getSubType,vo.getSubType());
          }
          if (!Strings.isNullOrEmpty(vo.getSubProjectSn())){
              wrapper.eq(SubServiceEntity::getSubProjectSn,vo.getSubProjectSn());
          }
          List<SubServiceEntity> list = dao.selectList(wrapper);
          return list;
      }

    @Override
    public SubServiceEntity findByServiceSn(String sn) {
        LambdaQueryWrapper<SubServiceEntity> wrapper = new LambdaQueryWrapper<>();
        if (!Strings.isNullOrEmpty(sn)){
            wrapper.eq(SubServiceEntity::getServiceSn,sn);
        }
        SubServiceEntity obj = dao.selectOne(wrapper);
        return obj;
    }

    public void checkEureka(SubServiceEntity subServiceEntity) {
        //先删除


        String url = subServiceEntity.getEurekaUrl();//"http://127.0.0.1:38081/actuator/mappings"; // 替换为你的应用地址
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        // 创建请求实体
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 发送请求并获取响应
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // 获取响应体
        String jsonResponse = responseEntity.getBody();

        // 处理 JSON 响应
        if (jsonResponse != null) {
            // 解析 JSON 响应
            JSONObject jsonObject = JSONObject.parseObject(jsonResponse);
            JSONObject applications = jsonObject.getJSONObject("applications");
            JSONArray array = applications.getJSONArray("application");

            for (Object obj : array) {
                JSONObject application = (JSONObject) obj; // 将 Object 转换为 JSONObject
                String name = application.getString("name");
                SubServiceEntity entity1 = findByServiceSn(name);
                if (entity1==null){
                    SubServiceEntity temp = new SubServiceEntity();
                    temp.setServiceSn(name);
                    save(temp);
                }else{
                    entity1.setClsType("biz");
                    saveOrUpdate(entity1);
                }

            }
        }

    }


}
