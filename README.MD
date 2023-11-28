JCDraft
==

## Сборка 

### Версия JAVA
Сборка и запуск проекта выполняется в среде JDK 17

### Сборка проекта
mvn clean install

### Локальный запуск
mvn compile exec:java -Dexec.mainClass=com.kdk.sberuniversity.host.Application

## Примеры запросов
Все примеры запросов выполняются с помощью встроенного в IDEA клиента HTTPClient

- Примеры запросов для сущности Car [link](./example-queries/Car.http)
- Примеры запросов для сущности Person [link](./example-queries/Person.http)
