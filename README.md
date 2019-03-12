#seci-log
## 安装 spring side 
```bash
mvn install:install-file -DgroupId=org.springside -DartifactId=springside-core -Dversion=4.2.2.GA -Dfile=./springside-core-4.2.2.GA.jar -Dpackaging=jar -DgeneratePom=true

```
## 导入数据库
secilog.sql

## 登录系统
用户名：admin，密码：123456

## 注意
登录后获取数据失败，可能是没有数据获取

此配置项不知为何：
rmiIpPort=rmi://127.0.0.1:7017  

已经删除test、profiles