# get an official image of java 17
FROM eclipse-temurin:17-jdk-focal

# installation of dos2unix in case of an build in windows env
RUN apt-get update && apt-get install dos2unix

# set the working directory
WORKDIR /app

# copy .mvn in order (the directory containing an embedded maven version)
COPY .mvn/ .mvn

# copy pom.xml (maven config file)
COPY pom.xml ./

# copy maven wrapper script to call embedded maven version
COPY mvnw ./

# run dos2unix to fix mvnw
RUN dos2unix mvnw

# call mvnw in order to download all dependencies before run
RUN ./mvnw dependency:go-offline

# copy src of the springboot project
COPY src ./src

# call mvnw to build and run spring boot
CMD ["./mvnw", "spring-boot:run"]