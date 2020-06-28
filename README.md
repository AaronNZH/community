## 振华的社区

## 注意！若报错未能找到table，请运行README末尾脚本

## 部署
### 依赖
- Git
- JDK
- Maven
- Mysql
## 步骤
- apt update
- apt install git
- apt install maven
- mkdir App
- cd App
- git clone https://github.com/AaronNZH/community.git
- cd community
- mvn compile package
- cp src/main/resources/application.properties src/main/resources/application-production.properties
- mvn package
- java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar


## 资料
[Spring 文档](https://spring.io/guides)

[Spring Web](https://spring.io/guides/gs/serving-web-content/)

[es](https://elasticsearch.cn/explore)

[Github develop key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)

[Bootstrap](https://v3.bootcss.com/getting-started/)

[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[Spring](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)

[菜鸟教程](https://www.runoob.com/mysql/mysql-insert-query.html)

## 工具
[Git](https://git-scm.com/download)

[Visual Paradigm](https://www.visual-paradigm.com)

[Flyway](https://flywaydb.org/getstarted/firststeps/maven)

[lombok](https://www.projectlombok.org)

## 脚本
~~~bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
~~~
