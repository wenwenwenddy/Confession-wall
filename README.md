# Confession-wall

# develop log

## 1st week

04/05/2022 - 04/12/2022

### client

accomplish login and register features

### server

accomplish all features except Cookie

# MySQL

```
+---------------------------+
| Tables_in_confession_wall |
+---------------------------+
| contentdata               |
| userdata                  |
+---------------------------+
```

## userdata

```
+----------+--------------+------+-----+---------+----------------+
| Field    | Type         | Null | Key | Default | Extra          |
+----------+--------------+------+-----+---------+----------------+
| uid      | int          | NO   | PRI | NULL    | auto_increment |
| username | varchar(255) | NO   |     | NULL    |                |
| password | varchar(255) | NO   |     | NULL    |                |
+----------+--------------+------+-----+---------+----------------+
```

## contentdata

```
+----------+----------------+------+-----+---------+----------------+
| Field    | Type           | Null | Key | Default | Extra          |
+----------+----------------+------+-----+---------+----------------+
| id       | int            | NO   | PRI | NULL    | auto_increment |
| uid      | int            | NO   | MUL | NULL    |                |
| username | varchar(255)   | NO   |     | NULL    |                |
| content  | varchar(10000) | NO   |     | NULL    |                |
| date     | datetime       | NO   |     | NULL    |                |
| target   | varchar(255)   | NO   |     | NULL    |                |
+----------+----------------+------+-----+---------+----------------+
```

### foreign key

```
CONSTRAINT `KF_uid` FOREIGN KEY (`uid`) REFERENCES `userdata` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
```

# Back-End

language: Java

jdk: 1.8

tomcat: 9

dependencies: 

```
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.28</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.5.3</version>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
    <scope>test</scope>
</dependency>
```