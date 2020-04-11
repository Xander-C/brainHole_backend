package com.xander.flutter_backend.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Map;

@Configuration
@EnableScheduling
@Component
public class ScheduledService {
    @Autowired
    JiGuangPushUtil jiGuangPushUtil;
    @Autowired
    UserDao userDao;

    @Scheduled(cron = "0 0 23,0,1,2 * * ?")
    void timeToSleep() {
        jiGuangPushUtil.pushNotice("all", "all", "睡了吗，没睡赶紧睡了");
    }

    @Scheduled(cron = "0 0 7 * * ?")
    void wakeUp() {
        jiGuangPushUtil.pushNotice("all", "all", "早上好，今天也要继续努力");
    }

    @Scheduled(cron = "0 0 21 * * ?")
    void tip() {
        List<User> userList = userDao.getUserList();
        for (User i : userList) {
            String todoList = i.getTodoList();
            int count = 0;
            if (!todoList.equals("[]")) {
                count++;
                while (todoList.indexOf(',') != -1) {
                    int index = todoList.indexOf(',');
                    todoList = todoList.substring(0, index) + todoList.substring(index + 1);
                    count++;
                }
            }
            if (count != 0)
                jiGuangPushUtil.pushNotice("regId", i.getRegId(), String.format("今天还有%d条任务没有完成，抓紧时间哦", count));
        }
    }

    @Scheduled(cron = "* * 21 * * ?")
    void weather() {


        ObjectMapper mapper = new ObjectMapper();
        List<User> userList = userDao.getUserList();
        String result;
        for (User i : userList) {
            RestTemplate restTemplate = new RestTemplate();
            result = restTemplate.exchange(i.getWeatherUrl(), HttpMethod.GET, null, String.class).getBody();
            int dayCode, nightCode, max, min;
            if (result != null) {
                try {
                    Map map = mapper.readValue(result, Map.class);
                    System.out.println();
                    String dayString = (String) ((Map) ((Map) ((Map) map.get("data")).get("forecast_24h")).get("1")).get("day_weather_code");
                    String nightString = (String) ((Map) ((Map) ((Map) map.get("data")).get("forecast_24h")).get("1")).get("night_weather_code");
                    String maxString = (String) ((Map) ((Map) ((Map) map.get("data")).get("forecast_24h")).get("1")).get("max_degree");
                    String minString = (String) ((Map) ((Map) ((Map) map.get("data")).get("forecast_24h")).get("1")).get("min_degree");
                    dayCode = Integer.parseInt(dayString);
                    nightCode = Integer.parseInt(nightString);
                    max = Integer.parseInt(maxString);
                    min = Integer.parseInt(minString);
                    if (isRain(dayCode)&&isRain(nightCode))
                        jiGuangPushUtil.pushNotice("regId", i.getRegId(), "今天一天都有雨哦，出门记得带伞");
                    else if (isRain(dayCode))
                        jiGuangPushUtil.pushNotice("regId", i.getRegId(), "今天白天有雨哦，出门记得带伞");
                    else if (isRain(nightCode))
                        jiGuangPushUtil.pushNotice("regId", i.getRegId(), "今天白天有雨哦，出门记得带伞");
                    if (max-min>10)
                        jiGuangPushUtil.pushNotice("regId", i.getRegId(), "今天温差有点大，不要穿少了衣服哦");
                    else if (-2<((max+min)/2-26)&&((max+min)/2-26)<2)
                        jiGuangPushUtil.pushNotice("regId", i.getRegId(), "今天的温度接近人体最适温度哦");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    boolean isRain(int code){
        return code >= 3 && code <= 19;
    }
}
