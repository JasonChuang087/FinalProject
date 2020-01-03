<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NBASearch</title>
</head>
<body>
<%
String[][] orderList = (String[][])  request.getAttribute("sr");
for(int i =0 ; i < orderList.length;i++){%>
	<a href='http://google.com<%= orderList[i][1] %>'><%= orderList[i][0] %></a>
	<br><span style="font-size:12px ;font-family:monospace;"><%= orderList[i][1] %></span>
	<br><br>
<%
}
%>

<div style="background-color:gray;">相關搜尋<br>
<%
String[] relateList = (String[])  request.getAttribute("rs");
for(int i =0 ; i <  relateList.length;i++){%>
	<a href='http://localhost:8080/FinalProject/TestProject?keyword=<%= relateList[i] %>'><span style="font-size:20px ;font-family:DFKai-sb;"><%= relateList[i] %></span></a><br>
<%
}
%>

</div>
</body>
</html>