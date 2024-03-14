FROM openjdk:11
COPY target/mail*.jar /usr/src/mail.jar
COPY src/main/resources/application.properties /opt/conf/application.properties
CMD ["java", "-jar", "/usr/src/mail.jar", "--spring.config.location=file:/opt/conf/application.properties"]
