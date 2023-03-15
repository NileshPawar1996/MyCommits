<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	addBlog.jsp


	<table border="1" style="width: 100%;">

		<tr>
			<td colspan="2" style="text-align: center;">
				<h3>Add Blog</h3>
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

			<td style="vertical-align: top;">
				<form action="insertBlog.jsp">
					<table style="height: 100%; width: 100%; margin-left: 50px;">
						<tr>
							<td>Title : <input type="text" name="title"> <input
								type="hidden" name="userId"
								value="${sessionScope.user.locatedUser.userId}">
							</td>
						</tr>
						<tr>
							<td>Contents :</td>
						</tr>
						<tr>
							<td><input type="text" name="contents"></td>
						</tr>
						<tr>
							<td>Category : <select name="categoryId">
									<option value="-1">Select Category</option>
									<option value="1">IT Boom</option>
									<option value="2">Technology</option>
									<option value="3">Movies</option>
									<option value="4">Cars</option>
									<option value="5">Wildlife</option>
							</select>
							</td>
						</tr>
						<tr>
							<td><input type="submit" value="Create"></td>
							<td><input type="button" value="Cancel"
								onclick="window.location.href='http://localhost:8080/bms/allBlogs.jsp';"></td>
						</tr>
					</table>
				</form>
			</td>
	</table>

</body>
</html>