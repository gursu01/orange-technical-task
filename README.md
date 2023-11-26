# Steps to run the project
### 1. From root folder run in command line following commands:
- gradle flywayMigrate
- gradle run

The application will start on default port: 8080

<br>PostgreSQL version 12
<br>Spring Boot version 2.6.2
<br>JDK 11

### 2. Exposed Endpoints

##### API Operation insert new exchange rates
POST http://localhost:8080/api/v1/exchange-rates
<br>JSON Body example:
<br>{
<br>"currencyCode": "USD",
<br>"rate": 1,
<br>"conversionRate": 21.70
<br>}

<br>Returns 201 HttpStatus
<br>Returns 404 HttpsStatus with exception message

---------------------------------------------------------------------------------
##### API Operation fetch exchange rate for a currency for a given date
GET http://localhost:8080/api/v1/exchange-rates/currencies/{currencyCode}/dates/{date}

Returns 200 HttpStatus 

---------------------------------------------------------------------------------
##### API Operation to simulate an exchange, only MDL is accepted as source, target should exist currency dictionary. Also, a valid operator should exist.
POST http://localhost:8080/api/v1/exchange-currencies
JSON Body example:
<br>{
<br>"targetCurrencyCode": "MDL",
<br>"sourceCurrencyCode": "USD",
<br>"conversionRate": 20.00,
<br>"rate": 1,
<br>"receivedAmount": 100.00,
<br>"releasedAmount": 2000.00,
<br>"operatorId": 1
<br>}

<br>Returns 201 HttpStatus
<br>Returns 404 HttpsStatus with exception message

---------------------------------------------------------------------------------
### API Operation to simulate some cash corrections
PUT http://localhost:8080/api/v1/cash
<br>JSON Body example:
<br>{
<br>"operatorId": 1,
<br>"currencyCode": "EUR",
<br>"amount": 100.50,
<br>"date": "2023-11-26"
<br>}

<br>Returns 200 HttpStatus
<br>Returns 404 HttpsStatus with exception message

### 3. Database schema
![img.png](img.png)


#### Disclaimer
#### Project just represent some reactive operations and validations. Logic regarding exchange rate, validations is missing!
