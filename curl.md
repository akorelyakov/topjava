####MealsGetAll:
curl --location --request GET 'http://localhost:8080/topjava/rest/meals'

####MealsGet:
curl --location --request GET 'http://localhost:8080/topjava/rest/meals/100006'

####MealsGetFiltered:
curl --location --request GET 'http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-31&startTime=01:30&endDate=2021-01-28&endTime=21:30'