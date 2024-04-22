FROM openjdk:11
ADD /build/libs/*.jar grocery-store-Release_1.24.4.22-plain.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","grocery-store-Release_1.24.4.22-plain.jar"]