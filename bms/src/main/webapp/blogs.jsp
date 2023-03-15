<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Namskar ${sessionScope.user.locatedUser.fullName}!!!!</h1>
	<table border="1" style="width: 100%;">

		<tr>
			<td colspan="2" style="text-align: center;">
				<h3>Blogging App</h3>
			</td>
		</tr>

		<tr>
			<td style="width: 200px; vertical-align: top;">
				<table style="height: 200px; margin-left: 10px;">
					<tr>
						<td><a href="myBlogs.jsp">My Blogs</a></td>
					</tr>
					<tr>
						<td><a href="myBlogs.jsp">All Blogs</a></td>
					</tr>
					<tr>
						<td><a href="addCategory.jsp">Add Category</a></td>
					</tr>
					<tr>
						<td><a href="showCategories.jsp">Show Categories</a></td>
					</tr>
					<tr>
						<td><a href="addBlog.jsp">Add Blog</a></td>
					</tr>
					<tr>
						<td><a href="searchBlogs.jsp">Search Blogs</a></td>
					</tr>
					<tr>
						<td><a href="signout.jsp">Logout</a></td>
					</tr>

				</table>
			</td>

			<td style="vertical-align: top;"><br>
				<h1></h1></td>
	</table>
	<jsp:useBean id="blog" class="beans.BlogBean" scope="session"></jsp:useBean>
	<jsp:useBean id="category" class="beans.CategoryBean" scope="session"></jsp:useBean>
</body>
</html>