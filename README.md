paoding-rose-demo
=================

### paoding-rose 环境构建

#### 准备工作

1. jdk 安装完成
2. gradle 安装完成
3. 网络ok
4. 构建项目结构工具[下载地址](https://github.com/townsfolk/gradle-templates)

#### 构建项目

1. 生成一枚空的web项目结构如下
   
   ```
   | |____src
   | | |____main
   | | | |____java
   | | | | |____org
   | | | | | |____demo
   | | | | | | |____controllers
   | | | | | | |____service
   | | | |____resources
   | | | |____webapp
   | | | | |____WEB-INF
   | | |____test
   | | | |____java
   | | | |____resources
   ```
   
2. 配置build.gradle文件

   ```
   apply plugin: 'jetty'
   apply plugin: 'war'     // web项目的打包格式
   apply plugin: 'idea'    // ide的支持，eclipse则填写eclipse

   war.archiveName = 'ROOT.war'
   group = 'cinao'
   version = '1.0.0'
   compileJava.options.encoding = 'UTF-8' // 设置默认字符编码为UTF-8

   repositories {
     mavenCentral()    // 使用中央maven库, 在公司中, 通常会有自己的maven库
   }

   dependencies {        // 配置项目的jar包依赖
      compile 'commons-dbcp:commons-dbcp:1.4'         // 数据库链接池
      compile 'com.54chen:paoding-rose-scanning:1.0'  // paoding-rose
      compile 'com.54chen:paoding-rose:1.0'
      compile 'com.54chen:paoding-rose-jade:1.1'
      compile 'mysql:mysql-connector-java:5.1.30'
      
      testCompile 'org.springframework:spring-test:2.5.6.SEC02'
      testCompile 'junit:junit:4.11'                  // 单元测试
   }
   ```
3. 配置web.xml文件
   
   ```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
         id="WebApp_ID" version="2.5">
    <display-name>yangcong</display-name>

    <!-- 字符编码 -->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>


    <filter>
        <filter-name>roseFilter</filter-name>
        <filter-class>net.paoding.rose.RoseFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>roseFilter</filter-name>
        <url-pattern>/*</url-pattern>
        <dispatcher>REQUEST</dispatcher>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>INCLUDE</dispatcher>
    </filter-mapping>
</web-app>
   ```
   
4. 填写相关demo代码
   
   ```
   package org.demo.controllers;

   import net.paoding.rose.web.annotation.Path;
   import net.paoding.rose.web.annotation.rest.Get;

   /**
    * @author hezhiyu on 14/11/6.
    */
   @Path("/")
   public class DemoController {
   
       @Get("hi")
       public String sayHi() {
          return "@" + "Hello World!";
       }
   }
   ```

#### 跑起来

1. 在项目目录下执行gradle war，当现实如下状态时，即打包成功
   
   ```
   ➜  paoding-rose-base git:(master) gradle war
   :compileJava
   :processResources
   :classes
   :war
   
   BUILD SUCCESSFUL
   
   Total time: 6.394 secs
   ```

2. 进入项目目录下的`build`文件夹，在`paoding-rose-base/build/libs`下会发现一个名为`ROOT.war`的东东，这货就是我们的web应用的war包

   ```
   ➜  paoding-rose-base git:(master) ls
   LICENSE               LICENSE.txt           README.md             build                   
   build.gradle          gradle.properties     paoding-rose-base.iml src
   ```

3. 复制`ROOT.war`到web容器中（如tomcat、resin、jetty...），然后启动，我的环境为`jetty port:8080`，启动容器后状态如下

   ```
   ➜  jetty-8080  tail -f logs/2014_11_06.stderrout.log
   十一月 07, 2014 1:19:15 上午 net.paoding.rose.RoseFilter initFilterBean
   信息: [init] exits from 'init/mappingTree'
   十一月 07, 2014 1:19:15 上午 net.paoding.rose.RoseFilter initFilterBean
   信息: [init] exits from 'init'
   十一月 07, 2014 1:19:15 上午 net.paoding.rose.RoseFilter printRoseInfos
   信息: [init] rose initialized, 2 modules loaded, cost 692ms!    (version=1.0.1-20100805)
   2014-11-07 01:19:15.632:INFO:ROOT:main: [init] rose initialized, 2 modules loaded, cost 692ms! (version=1.0.1-20100805)
   2014-11-07 01:19:15.660:INFO:oejsh.ContextHandler:main: Started o.e.j.w.WebAppContext@9d84476{/,file:/private/var/folders/lb/qlsp55px2yvb4mkwx3zxg42r0000gn/T/jetty-0.0.0.0-8080-ROOT.war-_-any-1291130023571217795.dir/webapp/,AVAILABLE}{/ROOT.war}
   2014-11-07 01:19:15.705:INFO:oejs.ServerConnector:main: Started ServerConnector@4fc5dce6{HTTP/1.1}{0.0.0.0:8080}
   2014-11-07 01:19:15.706:INFO:oejs.Server:main: Started @4384ms
   ```

4. 用`浏览器`或`curl`访问一记: `http://192.168.1.10:8080/hi`

   ```
   ➜  jetty-8080  curl http://192.168.1.10:8080/hi
   Hello World!%
   ```

#### 注意事项

### Paoding 功能Demo

#### Interceptor

1. 参考文档
    * [paoding google code wiki](https://code.google.com/p/paoding-rose/wiki/Rose_Code_Fragment_Interceptor#作用范围与实际拦截的区别)

paoding-rose中的Interceptor，我倾向于使用`全局`与`特定`作为区分，并在Demo中，Interceptor位于`org.demo.interceptor`package下
对于Interceptor解析如下问题

1. Interceptor的配置
2. 多个Interceptor的执行顺序（待续）

###### Interceptor的配置
1. xx.java extends `ControllerInterceptorAdapter`
2. 重写相关方法
    * 对于全局Interceptor重写 `before(Invocation inv, Object instruction):Object` 与 `after(Invocation inv, Object instruction):Object`
	* 对于特定Interceptor重写 `getRequiredAnnotationClass():`、`before(Invocation inv, Object instruction):Object`、`after(Invocation inv, Object instruction):Object`
3. 配置applicationContext

```
    <!-- 配置Interceptor -->
    <bean class="org.demo.interceptor.GlobalInterceptor"/>
    <bean class="org.demo.interceptor.LoginInterceptor"/>
```