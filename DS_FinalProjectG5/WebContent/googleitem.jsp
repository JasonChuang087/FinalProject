<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NBASearch</title>
</head>
<body>

<div style="border:4px red solid;padding:10px;background-color:darkgoldenrod;font-family:DFKai-sb;vertical-align:middle">


<br>搜尋
<form action='${requestUri}' method='get'>
<img  src="https://miro.medium.com/max/2560/1*dwvP5WsDrgPLA7UpS0oVpw.jpeg"  width="160" height="90" style="vertical-align:middle;">
<input type='text' name='keyword' placeholder = 'keyword'/>

<input type='submit' value='submit' />

</form>
</div>
<div style="font-family:Times New Roman;border:5px #ccc solid;padding:10px;background-color:white	thistle">
<%
String[][] orderList = (String[][])  request.getAttribute("output");
for(int i =0 ; i < orderList.length;i++){%>
	<span style ="border:4px powderblue solid;font-color:coffee;font-size:20px ;font-family:DFKai-sb;"><a href='<%= orderList[i][1] %>'><%= orderList[i][0] %></a></span>
	<br><span style ="font-color:coffee;font-size:12px ;font-family:monospace;font-weight:bold;"><%= orderList[i][1] %></span>
	<br><br>
<%
}
%>
</div>
</body>
</html>