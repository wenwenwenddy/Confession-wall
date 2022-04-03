# Confession-wall

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