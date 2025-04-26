package cn.dails.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dzl
 */
@Slf4j
@Controller
public class LoginController {

    @GetMapping(value = {"/home","/"})
    public String home() {
        log.info("home");
        // 返回名为 "index" 的模板或 HTML 页面
        return "home";
    }

    @GetMapping("/mylogin")
    public String mylogin() {
        log.info("login/login");
        // 返回名为 "index" 的模板或 HTML 页面
        return "login/login";
    }


}

