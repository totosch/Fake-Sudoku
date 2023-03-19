FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY . /app

RUN javac -cp ".:/app/lib/*" /app/src/main/Main.java

CMD ["java", "-cp", ".:/app/lib/*:/app/src", "main.Main"]
