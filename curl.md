####MealsGetAll:
curl --location --request GET 'http://localhost:8080/topjava/rest/meals'

####MealsGet:
curl --location --request GET 'http://localhost:8080/topjava/rest/meals/100006'

####MealsGetFiltered:
curl --location --request GET 'http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-31&startTime=01:30&endDate=2021-01-28&endTime=21:30'

####MealsGetFilteredWithNullParams
curl --location --request GET 'http://localhost:8080/topjava/rest/meals/filter'

####MealsDelete
curl --location --request DELETE 'http://localhost:8080/topjava/rest/meals/100002'

####MealsUpdate
curl --location --request PUT 'http://localhost:8080/topjava/rest/meals/100002' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 100002,
"dateTime": "2020-01-30T01:11",
"description": "Новый завтрак",
"calories": 200
}'

####MealsCreate
curl --location --request POST 'http://localhost:8080/topjava/rest/meals' \
--header 'Content-Type: application/json' \
--data-raw '{
"dateTime": "2020-01-18T18:00",
"description": "Созданный ужин",
"calories": 300
}'