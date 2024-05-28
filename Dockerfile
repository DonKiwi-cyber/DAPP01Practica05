
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

FROM tomcat:9.0.87-jdk11
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/DAPP01Practica05-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/dapp05.war
EXPOSE 8080

CMD ["catalina.sh", "run"]