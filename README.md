# 使用

**该项目使用了极光推送平台，请先注册极光[开发者服务](https://www.jiguang.cn/accounts/register),若无该服务将无法使用推送功能。**

## 数据库

### 数据表结构

```mysql
CREATE TABLE IF NOT EXISTS `myapp`(
   `userKey` VARCHAR(32) NOT NULL,
   `weatherUrl` TEXT NOT NULL,
   `todoList` TEXT NOT NULL,
   `finishedList` TEXT NOT NULL,
   `express` TEXT NOT NULL,
   `regId` TEXT NOT NULL,
   `exp` INT NOT NULL,
   `lastChange` TEXT NOT NULL,
   PRIMARY KEY ( `userKey` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```



### 配置数据库链接

在src\main\resources\application.yml中配置数据库链接

```yml
spring:
  datasource:                        #↓这个是数据库名
    url: jdbc:mysql://localhost:3306/flutter?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST&useSSL=false&allowPublicKeyRetrieval=true
    username: flutter #这里是用户名
    password: 123456 #这里填用户密码
    driver-class-name: com.mysql.cj.jdbc.Driver
server:
  port: 9094 #这里是服务器端口号
```

## 极光推送

### 服务器端

请在``JiGuangPushUtil`中配置极光的appkey与masterSecret.

### 极光推送后台

将包名改为`com.xander.flutterlearing2`

## 启动

将项目打包为`.jar`包后使用`java -jar 文件名.jar`运行。