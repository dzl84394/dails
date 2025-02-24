package cn.dails.bean.vo;

import cn.dails.base.bean.PageVo;
import lombok.Data;

@Data
public class SubApiScopeRequestVo extends PageVo {
    private String projectSn ;
    private String serviceSn ;
}
