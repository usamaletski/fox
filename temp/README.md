## Clone and properly configure framework
1. Clone the project
```console
git clone git@git.*********.git
cd CW_*****QA_AutoTests
```

2. Import project into IntelliJ Idea
3. Install Idea plugin - "Cucumber for Java"
4. Update _config.properties_
```
seleniumEnvironment=**docker**
...
siteUrl=**https://*****
siteHost=**vm*********
...
```
5. Modify _mysql.properties_
```
dbUrl=jdbc:mysql://*******:3306/d_qa
dbHost=**vm**********
dbUname=*******
dbPwd=*****
```

6. Ignore local changes in property files
```console
git update-index --assume-unchanged src/test/resources/config.properties
git update-index --assume-unchanged src/test/resources/mysql.properties
``` 

## Execute test locally
![#f03c15](https://placehold.it/15/f03c15/000000?text=+) **CAUTION: It will completely remove all existing Docker Images and Containers**
```console
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker rmi $(docker images -a -q)
```

#### Shows list of docker images which exists on the local system
```console
docker images -a
```

#### Pull required docker image
```console
docker pull noadmijn/selenium-standalone-debug
```

#### Start docker container
```console
docker run -d  --name dn-auto -p 4444:4444 -p 5900:5900 -v /dev/shm:/dev/shm noadmijn/selenium-standalone-debug
```

#### Make sure that docker is running
```console
docker ps
```

#### Trigger test execution skipping _@ignored_ tests with 
```console
mvn clean test -Dtest=TestRunner '-Dcucumber.options=--tags ~@ignored'
```

#### Connect via VNC
```console
open vnc://:secret@localhost
```

#### Stop and remove just created container
```console
docker stop dn-auto
docker rm dn-auto
```

#### Run ALL cucumber scenarios
```console
mvn clean test'
``` 

#### Run cucumber scenarios with special tag
```console
mvn clean test -Dtest=TestRunner -Dcucumber.options='--tags @control_panel'
```

#### Run cucumber scenarios which have one of mentioned tags
```console
mvn clean test -Dtest=TestRunner -Dcucumber.options='--tags @control_panel,@slug_elements_box'
```  

#### Run cucumber scenarios which have all mentioned tags at the same time
```console
mvn clean test -Dtest=TestRunner -Dcucumber.options='--tags @control_panel --tags @format_filter'
```

#### Run ALL cucumber scenarios EXCEPT those which have special tag
```console
mvn clean test -Dtest=TestRunner -Dcucumber.options='--tags ~@ignore'
```

#### Run special cucumber scenarios EXCEPT those which have special tag
```console
mvn clean test -Dtest=TestRunner -Dcucumber.options='--tags ~@ignore, --tags @control_panel,@slug_elements_box'
```