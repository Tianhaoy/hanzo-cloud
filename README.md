# hanzo-cloud
半藏微服务是基于SpringCloud(Hoxton.SR7) + SpringBoot(2.3.2.RELEASE) 的 SaaS型微服务后端脚手架，核心技术采用SpringBoot、Nacos、Gateway、Fegin、Ribbon、Hystrix、JWT Token、Mybatis、Seata、Sentinel、RabbitMQ、Mysql、Redis、ElasticSearch、FastDFS等主要框架和中间件。希望能努力打造一套 SaaS基础框架 - 分布式微服务架构 - 持续集成 - 自动化部署K8s - 系统监测 的解决方案。 旨在帮助想要学习微服务的javaer快速入门，实现基础能力，不涉及具体业务。具备用户管理、资源权限管理、网关统一鉴权、XSS防跨站攻击、多存储系统、分布式事务、分布式定时任务等多个模块，支持多业务系统并行开发，支持多服务并行开发，可以作为后端服务的开发脚手架。

# 期望 
最后希望能够打造一款支持单租户，多租户动态切换的Saas脚手架
## 技术选型

### 后端技术

| 技术                   | 说明                 | 
| ---------------------- | -------------------- | 
| Spring Boot            | 基础框架             |
| Spring Cloud           | 微服务框架           | 
| Spring Cloud Alibaba   | 微服务框架           |
| Spring Security Oauth2 | 认证和授权框架       |
| JWT                    | JWT登录支持          |
| MyBatis                | ORM框架              |
| MyBatisGenerator       | 数据层代码生成       |
| PageHelper             | MyBatis物理分页插件  | 
| Knife4j                | 文档生产工具         |
| Elasticsearch          | 搜索引擎             |
| RabbitMq               | 消息队列             |
| Mysql                  | 关系型数据库         |
| Druid                  | 数据库连接池         |
| Redis                  | 分布式缓存           |
| MongoDb                | NoSql数据库          |
| OSS                    | 对象存储             |
| MinIO                  | 对象存储             |
| LogStash               | 日志收集             |
| Lombok                 | 简化对象封装工具     |
| Seata                  | 全局事务管理框架     |
| Docker                 | 应用容器引擎         |
| Portainer              | 可视化Docker容器管理 |
| Jenkins                | 自动化部署工具       |
| K8s                    | 自动化部署           |

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

