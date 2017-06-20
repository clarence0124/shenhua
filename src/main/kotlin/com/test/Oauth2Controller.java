package com.test;

import com.itspub.framework.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/oauth2")
public class Oauth2Controller extends BaseController {

    @RequestMapping(value = "")
    public String oauth2() {
        return "oauth2";
    }
}
