### https://blog.csdn.net/runner1920/article/details/79035491
FROM java:8

VOLUME /tmp

ADD stoken-0.0.1.jar app.jar

RUN bash -c 'touch /app.jar'

#ENTRYPOINT [“java”,”-Djava.security.egd=file:/dev/./urandom”,”-jar”,”/app.jar”]
