# spring_cloud_msrv_kaggle_gd
GD Distributed systems and microservices architecture: Capstone project: Microservices on Spring Boot


### Build all modules:
`> ./mvnw clean package -DskipTests=true`

### Build single module:
`catalog-service> ./mvnw spring-boot:run`

`inventory-service> ./mvnw spring-boot:run`



### catalog-service:

http://localhost:8181/api/catalog/

http://localhost:8181/api/catalog/id/2

http://localhost:8181/api/catalog/sku/345


### inventory-service:

http://localhost:8282/api/inventory/2

http://localhost:8282/api/inventory/9


### product-service:

http://localhost:8888/api/product/id/2    returns null - 0 quantity for prodId 2

http://localhost:8888/api/product/id/9    returns valid product

http://localhost:8888/api/product/sku/345    returns 2 products (2 other hidden due to 0 in inventory)
http://localhost:8888/api/product/sku/567    20% timeouts (5 ok and 6th timeouts); returns 503 status
http://localhost:8888/api/product/sku/789    100% timeouts; returns 503 status


http://localhost:8888/actuator/hystrix.stream  for hystrix dashboard


### Eureka service-registry

http://localhost:8765/   user dashboard

http://localhost:8765/eureka    for services


### Hystrix dashboard

http://localhost:8989/hystrix 

