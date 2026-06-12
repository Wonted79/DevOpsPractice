# Build stage
# 베이스 이미지 생성 , Alias 지정
FROM dhi.io/amazoncorretto:25-dev AS builder
# 컨테이너 내부에서 작업할 디렉토리 설정
WORKDIR /app
# 현재 디렉토리의 모든 소스코드와 설정 파일을 WORKDIR로 복사
COPY . .
# 테스트 없이 캐시를 지우고 Gradle build
RUN chmod +x gradlew && ./gradlew clean build -x test
#-----------------------------------------------------------
# Run Stage
# 사용시에 dev를 빼라는 의미는 JRE 만 포함된 경량 이미지를 사용하라는건가?
FROM dhi.io/amazoncorretto:25
# 컨테이너 안에서 작업할 폴더 설정
WORKDIR /app
# builder 스테이지에서 가져와서 해당 경로안의 jar 파일을 작업폴더에 app.jar로 복사해
COPY --from=builder /app/build/libs/*.jar app.jar
# Spring Boot 기본 포트임을 문서화(문서화 목적, 실제 바인딩은 docker run -p 에서 설정)
EXPOSE 8080
#컨테이너가 시작될때 명령어 , JVM시스템 속성 주입 "-Dspring.profiles.active=default"
ENTRYPOINT ["java","-Dspring.profiles.active=default","-jar","app.jar"]

# 빌드 명령
# docker build -t docker-ex1:latest .
# 이미지 생성 확인
# docker images docker-ex1
# 빌드 후 확인 및 실행
# docker run -p 8080:8080 docker-ex1:latest
# 마리아 db
# docker run -d -p 3307:3306 --name mariadb-test -e MARIADB_ROOT_PASSWORD=1234 mariadb
# 마리아 db 실행
# docker exec -it mariadb-test -uroot -p