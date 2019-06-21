## Time series data save & retrieve sample application

### How to run


#### 1. You need Docker installed in order to run PostgreSQL locally. Run from root project folder:

`docker-compose up`


#### 2. Start project: 

`./gradlew bootRun`

This will start server on default port (http://localhost:8080), you can see endpoints available with
`http://localhost:8080/swagger-ui.html`

#### 3. Save and retrieve data

3.1 Save:

`curl -X POST \
  http://localhost:8080/save \
  -H 'Content-Type: application/json' \
  -d '{
"timestamp": 10001,
"value": "value1"
}'`

3.2 Retrieve

`curl -X GET 'http://localhost:8080?from=10000&to=10010'
`

#### If you want just run build with tests, use:

`./gradlew build`


 
