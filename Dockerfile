FROM openjdk:10
COPY build/libs/HelloKotlin-1.0-SNAPSHOT-all.jar calculator.jar
CMD ["java", "jar", "calculator.jar"]