package com.xander.flutter_backend.Mapper;


import com.xander.flutter_backend.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserDao {

    @Results({
            @Result(column = "userKey", property = "userKey"),
            @Result(column = "weatherUrl", property = "weatherUrl"),
            @Result(column = "todoList", property = "todoList"),
            @Result(column = "finishedList", property = "finishedList"),
            @Result(column = "express", property = "express"),
            @Result(column = "regId", property = "regId"),
            @Result(column = "exp", property = "exp"),
            @Result(column = "lastChange", property = "lastChange")
    })
    @Update("INSERT INTO myapp (userKey, weatherUrl, todoList, finishedList, " +
            "express, regId, exp, lastChange) VALUES (#{userKey}, #{weatherUrl}, " +
            "#{todoList}, #{finishedList}, #{express}, #{regId}, #{exp}, #{lastChange})")
    void insertUser(User user);

        @Results({
                @Result(column = "userKey", property = "userKey"),
                @Result(column = "weatherUrl", property = "weatherUrl"),
                @Result(column = "todoList", property = "todoList"),
                @Result(column = "finishedList", property = "finishedList"),
                @Result(column = "express", property = "express"),
                @Result(column = "regId", property = "regId"),
                @Result(column = "exp", property = "exp"),
                @Result(column = "lastChange", property = "lastChange")
        })
        @Select("SELECT * FROM myapp")
        List<User> getUserList();

    @Results({
            @Result(column = "userKey", property = "userKey"),
            @Result(column = "weatherUrl", property = "weatherUrl"),
            @Result(column = "todoList", property = "todoList"),
            @Result(column = "finishedList", property = "finishedList"),
            @Result(column = "express", property = "express"),
            @Result(column = "regId", property = "regId"),
            @Result(column = "exp", property = "exp"),
            @Result(column = "lastChange", property = "lastChange")
    })
    @Update("UPDATE myapp SET weatherUrl = #{weatherUrl}, todoList = #{todoList}, " +
            "finishedList = #{finishedList}, express = #{express}, regId = #{regId}, exp = #{exp}" +
            "lastChange = #{lastChange}" +
            "WHERE  userKey = #{userKey}")
    void updateUser(User user);

    @Results({
            @Result(column = "userKey", property = "userKey"),
            @Result(column = "weatherUrl", property = "weatherUrl"),
            @Result(column = "todoList", property = "todoList"),
            @Result(column = "finishedList", property = "finishedList"),
            @Result(column = "express", property = "express"),
            @Result(column = "regId", property = "regId"),
            @Result(column = "exp", property = "exp"),
            @Result(column = "lastChange", property = "lastChange")
    })
    @Select("SELECT * FROM myapp WHERE userKey = #{userKey} ")
    User getUser(String userKey);

    @Results({
            @Result(column = "userKey", property = "userKey"),
            @Result(column = "weatherUrl", property = "weatherUrl"),
            @Result(column = "lastChange", property = "lastChange")
    })
    @Update("UPDATE myapp SET weatherUrl = #{weatherUrl}, lastChange = #{lastChange} WHERE  userKey = #{userKey}")
    void updateWeatherUrl(User user);

    @Results({
            @Result(column = "userKey", property = "userKey"),
            @Result(column = "todoList", property = "todoList"),
            @Result(column = "lastChange", property = "lastChange")
    })
    @Update("UPDATE myapp SET todoList = #{todoList}, lastChange = #{lastChange} WHERE  userKey = #{userKey}")
    void updateTodoList(User user);

    @Results({
            @Result(column = "userKey", property = "userKey"),
            @Result(column = "finishedList", property = "finishedList"),
            @Result(column = "lastChange", property = "lastChange")
    })
    @Update("UPDATE myapp SET finishedList = #{finishedList}, lastChange = #{lastChange} WHERE  userKey = #{userKey}")
    void updateFinishedList(User user);

    @Results({
            @Result(column = "userKey", property = "userKey"),
            @Result(column = "express", property = "express"),
            @Result(column = "lastChange", property = "lastChange")
    })
    @Update("UPDATE myapp SET express = #{express}, lastChange = #{lastChange}" +
            "WHERE  userKey = #{userKey}")
    void updateExpress(User user);

    @Results({
            @Result(column = "userKey", property = "userKey"),
            @Result(column = "exp", property = "exp"),
            @Result(column = "lastChange", property = "lastChange")
    })
    @Update("UPDATE myapp SET exp = #{exp}, lastChange = #{lastChange} WHERE  userKey = #{userKey}")
    void updateExp(User user);

}
