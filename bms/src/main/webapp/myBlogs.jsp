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
	myBlogs.jsp

	
	<table border="1" style="width: 100%;">
		<tr>
			<td colspan="2" style="text-align: center;">
				<h3>All Blogs</h3>
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

				<table border="1">
					<tr>
						<th>Id</th>
						<th>Title</th>
						<th>Category</th>
						<th>Date</th>
						<th>Action</th>
					</tr>
					<c:forEach var="blogs" items="${sessionScope.blog.allBlogs}">
						<tr>
							<td>${blogs.blogId}</td>
							<td>${blogs.title}</td>
							<td>${blogs.categoryId}</td>
							<td>${blogs.createdTime}</td>
							<td>Buttons</td>
						<tr />
					</c:forEach>
				</table>
			</td>
	</table>
</body>
</html>