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

http://localhost:8888/api/product/id/2

http://localhost:8888/api/product/id/9

http://localhost:8888/api/product/sku/345

http://localhost:8888/actuator/hystrix.stream,


### Eureka service-registry

http://localhost:8765/   user dashboard

http://localhost:8765/eureka    for services


### Hystrix dashboard

http://localhost:8989/hystrix 

