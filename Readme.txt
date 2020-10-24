

create jar for bookmanagement and bookstore using below command

mvn clean install package




Create docker images of bookmanagement and bookstore using below command

docker build -t bookmanagement .

docker build -t bookstore .





Create container using below commands

docker run -p 3306:3306 -p 33060:33060 --name mysqlserver -e MYSQL_DATABASE=book -e MYSQL_ROOT_PASSWORD=123root -d mysql

docker run -p 7001:8080 --name bookmanagement -e mysqlHost=mysqlserver -e mysqlUser=root -e mysqlPass=123root -e mysqlDatabase=book --link mysqlserver bookmanagement

docker run -p 7002:8080 --name bookstore -e bookmanagementhost=bookmanagement:8080 --link bookmanagement bookstore
 
 