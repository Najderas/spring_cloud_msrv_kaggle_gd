# spring_cloud_msrv_kaggle_gd
GD Distributed systems and microservices architecture: Capstone project: Microservices on Spring Boot


### Build all modules:
`> ./mvnw clean package -DskipTests=true`

### Build single module:
`catalog-service> ./mvnw spring-boot:run`

`inventory-service> ./mvnw spring-boot:run`



catalog-service:

http://localhost:8181/api/catalog/

http://localhost:8181/api/catalog/id/2

http://localhost:8181/api/catalog/sku/345


inventory-service:

http://localhost:8282/api/inventory/2


