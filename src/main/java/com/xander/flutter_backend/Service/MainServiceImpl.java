package com.xander.flutter_backend.Service;

import com.xander.flutter_backend.Mapper.UserDao;
import com.xander.flutter_backend.User;
import com.xander.flutter_backend.response.BusinessException;
import com.xander.flutter_backend.response.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service("mainService")
public class MainServiceImpl implements MainService {
    @Autowired
    UserDao userDao;

    public void initUser(HttpServletRequest request) throws BusinessException {
        if (request.getParameter("userKey") == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "userKey不能为空");
        }
        User temp = userDao.getUser(request.getParameter("userKey"));
        if (temp != null) throw new BusinessException(ReturnCode.CODE_FAIL, "用户已存在，若为您的账户请直接登录");
        if (request.getParameter("weatherUrl") == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "weatherUrl不能为空");
        }
        if (request.getParameter("todoList") == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "todoList不能为空");
        }
        if (request.getParameter("finishedList") == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "finishedList不能为空");
        }
        if (request.getParameter("express") == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "express不能为空");
        }
        if (request.getParameter("regId") == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "regId不能为空");
        }
        if (request.getParameter("exp") == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "exp不能为空");
        }
        if (request.getParameter("lastChange") == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "lastChange不能为空");
        }
        User user = new User((String) request.getParameter("userKey"), (String) request.getParameter("weatherUrl"), (String) request.getParameter("todoList"), (String) request.getParameter("finishedList"), (String) request.getParameter("express"), (String) request.getParameter("regId"), Integer.parseInt(request.getParameter("exp")), request.getParameter("lastChange"));
        userDao.insertUser(user);
    }

    public void updateUser(HttpServletRequest request) throws BusinessException {
        User user = new User();
        if (request.getParameter("userKey") == null || request.getParameter("lastChange") == null)
            throw new BusinessException(ReturnCode.CODE_FAIL, "userKey与lastChange不能为空");
        user.setUserKey(request.getParameter("userKey"));
        user.setLastChange(request.getParameter("lastChange"));
        if (request.getParameter("weatherUrl") != null) {
            user.setWeatherUrl(request.getParameter("weatherUrl"));
            userDao.updateWeatherUrl(user);
        }
        if (request.getParameter("todoList") != null) {
            user.setTodoList(request.getParameter("todoList"));
            userDao.updateTodoList(user);
        }
        if (request.getParameter("finishedList") != null) {
            user.setFinishedList(request.getParameter("finishedList"));
            userDao.updateFinishedList(user);
        }
        if (request.getParameter("express") != null) {
            user.setExpress(request.getParameter("express"));
            userDao.updateExpress(user);
        }
        if (request.getParameter("exp") != null) {
            user.setExp(Integer.parseInt(request.getParameter("exp")));
            userDao.updateExp(user);
        }
    }

    public String login(HttpServletRequest request) throws BusinessException {
        if (request.getParameter("userKey") == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "userKey不能为空");
        }
        User user = userDao.getUser(request.getParameter("userKey"));
        if (user == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "用户不存在，请先注册");
        }
        return user.getLastChange();
    }

    public User getUser(String userKey) throws BusinessException {
        User user = userDao.getUser(userKey);
        if (user == null) throw new BusinessException(ReturnCode.CODE_FAIL, "找不到用户");
        user.setLastChange(user.getLastChange().substring(0,4) + user.getLastChange().substring(5,7) + user.getLastChange().substring(8,10)+user.getLastChange().substring(11,13)+user.getLastChange().substring(14,16)+user.getLastChange().substring(17,19));
        return user;
    }
}
