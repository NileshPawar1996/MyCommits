<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hiii ${sessionScope.user.locatedUser.fullName}!!!!</h1>
	${pageContext.session.invalidate()}
	<h3>
		You've been logged out. <a href="signin.jsp">Visit Again!</a>
	</h3>
</body>
</html>