<%@ page language="java" contentType="application/json; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isErrorPage="true"%>
<%
	response.setStatus(200);
%>
{ "status" : "api key error!", "message" : "<%=exception.getMessage()%>"
}
