<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Weather information</title>
</head>
<body>

	<div id="container-fluid">

		<div id="header" style="background-color: #FFA500;">
			<center>
				<h1 style="margin-bottom: 0;">Welcome, Weather information
					Provider</h1>
			</center>
		</div>

		<div id="menu-fluid"
			style="background-color: #FFD700; height: 800px; width: 250px; float: left;">
			<a href="Weatherinfo.jsp">Weather information link</a><br> <a
				href="Weatherupdate.jsp">Weather update</a><br>
				<a href="welcome.jsp">Back to the main page</a><br>
		</div>

		<div id="content-fluid"
			style="background-color: #EEEEEE; height: 800px; float: left;">




			<form name="form" action="WeatherServlet" method="post">
				<input type="text" name="weather" id="weather" value="weather"
					onclick="if(this.value == 'weather'){this.value = '';}else {this.select();}"
					onblur="if(this.value == ''){this.value = 'weather'; }" /><input
					type="submit" name="update" value="update" 
					align="middle" /><br> <input type="radio" name="actors"
					value="1" checked>Safe to departure/land <input type="radio"
					name="actors" value="0">Dangerous to departure/land
			</form>
			<%
				String weather=(String) session.getAttribute("weather");
				String decision=(String)session.getAttribute("decision");
			%>
			<br> <span id="span1">You have updated the weather : <%=weather%> <br> your decision : <%=decision %></span>


		</div>

		<div id="footer"
			style="background-color: #FFA500; clear: both; text-align: center;">
			Copyright © AODB</div>

	</div>
</body>
</html>