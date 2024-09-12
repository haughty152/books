<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import ="com.awd.bp.beans.Book"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Data</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
	<div class="row pt-5">

	<table class="table table-striped">
	<thead>
	<tr>
		<th>Title</th>
		<th>Author</th>
		<th>Publisher</th>
		<th>Edition</th>
		<th>Year</th>
		<th>Genre</th>
		<th>ISBN</th>
	</tr>
	</thead>

	<c:forEach var="buk" items="${Book_List}">
	
		<c:url var="displaylink" value="BookControllerServlet">
			<c:param name="identifier" value="${buk.bookID}"/>
			<c:param name="option" value="LOAD"/>
		</c:url>
		<c:url var="editlink" value="BookControllerServlet">
			<c:param name="identifier" value="${buk.bookID}"/>
			<c:param name="option" value="EDIT"/>
		</c:url>
		<c:url var="deletelink" value="BookControllerServlet">
			<c:param name="identifier" value="${buk.bookID}"/>
			<c:param name="option" value="DELETE"/>
		</c:url>
		<tr>
			<td> ${buk.tittle}</td>
			<td> ${buk.author}</td>
			<td> ${buk.publisher}</td>
			<td> ${buk.edition}</td>
			<td> ${buk.year}</td>
			<td> ${buk.genre}</td>
			<td> ${buk.ISBN}</td>
			<td> <a href="${displaylink}"><i class="bi bi-pip"></i></a></td>
			<td> <a href="${editlink}"><i class="bi bi-pencil-square"></i></a></td>
			<td> <a href="${deletelink}" onclick="if (!(confirm('Are you sure you want to delete this book?'))) return false"><i class="bi bi-trash"></i></a></td>
			
		</tr>
		
	
	</c:forEach>
	
	</table>
	</div>
 </div>
</body>
</html>
