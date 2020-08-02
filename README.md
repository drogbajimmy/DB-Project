## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Build](#build)
* [Run](#run)
* [Reference](#reference)


## General info
Consider that we are a service provider to provide customers different services. Each customer can subscribe to multiple service and each service can be subscribed to by multiple customers. Come up a database schema to model the service-customer relationship. And use java/html/tomcat/db of your choice to implement a simple web app that will display a customer's subscriptions and let the customer subscribe to a new service, modify or delete a subscribed service.
	
## Technologies
Project is created with:
* JDK version: 1.8
* Gradle version: 4.5
* Spring Boot version: 2.x
* MyBatis version: 3.x
* H2 version: 1.4
* Thymeleaf
	
## Setup
Install JDK and Gradle locally:

```
$ sudo apt-get install python-software-properties
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
$
$ wget https://services.gradle.org/distributions/gradle-4.5-bin.zip -P /tmp
$ sudo unzip -d /opt/gradle gradle-4.5-bin.zip
$ sudo vim /etc/profile.d/mygradle.sh

export GRADLE_HOME=/opt/gradle/gradle-4.5/
export PATH=${GRADLE_HOME}/bin:${PATH}

$ sudo chmod u+x /etc/profile.d/mygradle.sh
$ source /etc/profile.d/mygradle.sh
```

## Build

```
$ cd DB-Project
$ ./gradlew build
$ ./gradlew bootRun
```

## Run
Run the application via command line

```
$ ./gradlew bootRun
```
* Open your browser and visit http://localhost:8080/SubscriptionManager/index
* Note:
* Please use email of customer as username to login
* Please use the pre-defined customer email to login
*     test1@baomidou.com
*     test2@baomidou.com
*     test3@baomidou.com
*     test4@baomidou.com
*     test5@baomidou.com
* To add other customers or services, make sure insert it in both db and csv files, then restart server
* H2 Console 
* http://localhost:8080/SubscriptionManager/h2-console/
*     Driver Class - org.h2.Driver
*     JDBC URL - jdbc:h2:~/sp
*     User Name - sa
*     Password - sa

## Reference
*     DB-Project/src/main/resources/db/schema.sql
*     DB-Project/src/main/resources/db/data.sql
*     DB-Project/src/main/resources/db/customer.csv
*     DB-Project/src/main/resources/db/service.csv
