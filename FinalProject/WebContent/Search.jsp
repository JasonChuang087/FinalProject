<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ page import="java.sql.*"%>
<title>NBASearch</title>
</head>
<body>
<div style="background-color:blue;font-size:40px ;font-family:Microsoft JhengHei;">關鍵字
<div style="border:2px;red solid;padding:10px;">
<form action='${requestUri}' method='get'>
<input type='text' name='keyword' placeholder = 'keyword'/>
<input type='submit' value='submit' />

</form>
</div>
</div>
<div style="background-color:white;"><span style="font-size:30px ;font-family:DFKai-sb;">快捷鍵<br></span></div>
<div style="background-color:red;"><a href="https://www.nba.com/?4"><img src="https://truth.bahamut.com.tw/s01/201210/643ced27266d7156785281b9bec5ece5.JPG" width="100" height="100"style="border:2px #ccc solid;padding:30px;"></a>
<a href="https://www.nba.com/lakers/"><img src="https://wallpaperaccess.com/full/1126209.jpg" width="100" height="100"style="border:2px #ccc solid;padding:30px;"></a>
<a href="https://www.nba.com/players/lebron/james/2544"><img src="https://images-na.ssl-images-amazon.com/images/I/91JL1XmxHQL._SX425_.jpg" width="100" height="100"style="border:2px #ccc solid;padding:30px;"></a>
</div>
</body>
</html>