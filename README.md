## 📌What project is it?

Spring cloud 로 만드는 MSA 애플리케이션으로 MSA 학습에 초점을 둔 간단한 프로젝트 입니다.

기존에 모노리틱 프로젝트만 개발 해 왔기에 **MSA 애플리케이션의 구성 요소를 이해하고 사용 이유와 설계 방법을 숙지**하기 위해 프로젝트를 만들었습니다.

### 프로젝트 서비스 설명

책 이커머스 도메인 배경으로 [회원](https://github.com/yeahdy/spring-cloud-pratice/tree/spring-cloud-msa/user-service), [주문](https://github.com/yeahdy/spring-cloud-pratice/tree/spring-cloud-msa/order-service), [책 카탈로그](https://github.com/yeahdy/spring-cloud-pratice/tree/spring-cloud-msa/catalog-service), [쿠폰](https://github.com/yeahdy/spring-cloud-pratice/tree/spring-cloud-msa/coupon-service)이 있습니다.

| 서비스 | 기능 |
| --- | --- |
| user-service | - 회원가입 <br> - 로그인 <br> - 회원 목록 조회 <br> - 회원 정보 조회 |
| order-service | - 주문 등록 <br> - 회원 주문 조회 |
| catalog-service | - 책 목록 조회 |
| coupon-service | 쿠폰 등록 (user-service 와 통신 연결 예정) |

<br>

## 📌아키텍처 구성
※ 이미지 클릭 후 "Ctrl + 마우스휠↑" 을 통해 확대가 가능합니다.
![spring cloud MSA 아키텍처_watermark](https://github.com/yeahdy/spring-cloud-pratice/assets/86579541/fa8aed03-43a4-487f-bfff-ed5bf9eb0388)

<br>

## 📌Stacks

- **애플리케이션**
    - Spring boot (maven, gradle)
    - Java 17
    - Spring data JPA, Spring Security
    - Eureka Service Discovery, Spring Cloud Gateway, Spring Cloud Config, Spring Cloud Bus
- **인프라**
    - MariaDB
    - Docker
    - Kafka
    - RabbitMQ
- 모니터링
    - Zipkin
    - Prometheus, Grafana

<br>

### 학습 참고 강의

인프런 | Spring Cloud로 개발하는 마이크로서비스 애플리케이션(MSA)

인프런 | 실습으로 배우는 선착순 이벤트 시스템
