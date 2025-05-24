package cn.dails.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class IndexController {

    @GetMapping(value = {"/"})
    public String home() {
        // 返回名为 "index" 的模板或 HTML 页面
        return "home";
    }
}
