<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to AODB</title>
</head>
<body>
	<div
		style="position: absolute; width: 100%; height: 100%; z-index: -1; left: 0; top: 0;">
		<img src="image/cover.jpg" height="100%" width="100%"
			style="left: 0; top: 0;">
	</div>
	<center>
		<h1>Welcome to Airport Operation Database System</h1>
	</center>
	<center>
		<form name="form" action="RegisterServlet" method="post">
			<input type="text" name="username" id="username" value="username"
				onclick="if(this.value == 'username'){this.value = '';}else {this.select();}"
				onblur="if(this.value == ''){this.value = 'username'; }" /> <input
				type="password" name="password" id="password" value="password"
				onclick="if(this.value == 'password'){this.value = '';}else {this.select();}"
				onblur="if(this.value == ''){this.value = 'password'; }" /> <input
				type="password" name="repassword" id="repassword" value="password"
				onclick="if(this.value == 'password'){this.value = '';}else {this.select();}"
				onblur="if(this.value == ''){this.value = 'password'; }" /> <input
				type="submit" name="register" value="register"> <input
				type="reset" name="re" value="reset"><br>
			
			<input type="radio" name="actors" 
				value="ATC" checked>Air traffic controller <input type="radio"
				name="actors"  value="WIP">Weather
			information provider <br> <input type="radio" name="actors"
				 value="PIA">Passenger information
			administrator <input type="radio" name="actors"
				 value="CC">Check-in counter
		</form>
	</center>





</body>
</html>