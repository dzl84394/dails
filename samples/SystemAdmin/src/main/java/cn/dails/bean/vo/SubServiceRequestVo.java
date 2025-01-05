package cn.dails.bean.vo;

import cn.dails.base.bean.PageVo;
import lombok.Data;

@Data
public class SubServiceRequestVo extends PageVo {
    private Long id;
    private String clsType;//一级分类
    private String subType;//二级分类
    private String subProjectSn;
    private String serviceSn;
    private String actuatorMappingUrl;
}
