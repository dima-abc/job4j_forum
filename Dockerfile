FROM tomcat:9.0-jre17
WORKDIR job4j_forum
COPY target/job4j_forum-1.war /usr/local/tomcat/webapps/job4j_forum-1.war