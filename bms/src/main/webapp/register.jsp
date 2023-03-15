<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome, Please register</title>
</head>
<body>
<h2>Authenticate User Page</h2>
	<jsp:useBean id="user" class="beans.UserBean" scope="session"></jsp:useBean>
	<jsp:setProperty property="*" name="user" />
	<c:redirect url="${sessionScope.user.registerUser()}.jsp"></c:redirect>
	<h2>After</h2>
</body>
</html>