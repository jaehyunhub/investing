# Java 17 이미지를 기반으로 함
FROM openjdk:17

# 작업 디렉토리 설정
WORKDIR /app

# 프로젝트 파일을 Docker 이미지로 복사
COPY . /app

# JAR 파일 실행
CMD ["java", "-jar", "app.jar"]
