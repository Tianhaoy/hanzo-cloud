# hanzo-cloud
**半藏微服务**是基于Spring Cloud Alibaba(Hoxton.SR5) + SpringBoot(2.3.0.RELEASE)的 SaaS型微服务后端脚手架。
希望能努力打造一套 SaaS基础框架 - 分布式微服务架构 - 分布式XXL-Job - 持续集成 - 自动化部署K8s - 系统监测 的解决方案。 

### 功能特点
- 主体框架：采用SpringCloud、SpringBoot、Nacos、Gateway、OpenFegin、Ribbon、Hystrix(后期会采用sentienl)、JWT Token、Mybatis Plus、Mysql、Redis、ElasticSearch、RabbitMQ、kafka、RocketMQ、Seata、FastDFS等主要框架和中间件

- 统一注册：采用Nacos作为注册中心

- 统一认证：统一Oauth2认证协议，采用jwt的方式，实现统一认证

- 业务监控：利用Spring Boot Admin 来监控各个独立Service的运行状态

- 内部调用：集成了openfeign

- 消息驱动：stream事件驱动

- 业务熔断：采用Sentinel实现业务熔断处理，避免服务之间出现雪崩(目前使用的hystrix)

- 在线文档：通过接入knife4j，实现在线API文档的查看与调试;

- 代码生成：基于Mybatis-plus-generator自动生成代码，提升开发效率，选择数据源，选择表结构，一键生成后端代码。

- 消息中心：集成消息中间件kafka、RabbitMQ、RocketMQ，对业务进行异步处理;

- 业务分离：采用前后端分离的框架设计，前端打算采用vue-element-admin（我不会前端，希望能有前端大佬一起合作开发，~~~~(>_<)~~~~）

- 链式追踪：自定义traceId的方式，实现简单的链路追踪功能

- 对象存储：OSS

- 分布式定时任务：采用xxl-job中间件进行任务调度

- 搜索引擎：采用elasticsearch搜索引擎实现搜索功能。

- 自动化运维：docker,Jenkins,K8s


### 开发中
    hanzo-gateway-server 网关
    hanzo-auth-center 认证中心
    hanzo-auth-client 授权客户端jar包
    hanzo-cloud-system 系统管理模块
    hanzo-cloud-monitor 监控模块
    这五个模块开发完毕
    启动gateway center system monitor这四个模块即可 
   
## 导入项目、登录访问接口流程以及开发文档地址 (请仔细阅读文档启动，文档最后有微信联系方式，启动失败可以加微信入群讨论)
   [https://www.kancloud.cn/hanzo/hanzo](https://www.kancloud.cn/hanzo/hanzo)

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

### 文件结构
```lua
hanzo-cloud -- 父项目,各模块分离，方便集成和微服务
│  ├─hanzo-cloud-admin -- 后台管理父模块
│  │  ├─hanzo-cloud-system -- 系统管理子模块[6401]
│  │─hanzo-cloud-auth -- 统一认证父模块 
│  │  ├─hanzo-auth-center -- 认证中心子模块[6301]
│  │  ├─hanzo-auth-client -- 授权客户端client公共jar包模块
│  │─hanzo-cloud-common -- 公共jar包模块
│  │─hanzo-cloud-demo -- 测试父模块
│  │  ├─hanzo-demo-test -- 测试子模块[6001]
│  │─hanzo-cloud-file -- 分布式文件父模块
│  │─hanzo-cloud-gateway -- 网关父模块
│  │  ├─hanzo-gateway-server -- gateway网关服务模块[6201]
│  │  ├─hanzo-zuul-server -- zuul网关服务模块 作废
│  │─hanzo-cloud-job -- xxl-job任务父模块
│  │─hanzo-cloud-log -- log日志父模块
│  │─hanzo-cloud-monitor -- 系统监控模块[6101]
│  │─hanzo-cloud-msg -- 短信模块
│  │─hanzo-cloud-queue -- 消息队列父模块
│  │  ├─hanzo-cloud-kafka -- kafka消息队列子模块
│  │  ├─hanzo-cloud-rabbitmq -- rabbitmq消息队列子模块
│  │  ├─hanzo-cloud-rocketmq -- rocketmq消息队列子模块
│  │─hanzo-cloud-search -- 搜索父模块
│  │  ├─hanzo-cloud-elasticsearch -- es搜索引擎子模块
│  │─hanzo-cloud-starter -- starter公共父模块
│  │  ├─hanzo-starter-kafka -- kafka-starter子模块
```

