package com.bjqg.web.controller;

import com.bjqg.web.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通用请求处理类
 * @author: lbj
 * @date: 2022/12/6 10:10
 */
@Controller
@RequestMapping("/base")
public class BaseController {
    public static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private ServerConfig serverConfig;

}
