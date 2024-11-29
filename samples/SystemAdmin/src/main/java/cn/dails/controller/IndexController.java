package cn.dails.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class IndexController {

    Logger logger = LogManager.getLogger(this.getClass());



    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String indexView() {
        return "index";
    }




    }
