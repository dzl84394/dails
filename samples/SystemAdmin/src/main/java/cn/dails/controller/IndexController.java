package cn.dails.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class IndexController {

    Logger logger = LogManager.getLogger(this.getClass());



    @RequestMapping(value =  {"index", "", "home"}, method = RequestMethod.GET)
    public String indexView() {
        return "index";
    }




    }
