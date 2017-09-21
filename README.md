# Fog Node
Fog Computing Simulation - Fog Part
implemented with Spring Web Server

### IDE
IntelliJ IDEA Community 2017

### Preparation
1. create a folder called fog-space under home folder

2. copy kmeans_example.py script to newly created folder


### Start
mvn spring-boot:run


### Configurations
some configurations require attention as follows

#### config.properties
    queue.max_capacity : set the maximum concurrent tasks number 

#### application.properties
    server.port=8090
    