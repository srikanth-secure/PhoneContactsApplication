<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
	function deleteConfirm() {
		return confirm("Are you sure, you want to delete ?");
	}
</script>
</head>
<body>
	<h2>View All Contacts</h2>
	<a href="/">+Add New Contact</a>
	<font color='green'>${succMsg}</font>
	<font color='red'>${errMsg}</font>
	<p></p>
	<table border="1">
		<tr>
			<th>Contact Name</th>
			<th>Contact Email</th>
			<th>Contact Number</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${contacts}" var="contact">
			<tr>
				<td>${contact.conName}</td>
				<td>${contact.conEmail}</td>
				<td>${contact.conNumber}</td>
				<td><a href="editContact?conId=${contact.conId }">Edit </a>| <a
					href="deleteContact?conId=${contact.conId }"
					onclick="return deleteConfirm()"> Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>