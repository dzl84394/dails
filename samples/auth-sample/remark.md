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


招聘
转正
绩效
考勤
工资
试用期


项目管理
用户，权限，令牌

按子系统做ak，sk授权，校验
权限系统

rbac
rbac0
rbac1 角色可以分成几个等级
rbac2 多角色互斥等规则
rbac3
多租户 tenancy（集群、公司、插件） 、部门强制部门department 虚拟部门team，授权
url（页面、操作）

签报系统：
https://cloud.tencent.com/developer/article/2350454
https://blog.csdn.net/weixin_46522803/article/details/131377730?spm=1001.2014.3001.5501
https://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651518102&idx=1&sn=e97e4e66d661c880fc24e494b1fc1088&chksm=bd2598e98a5211ff30423bf631570824b0e1a54ae194408d00e27c7e1edb10e4fec8a9fb63aa&scene=27


延时任务
xxljob
configlib

cookie包括：
x-ca-key； 默认给公钥，去获取公钥的系统名，如果是
x-ca-nonce；
x-ca-signature；
x-ca-signature-headers

同一个子系统是免检
跨子系统要验证签名
和第三方需要报文加密


需求种类
老板的需求
客户的需求
企业内部的需求
自己的需求
需求种类
问题点
隐形：不便，不满，抱怨
显性需求：明显，对解决方案关注，必须品，没有替代方案

