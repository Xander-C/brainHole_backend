package com.xander.flutter_backend.Service;

import com.xander.flutter_backend.User;
import com.xander.flutter_backend.response.BusinessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public interface MainService {

    void initUser(HttpServletRequest request) throws BusinessException;

    void updateUser(HttpServletRequest request) throws BusinessException;

    String login(HttpServletRequest request) throws  BusinessException;

    User getUser(String userKey) throws BusinessException;
}
