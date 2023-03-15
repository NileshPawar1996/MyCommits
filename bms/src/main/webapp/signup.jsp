<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align: auto;">New User</h1>
	<div style="height: 100%; width: 100%; align: auto">
		<form action="register.jsp" method="post">
			<table style="border: 1px; solid black; border-collapse: collapse;">
				<tr>
					<td colspan="2" style="text-align: center;">Registration</td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="email" name="email"
						placeholder="Enter your email"></td>

				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"
						placeholder="Enter your password"></td>

				</tr>
				<tr>
					<td>Full name:</td>
					<td><input type="text" name="fullName"
						placeholder="Enter your Full Name"></td>

				</tr>

				<tr>
					<td>Phone No:</td>
					<td><input type="text" name="phoneNo"
						placeholder="Enter your Phone Number"></td>

				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="submit" value="Sign Up"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>