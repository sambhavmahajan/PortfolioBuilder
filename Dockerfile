FROM alpine/java:21-jdk
COPY . .
ENTRYPOINT ["java","-jar","/PortfolioBuilder-0.0.1-SNAPSHOT.jar"]
