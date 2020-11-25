# hanzo-cloud
**半藏微服务**是基于SpringCloud(Hoxton.SR5) + SpringBoot(2.3.0.RELEASE)的 SaaS型微服务后端脚手架。
核心技术采用SpringCloud、SpringBoot、Nacos、Gateway、OpenFegin、Ribbon、Hystrix(后期会采用sentienl)、JWT Token、Mybatis Plus、Mysql、Redis、ElasticSearch、RabbitMQ、kafka、RocketMQ、Seata、FastDFS等主要框架和中间件。
希望能努力打造一套 SaaS基础框架 - 分布式微服务架构 - 分布式XXL-Job - 持续集成 - 自动化部署K8s - 系统监测 的解决方案。 
具备注册中心、网关、授权中心、消息驱动、消息队列、搜索引擎、链式追踪、分布式事务、分布式定时任务等多个模块。

### 开发中
    hanzo-gateway-server 网关
    hanzo-auth-center 授权中心
    hanzo-auth-client 授权客户端jar包
    hanzo-cloud-system 系统管理模块
    hanzo-cloud-monitor 监控模块
    这五个模块开发完毕
    启动gateway center system monitor这四个模块即可 
    可以访问knif4j接口文档查看如何登录 或者入微信群

## 没时间写如何登录 过段时间会写文档

### 后端技术
| 技术                           | 说明               | 
| ------------------------------|--------------------| 
| Spring Boot                   | 基础框架            |
| Spring Cloud                  | 微服务框架           | 
| Spring Cloud Alibaba          | 微服务框架           |
| Nacos                         | 注册中心             |
| Spring Security Oauth2        | 认证授权             |
| JWT                           | JWT登录             |
| MyBatis Plus                  | ORM框架             |
| MyBatisGenerator              | 代码生成             |
| Knife4j                       | 文档生产工具          |
| Elasticsearch                 | 搜索引擎             |
| RabbitMq & Kafka & RocketMq   | 消息队列             |
| Mysql                         | 关系型数据库          |
| Druid                         | 数据库连接池          |
| Redis                         | 分布式缓存            |
| MongoDb                       | NoSql数据库          |
| OSS                           | 对象存储             |
| LogStash                      | 日志收集             |
| Lombok                        | 对象封装工具          |
| XXL-job                       | 分布式任务           |
| Seata                         | 全局事务管理框架      |
| Docker                        | 应用容器引擎          |
| Portainer                     | 可视化Docker容器管理  |
| Jenkins                       | 自动化部署工具        |
| K8s                           | 自动化部署           |
