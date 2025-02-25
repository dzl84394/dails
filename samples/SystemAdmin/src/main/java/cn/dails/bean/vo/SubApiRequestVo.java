package cn.dails.bean.vo;

import cn.dails.base.bean.PageVo;
import lombok.Data;

@Data
public class SubApiRequestVo extends PageVo {
    private String path;

    private String projectSn ;
    private String serviceSn ;
    private String scope ;
}
