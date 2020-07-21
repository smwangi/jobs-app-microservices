FROM adoptopenjdk/openjdk11:alpine-jre
LABEL maintainer="swanjohi.mwangi@gmail.com"

VOLUME /tmp
# Copy maven executable to the image
#COPY mvnw .
#COPY .mvn .mvn

# Copy the pom.xml file
#COPY pom.xml .
#EXPOSE 3010
RUN apk --no-cache add curl

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /admin-service-1.jar
#ENTRYPOINT ["sh", "-c"]
CMD java -jar /admin-service-1.jar