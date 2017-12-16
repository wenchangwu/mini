package com.lakala.mini.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * User: xiao_dingo
 * Date: 2017-12-15
 * Time: 下午2:21
 */
@RestController
public class DemoController {


    @RequestMapping("/demo")
    public String demo() {
        return "this is just a test!";
    }


}

