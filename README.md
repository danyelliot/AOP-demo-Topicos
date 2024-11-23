# AOP-demo-Topicos

# Grupo 4

## Dependencias

* Java JDK 15
* Maven:
    * `spring-boot-starter-aop`
    * `aspectjweaver`

## Uso

* Compilar y ejecutar el servicio web (configurar el puerto en `application.properties`):
```sh
mvn clean install
mvn spring-boot:run
```
* Ejemplo de uso (dependiendo del puerto configurado):
```sh
GET http://localhost:8081/api/greeting/{name}
GET http://localhost:8081/api/order/{menu}
```
