<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<h2>Meals</h2>
<section>
    <table>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <c:set var="excessClass" value="${meal.excess ? 'excess': 'normal'}"/>
            <tr class="${excessClass}">
                <td>${meal.dateTime}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="/">Edit</a></td>
                <td><a href="/">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
