# Farzoom backend test

This project implements CRUD operations to work with Tasks that contains fields: id, name , description and date of task
creation/update. All data saved in Hashmap and being removed upon stopping or restarting application.

Project built with jdk11 and includes lombok library.

Curl command for testing: curl http://server-ip:8080/api/

### `GET /api/tasks`

response example :

```json
[
  {
    "id": 1,
    "name": "task 1",
    "description": "description for task 1",
    "date": "2022-03-31T09:08:41.361863900Z"
  },
  {
    "id": 2,
    "name": "task 2",
    "description": "description for task 2",
    "date": "2022-03-31T09:08:41.957969300Z"
  },
  {
    "id": 3,
    "name": "task 3",
    "description": "description for task 3",
    "date": "2022-03-31T09:08:42.390961900Z"
  }
]
```

Response : `200 OK`

For pagination you can use query parameters "page" and "size" either any of them or both together
request would look like GET api/tasks?page=0&size=5
If size is not set, 10 elements will be shown by default


### `GET /api/task/1`

Response example :

```json
  {
    "id": 1,
    "name": "task 1",
    "description": "description for task 1",
    "date": "2022-03-31T09:08:41.361863900Z"
  }
```

Response : `200 OK`

### `POST /api/task`

Body example :

```json
{
	"name": "task 1",
	"description": "description for task 1"
}
```

Response example :

```json
  {
    "id": 1,
    "name": "task 1",
    "description": "description for task 1",
    "date": "2022-03-31T09:08:41.361863900Z"
  }
```

Response : `200 OK`

### `PUT /api/task/1/update-name`

Body example :

```
task 1 new name
```

Response example :

```json
  {
    "id": 1,
    "name": "task 1 new name",
    "description": "description for task 1",
    "date": "2022-03-31T10:05:43.361863900Z"
  }
```

Response : `200 OK`

### `PUT /api/task/1/update-description`

Body example :

```
new description for task 1
```

Response example :

```json
  {
    "id": 1,
    "name": "task 1",
    "description": "new description for task 1",
    "date": "2022-03-31T10:05:43.361863900Z"
  }
```

Response : `200 OK`

### `DELETE /api/task/3`

Response : `200 OK`


In addition to whats being done, H2 database ,or any other, can be implemented. 