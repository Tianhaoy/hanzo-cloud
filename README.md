# hanzo-cloud
**半藏微服务**是基于SpringCloud(Hoxton.SR5) + SpringBoot(2.3.0.RELEASE)的 SaaS型微服务后端脚手架。
核心技术采用SpringBoot、Nacos、Gateway、OpenFegin、Ribbon、Hystrix(后期会采用sentienl)、JWT Token、Mybatis Plus、Mysql、Redis、ElasticSearch、RabbitMQ、Seata、FastDFS等主要框架和中间件。
希望能努力打造一套 SaaS基础框架 - 分布式微服务架构 - 分布式XXL-Job - 持续集成 - 自动化部署K8s - 系统监测 的解决方案。 
旨在帮助想要学习微服务的java er快速入门（我也是新手），实现基础能力，不涉及具体业务。具备注册中心、网关统一鉴权、授权中心、消息队列、搜索引擎、分布式事务、分布式定时任务等多个模块。
## 技术选型 
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
| XXL-job                       | 分布式任务           |**暂时不会**
| Seata                         | 全局事务管理框架      |**暂时不会**
| Docker                        | 应用容器引擎          |**暂时不会**
| Portainer                     | 可视化Docker容器管理  |**暂时不会**
| Jenkins                       | 自动化部署工具        |**暂时不会**
| K8s                           | 自动化部署           |**暂时不会**

## 环境搭建

### 开发环境

| 工具          | 版本号 | 下载                                                         |
| ------------- | ------ | ------------------------------------------------------------ |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql         | 8.0    | https://www.mysql.com/                                       |
| Redis         | 5.0    | https://redis.io/download                                    |
| Elasticsearch | 7.5.0  | https://www.elastic.co/cn/downloads/elasticsearch            |
| Kibana        | 7.5.0  | https://www.elastic.co/cn/downloads/kibana                   |
| Logstash      | 7.5.0  | https://www.elastic.co/cn/downloads/logstash                 |
| MongoDb       | 4.2.5  | https://www.mongodb.com/download-center                      |
| RabbitMq      | 3.7.14 | http://www.rabbitmq.com/download.html                        |
| nginx         | 1.9.9  | http://nginx.org/en/download.html                            |

