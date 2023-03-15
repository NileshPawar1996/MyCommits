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
	addCategory.jsp
	<br>
	<table border="1" style="width: 100%;">
		<tr>
			<td colspan="2" style="text-align: center;">
				<h3>Categories</h3>
			</td>
		</tr>
		<tr>
			<td style="width: 200px; vertical-align: top;">
				<table style="height: 200px; margin-left: 10px;">
					<tr>
						<td><a href="myBlogs.jsp">My Blogs</a></td>
					</tr>
					<tr>
						<td><a href="allBlogs.jsp">All Blogs</a></td>
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

			<td>
				<form action="insertCategory.jsp">
					<table style="margin-left: 50px;">
						<tr>
							<td>Category Title: <input type="text" name="title"></td>
						</tr>
						<tr>
							<td>Category Description: <br /> <input type="text"
								name="description" style="height: 200px; width: 600px;"
								placeholder="Enter Category Description here!"></td>
						</tr>

						<tr>
							<td><input type="submit" value="Add Category"></td>
						</tr>
					</table>
				</form>
				<table style="margin-left: 200px; margin-top: 20px">
					<caption>All Categories:</caption>
					<tr>
						<th>Id</th>
						<th>Category</th>
						<th>Action</th>
					</tr>
					<c:forEach var="cat" items="${sessionScope.category.allCategories}">
						<tr>
							<td>${cat.categoryId}</td>
							<td>${cat.title}</td>
							<td>Button</td>
						</tr>
					</c:forEach>
				</table>
			</td>
	</table>
</body>
</html>