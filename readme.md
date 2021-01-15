This project can be used to test the deployment to tomcat server using Maven goals

Here everything is configured to run the applications locally.
Also, the default port (8080) is used (can be different for the existing servers). The localhost is used also everywhere asuming that tomcat is run on the same host where the project is run.

### 1.The tomcat7-maven-plugin using to deploy to existing running external tomcat
 ## 1.1 tomcat configuration
  to make it work you should modify the tomcat instance.
   Add the following elements to the 
   <TOMCAT_HOME>/conf/tomcat-users.xml:
   (you mau use your own values for username and password of course)
```
<role rolename="manager-gui" />
<role rolename="manager-script" />
<user username="tomcat" password="tomcat" roles="manager-gui,manager-script" />
```
and restart tomcat.

## 1.2  maven settings configuration
You should inform Maven about the username and password to use for the specified server.
Create or edit the
<HOME>/.m2/settings.xml file providing the 'server' element there.

```
<settings xmlns="https://maven.apache.org/SETTINGS/1.0.0"
xmlns:xsi="https://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="https://maven.apache.org/SETTINGS/1.0.0
https://maven.apache.org/xsd/settings-1.0.0.xsd">
<servers>
<server>
<id>TomcatServer</id>
<username>tomcat</username>
<password>tomcat</password>
</server>
</servers>
</settings>
```
here username and password should be the same as you have specified in tomcat-users.xml. The server id should be the same as one used in the tomcat7-maven-plugin configuration in the pom.xml  (see this plugin's configuration#server element value in the project pom.xml file)

##1.3  running
use ```mvn tomcat7:deploy``` to deploy the application to tomcat
(see 'tomcat7-maven-plugin deploy [tomcat7:deploy]' run configuration) 
and
```mvn tomcat7:undeploy``` for undeploying 
('tomcat7-maven-plugin undeploy [tomcat7:undeploy]' run configuration)
You may check the application deployment results at http://localhost:8080/tomcat7_plugin_test/

### 2. Use the embedded tomcat9 server for running 
To run the application on the embedded server the cargo-maven2-plugin can be used. See the pom.xml for configuration.
Use 
```mvn package cargo:run``` 
(see the 'run on embedded tomcat9 [cargo:run]' run configuration) 
to test and check the results by url http://localhost:8080/

### 3. Using maven-antrun-plugin 
You can also deploy your application to the external server using the 
maven-antrun-plugin that can just perform the specified ant task ('copy' in our case) on the specified build phase.
## 3.1 Set the target folder to copy
in this project i use the 'deployFolder' property for specifying the directory where the files should be copied. We can use the 'webapps' folder of Tomcat installation here to make our application deployed to this server
```
<properties>
    ...
    <deployFolder>C:/appservers/apache-tomcat-9.0.0.M11/webapps/</deployFolder>
  </properties>

```
## 3.2 Plugin configuration
see the maven-antrun-plugin plugin configuration in the pom.xml.
Note the used phase ('integration-test' in this case) and the 'copy' ant target configuration

## 3.3 running

use ```mvn clean integration-test package``` to run
(see the 'deployment by antrun plugin [integration-test]' run configuration)
