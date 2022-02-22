# MusicMash


To compile and run

./mvnw spring-boot:run

To build JAR file

./mvnw clean package

To run JAR file
java -jar target/name-of-jar-file

To test application

1. Send a request through the terminal.

Example:
curl -v -X GET localhost:8080/artist/53b106e7-0cc6-42cc-ac95-ed8d30a3a98e | json_pp

2. Send a request through the browser.

localhost:8080/artist/53b106e7-0cc6-42cc-ac95-ed8d30a3a98e

Note that the response might be delayed due to a high amount of API calls. For my Mac with 2 cores it takes between 13-30 seconds for two client to query simultaneously

About the application:

The application is built with Spring Boot as it is easy to get started with and good documentation. 

Dependencies are springframework.boot which is the core dependency to use Spring Boot. org.json is used to parse JSON strings fetched from APIs.

For future work org.json has to be replaced with jackson json or something similar to create a better way to parse json. For example, the current implementation sets all key values as arrays even though it is only one element. With custom made serializers and deserializers it will be easier to scale this application for the future.

Currently there is not much of REST in the application since it only have one available API request. Later a database could be added and which could be used as a cache to avoid making slow requests every time.