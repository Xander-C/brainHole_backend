package com.xander.flutter_backend;

public class User {
    private String userKey;
    private String weatherUrl;
    private String todoList;
    private String finishedList;
    private String express;
    private String regId;
    private int exp;
    private String lastChange;

    public User() {
    }

    public User(String userKey, String weatherUrl, String todoList, String finishedList, String express, String regId, int exp, String lastChange) {
        this.userKey = userKey;
        this.weatherUrl = weatherUrl;
        this.todoList = todoList;
        this.finishedList = finishedList;
        this.express = express;
        this.regId = regId;
        this.exp = exp;
        this.lastChange = lastChange;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public void setWeatherUrl(String weatherUrl) {
        this.weatherUrl = weatherUrl;
    }

    public void setTodoList(String todoList) {
        this.todoList = todoList;
    }

    public void setFinishedList(String finishedList) {
        this.finishedList = finishedList;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLastChange(String lastChange) {
        this.lastChange = lastChange;
    }

    public String getUserKey() {
        return userKey;
    }

    public String getWeatherUrl() {
        return weatherUrl;
    }

    public String getTodoList() {
        return todoList;
    }

    public String getFinishedList() {
        return finishedList;
    }

    public String getExpress() {
        return express;
    }

    public String getRegId() {
        return regId;
    }

    public int getExp() {
        return exp;
    }

    public String getLastChange() {
        return lastChange;
    }
}
