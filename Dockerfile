FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY /target/categoria-web.jar /opt/app
CMD ["java", "-jar", "/opt/app/categoria-web.jar"]