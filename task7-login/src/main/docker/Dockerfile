FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD task7-login-0.1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.hello.security.egd=file:/dev/./urandom -Dspring.profiles.active=local -jar /app.jar" ]