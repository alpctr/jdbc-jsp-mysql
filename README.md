# jdbc-jsp-mysql
```sql
CREATE TABLE `employee` (
   `id` int(3) NOT NULL AUTO_INCREMENT,
   `first_name` varchar(20) DEFAULT NULL,
   `last_name` varchar(20) DEFAULT NULL,
   `username` varchar(250) DEFAULT NULL,
   `password` varchar(20) DEFAULT NULL,
   `address` varchar(45) DEFAULT NULL,
   `contact` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```



```yml
version: '3'
services:
    jdbc-jsp-mysql:
        image:
           alpctr/jdbc-jsp-mysql:0.0.7-SNAPSHOT
        ports:
         - 0080:8080
        environment:
            spring.datasource.url: "jdbc:mysql://mysql:3306/mysql_database?useSSL=false"

    mysql:
        image:
            mysql:latest
        ports:
        - 3306:3306
        environment:
            MYSQL_ROOT_PASSWORD: password
```

```
FROM tomcat

COPY jdbc-jsp-mysql-0.0.7-SNAPSHOT.war /usr/local/tomcat/webapps/
```
