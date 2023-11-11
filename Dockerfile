FROM openjdk:8-jdk-alpine
EXPOSE 8089
COPY target/5SIM4-tpAchatProject-1.0.jar 5SIM4-tpAchatProjec.jar
ENTRYPOINT ["java", "-jar", "5SIM4-tpAchatProjec.jar"]
