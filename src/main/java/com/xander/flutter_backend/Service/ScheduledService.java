package com.xander.flutter_backend.Service;


import com.xander.flutter_backend.Mapper.UserDao;
import com.xander.flutter_backend.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@EnableScheduling
@Component
public class ScheduledService {
    @Autowired
    JiGuangPushUtil jiGuangPushUtil;
    @Autowired
    UserDao userDao;

    @Scheduled(cron = "0 0 23,0,1,2 * * ?")
    void timeToSleep(){
        jiGuangPushUtil.pushNotice("all", "all", "睡了吗，没睡赶紧睡了");
    }

    @Scheduled(cron = "0 0 7 * * ?")
    void wakeUp(){
        jiGuangPushUtil.pushNotice("all", "all", "早上好，今天也要继续努力");
    }

    @Scheduled(cron = "0 0 21 * * ?")
    void tip(){
        List<User> userList = userDao.getUserList();
        for(User i : userList){
            String todoList = i.getTodoList();
            int count = 0;
            if (!todoList.equals("[]")){
                count++;
                while (todoList.indexOf(',')!=-1){
                    int index = todoList.indexOf(',');
                    todoList = todoList.substring(0,index)+todoList.substring(index+1);
                    count++;
                }
            }
            if (count!=0)
            jiGuangPushUtil.pushNotice("regId", i.getRegId(), String.format("今天还有%d条任务没有完成，抓紧时间哦", count));
        }
    }

}
