# 忆梦博客-服务端

## 概述

服务端使用AOP技术对接口进行了良好的封装，在入参方面使用SpringBoot中的validation灵活的进行入参校验，并有着友好的参数校验错误返回；业务处理方面将接口与核心业务代码进行了分离，并利用自定义注解进行日志的处理和权限的控制；出参方面使用ResponseEntity类来规范出参格式；权限校验上使用了jwt+md5技术和双token校验解决方案，保证了敏感接口权限的安全性；日志方面使用log4j2，配置更加灵活，可以适应多种不同环境下的个性化日志需求

目前，权限校验方面基本已经完善，开发框架也较为成熟，基本可以应对日常的开发

未来的发展计划是如果可以的话，会从这个项目中分离出一套更加方面、快捷开发的框架，用来应对多种类似的应用场景

## 技术栈

语言环境：jdk 1.8

项目构建与管理：maven 3.5.4

数据库：

- MySQL 5.7
- Redis 3.0

开发框架：SpringBoot 2.3.4.RELEASE

日志框架：log4j2

## 项目结构描述

### 包结构（blog.itxj.ymb）

| 包名/类名            | 描述                                                |
| -------------------- | --------------------------------------------------- |
| YmbServerApplication | 应用程序启动类                                      |
| annotation           | 项目中用到的一些注解，如免鉴权、免验参等            |
| config               | java配置，处理一些用yml文件不方便处理的配置         |
| constant             | 全局常量，存储API接口常量与业务中需要用到的一些常量 |
| controller           | 控制器，API接口的定义处                             |
| dto                  | 负责存放业务交互相关的类和一些接口参数类            |
| exception            | 自定义业务异常类                                    |
| mapper               | 数据库访问接口与mapper映射文件所在的地方            |
| pojo                 | 数据库映射bean                                      |
| service              | 业务逻辑层，负责核心业务的处理                      |
| util                 | 工具类                                              |
| vo                   | 存放返回给前端的数据                                |

### 配置文件

| 文件名                | 描述                                                         |
| --------------------- | ------------------------------------------------------------ |
| application.yml       | 应用程序全局配置文件，负责端口的配置与环境的配置             |
| application-dev.yml   | 开发环境的配置文件，配置数据库和日志                         |
| application-front.yml | 开发环境与前端联调的配置文件                                 |
| application-pro.yml   | 生产环境的配置文件，配置内容同上                             |
| application-test.yml  | 测试环境的配置文件，配置内容同上                             |
| banner.txt            | 自定义SpringBoot启动banner，主要负责声明一些版权信息等       |
| log4j2-dev.xml        | 开发环境日志文件，不负责文件输出                             |
| log4j2-pro.xml        | 生产环境日志文件，有文件输出                                 |
| log4j2-test.xml       | 生产环境日志文件，与生产环境日志文件类似，主要为了模拟生产环境 |

## API接口HTTP状态码与描述

| status | 描述          |
| ---- | ---------------- |
| 200  | 请求成功         |
| 401 | 校验权限失败或者token过期，需要登录来获取权限 |
| 404 | 请求的URL未找到 |
| 500  | 服务器内部错误   |

## API接口业务码

| code | 枚举值    | message                                          |
| ---- | --------- | ------------------------------------------------ |
| 0    | TEST      | 方便写代码加上的，开发中不使用此code，但也不删除 |
| 200  | SUCCESS   | 业务请求成功                                     |
| 403  | FORBIDDEN | 业务需要重新校验权限                             |
| 404  | FAIL      | 业务请求失败

## 开发规范

### 控制器
- 类上必须声明一级路径，即`@RequestMapping("一级路径")`
- 控制器方法上一律使用`@PostMapping("二级路径")`
- 路径内不写斜杠，采用小驼峰命名，如`@PostMapping("getUserInfo")`
- 路径必须为二级，不得超过或者小于二级
- 不得使用路径进行传递参数，如下面的代码是不符合规范的
    ```java
    @PostMapping("getAuthList/{roleId}")
    @ApiLog
    public ResponseEntity<List<Auth>> getAuthList(@PathVariable Integer roleId) {
        return new Result<List<Auth>>().generateSuccessResponseEntity("权限列表查询成功", authService.getAuthList(roleId));
    }
    ```
- 控制器方法的参数一定是dto包下面的参数类
- 控制器方法的返回值类型一定是`ResponseEntity<实际返回类型>`
- 如果有返回值，实际返回类型应该是vo包下面的结果类，如果没有返回值，则改控制器的方法的返回值类型一律为`ResponseEntity<?>`
- 返回值使用vo包下的Result类中提供的方法封装返回结果
- 控制器方法应该避免出现处理业务逻辑的代码，所有的业务逻辑处理都应该由service来完成

## 命名规范

### 控制器
- 类路径的命名与pojo名称相同，比如：user、article
- 方法路径命名除特殊操作外：登录login、注册register等，查询以get开头，新增以add开头，修改以update开头，删除以delete开头
