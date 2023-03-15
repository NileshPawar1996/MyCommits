<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="validate.jsp" method="post">
		<table style="background-color: lightgrey; margin: auto">
			<tr>
				<td>Enter Your Email</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Enter Your Password</td>
				<td><input type="password" name="password" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="Sign In" /></td>
				<td><input type="button" value="Sign Up"
					onclick="window.location.href='http://localhost:8080/bms/signup.jsp';"></td>
			</tr>
		</table>
	</form>

</body>
</html>