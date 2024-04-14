# Getting Started

### To Run the application via docker

* Create MySQL Image using "docker pull mysql"
* Create Mysql Container using image: 
  * docker run -p 3307:3306 --name mysqlcontainer -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=grocerydb -d mysql
* Create a network for mysql
  * docker network create networkmysql
* Connect mysqlcontainer to the network
  * docker network connect networkmysql mysqlcontainer
* Build the project using maven
  * mvn clean package -DskipTests
* Create docker image of the app
  * docker build -t groceryimage .
* Create container for the application 
  * docker run -p 8090:8080 --name grocerycontainer --net networkmysql -e MYSQL_HOST=mysqlcontainer -e MYSQL_PORT=3306 -e MYSQL_DB_NAME=grocerydb -e MYSQL_USER=root -e MYSQL_PASSWORD=root groceryimage
* Now you Can View the endpoints via Swagger-UI in http://localhost:8090/swagger-ui/index.html
