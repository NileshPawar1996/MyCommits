<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1" style="margin: auto; background-color: lightgrey;">
		<tr>
			<th>Id</th>
			<th>Category</th>
			<th>Action</th>
		</tr>
		<c:forEach var="cat" items="${sessionScope.category.allCategories}">
			<tr>
				<td>${cat.categoryId}</td>
				<td>${cat.title}</td>
				<td>${cat.description}</td>
			</tr>
		</c:forEach>
	</table>
	<form action="myBlogs.jsp">
		<input type="submit" value="Go To Home">
	</form>
</body>
</html>