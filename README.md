# HSON SAMPLE

### 1. local 환경 세팅하기
1. docker-compose.yml 파일 생성하기
```yaml
# Use root as user credentials
version: "3.1"

services:
  mysql:
    image: mysql:8.0.19
    restart: always
    environment:
      MYSQL_DATABASE: hsondb
      MYSQL_ROOT_PASSWORD: localmysqlpwd123
      TZ: Asia/Seoul
    ports:
      - 3307:3306
    command: ['mysqld', '--sql_mode=NO_ENGINE_SUBSTITUTION', '--character-set-server=utf8mb4', '--collation-server=utf8mb4_unicode_ci']

  redis:
    image: redis:5.0.6
    restart: always
    ports:
      - 6370:6379

networks:
  default:
    external:
      name: mysql

```
2. docker-compose.yml 실행하기
```shell
docker-compose up -d
```

### 2. MySQL DB 설정하기
1. dependency 추가 (build.gradle)
```
implementation 'mysql:mysql-connector-java'
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
```
2. DB 접속 설정 정보 지정 (+ jpa 설정 추가)
```yaml
spring:
  ...

  datasource:
    url: jdbc:mysql://HOST:PORT/DB_NAME?useSSL=false
    username: USERNAME
    password: PASSWORD
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: create
    database-platform: org.hibernate.dialect.MySQL5Dialect
    # DDL 정의시 데이터베이스의 고유 기능을 사용합니다.
    generate-ddl: true
    # API 호출시, SQL 문을 콘솔에 출력한다.
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```
3. 위 방식 또는 DataSourceConfig.java 파일로 설정

### 3. Redis 설정하기
1. dependency 추가
```
implementation 'org.springframework.boot:spring-boot-starter-data-redis'
```
2. 설정 파일 작성 (RedisConfig.java)

### 4. jwt token 사용하기

### 5. jwt token을 redis에 저장하기

### 6. swagger 연동하기

### 7. social login 기능 구현하기