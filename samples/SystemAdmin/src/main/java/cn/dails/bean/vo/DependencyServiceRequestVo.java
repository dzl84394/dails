package cn.dails.bean.vo;

import cn.dails.base.bean.PageVo;
import lombok.Data;

@Data
public class DependencyServiceRequestVo extends PageVo {
    private String subProjectSna;
    private String subServiceSna;
}
