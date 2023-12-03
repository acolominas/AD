FROM tomcat:9.0.1-jre8-alpine

RUN apk update && apk upgrade --no-cache

ADD ./Application_WebClient/target/Application_WebClient-1.0 /usr/local/tomcat/webapps/webapp

CMD ["catalina.sh", "run"]
