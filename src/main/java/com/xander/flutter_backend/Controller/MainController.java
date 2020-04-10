package com.xander.flutter_backend.Controller;


import com.xander.flutter_backend.Service.MainService;
import com.xander.flutter_backend.User;
import com.xander.flutter_backend.response.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class MainController {

    @Resource(name = "mainService")
    MainService mainService;

    @RequestMapping("/reg")
    Object reg(HttpServletRequest request) {
        try{
            mainService.initUser(request);
        }catch (BusinessException e){
            e.printStackTrace();
            return e.getErrMsg();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "1";
    }

    @RequestMapping("/update")
    String update(HttpServletRequest request){
        System.out.println("收到请求，即将更新数据");
        try {
            mainService.updateUser(request);
        }catch (BusinessException e){
            e.printStackTrace();
            return e.getErrMsg();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "1";
    }

    @RequestMapping("/login")
    Object login(HttpServletRequest request){
        String lastChange = "0";
        try {
            lastChange = mainService.login(request);
        }catch (BusinessException e){
            e.printStackTrace();
            return e.getErrMsg();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(lastChange);
        return lastChange;
    }

    @RequestMapping("/getUser")
    Object getUser(String userKey){
        if (userKey == null) return "No UserKey";
        User user;
        try {
            user = mainService.getUser(userKey);
        }catch (BusinessException e){
            e.printStackTrace();
            return e.getErrMsg();
        }
        return user;
    }
}
