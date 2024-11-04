## 去eureka获取所有微服务
    给微服务授权秘钥
## 去获取所有微服务的path

## 网关鉴权，微服务鉴权
同一个子系统（微服务前2段一致）可以明文访问，跨子系统要分析path是否有权限
权限放在header里面，来源service ，来源trace
在访问FeignClient和http封装的时候，要带上service来源和trace 签名

## 账号
微服务
系统管理员，
业务管理员
测试
客户
