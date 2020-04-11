package com.xander.flutter_backend.Service;


import com.xander.flutter_backend.Mapper.UserDao;
import com.xander.flutter_backend.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    @Scheduled(cron = "* * 7 * * ?")
    void weather(){
        List<User> userList = userDao.getUserList();
        String result;
        for(User i : userList) {
            RestTemplate restTemplate=new RestTemplate();
            result=restTemplate.exchange(i.getWeatherUrl(), HttpMethod.GET,null,String.class).getBody();
            if (result!=null) {
                String dayCodeStr = result.substring(485, 487);
                String nightCodeStr = result.substring(693, 695);
                System.out.println(dayCodeStr + "  " + nightCodeStr);
                int dayCode = Integer.parseInt(dayCodeStr);
                int nightCode = Integer.parseInt(nightCodeStr);
                if ((dayCode >= 3 && dayCode <= 10) || (nightCode >= 3 && dayCode <= 10))
                    jiGuangPushUtil.pushNotice("regId", i.getRegId(), "今天会下雨记得带伞哦");
            }
        }
    }
}
