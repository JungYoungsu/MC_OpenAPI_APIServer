<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.multi.contactsapp.domain.*" %>
<%
	ContactList contactList = (ContactList)request.getAttribute("data");
	List<Contact> contacts = contactList.getContacts();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
	table.mytable { width:500px; }
	table.mytable > thead { 	background-color: aqua; }
	table.mytable > tbody  td { border-bottom: solid 1px gray; padding:3px 10px 3px 10px; }
	div.myinput { padding-bottom: 3px; }
	div.box { padding: 20px; }
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function() {
	$("#add").click(function() {
		$("#no").val("0");
		$("#f1")[0].submit();
	})
	
	$("#update").click(function() {
		$("#f1")[0].action = "update.do";
		$("#f1")[0].submit();
	})
})
</script>
</head>
<body>
	<div>
		<div class="box">
			<form id="f1" method="post" action="add.do">
					<div class="myinput">번호 : <input type="text" id="no" name="no" /></div>
					<div class="myinput">이름 : <input type="text" id="name" name="name" /></div>
					<div class="myinput">전화 : <input type="text" id="tel" name="tel" /></div>
					<div class="myinput">주소 : <input type="text" id="address" name="address" /></div>
			</form> 
			<button id="add">추가</button>
			<button id="update">수정</button>
		</div>
		<div  style="margin:10px 10px 10px 10px; ">
			<table class="mytable">
				<thead>
					<tr>
						<th>번호</th>
						<th>성명</th>
						<th>전화</th>
						<th>주소</th>
					</tr>
				</thead>
				<tbody>
				<% for (Contact c : contacts) { %>
					<tr>
						<td><%=c.getNo() %></td>
						<td><%=c.getName() %></td>
						<td><%=c.getTel() %></td>
						<td><%=c.getAddress() %></td>
					</tr>
				<% } %>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>