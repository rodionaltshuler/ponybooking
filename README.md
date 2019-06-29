## Time series data save & retrieve sample application

### How to run


#### 1. You need Docker installed in order to run PostgreSQL locally. Run from root project folder:

`docker-compose up`


#### 2. Start project: 

`./gradlew bootRun`

This will start server on http://localhost:3001, you can see endpoints available with
`http://localhost:3001/swagger-ui.html`


#### 3. Tests

Only integration tests for concurrent pony booking is included. You need to run Postgres container first (see 1) 
to run these tests.
