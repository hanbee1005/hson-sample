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

### 3. Redis 설정하기

### 4. jwt token 사용하기