FROM java:8
VOLUME /tmp
RUN mkdir /home/NotificationService
RUN mkdir /home/NotificationService/logs
RUN chmod -R 777 /home/NotificationService
ADD NotificationService-1.0.jar /home/NotificationService/app.jar
RUN bash -c 'touch /home/NotificationService/app.jar'
RUN chmod 777 /home/NotificationService/app.jar
USER 1001
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/NotificationService/app.jar"]
EXPOSE 8080