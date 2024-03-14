# User

## Spring Boot Project

This is a Spring Boot project that includes the following routes:

### Ping

The ping route is used to check if the service is running correctly.

```
curl --location 'localhost:8080/api/ping'
```

Expected response:

```
pong
```

### Create user

The create route is used to create a new user.

```
curl --location 'localhost:8080/api/user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "ddd": 123,
    "phone": 4567890,
    "birthDate": "1990-01-01",
    "address": "123 Main St",
    "country": "UNITED_STATES",
    "cpf": 123456789,
    "passport": 987654321
}'
```

Request body:

```json
{
  "name": "John",
  "surname": "Doe",
  "email": "john.doe@example.com",
  "password": "password123",
  "ddd": 123,
  "phone": 4567890,
  "birthdate": "1990-01-01",
  "address": "123 Main St",
  "country": "UNITED_STATES",
  "cpf": 123456789,
  "passport": 987654321
}
```

Expected response:

```json
{
  "id": 103,
  "name": "John",
  "surname": "Doe",
  "email": "john.doe@example.com",
  "ddd": 123,
  "phone": 4567890,
  "birthdate": "1990-01-01",
  "address": "123 Main St",
  "country": "UNITED_STATES",
  "cpf": 123456789,
  "passport": 987654321
}
```

### Get

The get route is used to retrieve information about a specific user.

```
curl --location 'localhost:8080/api/user?id=1'
```

Expected response:

```json
{
  "results": [
    {
      "id": 1,
      "name": "John",
      "surname": "Doe",
      "email": "john.doe@example.com",
      "password": "password123",
      "ddd": 123,
      "phone": 4567890,
      "birthdate": null,
      "address": "123 Main St",
      "country": "UNITED_STATES",
      "cpf": 123456789,
      "passport": 987654321
    }
  ],
  "limit": 10,
  "offset": 0,
  "total": 1
}
```

Please replace `{id}` with the ID of the user you wish to retrieve.