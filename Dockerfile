FROM tomcat:8
COPY target/*.war /usr/local/tomcat/webapps/
ENV bookmanagementhost=bookmanagement
EXPOSE 8080
CMD ["catalina.sh", "run"]