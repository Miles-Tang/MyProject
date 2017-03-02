<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check-in Counter page</title>
</head>
<body>
	<div id="container-fluid">

		<div id="header" style="background-color: #FFA500;">
			<center>
				<h1 style="margin-bottom: 0;">Welcome, Check-in Counter</h1>
			</center>
		</div>

		<div id="menu-fluid"
			style="background-color: #FFD700; height: 800px; width: 40%; margin: 0px; float: left;">
			<h2>Instruction</h2>
			<p>
				<B>Check the check-in status</B> : The check-in counter personnel
				inputs the passenger name and ID number to retrieve the passenger
				information and check-in status.

			</p>
			<p>
				<B>Update Check-in &amp; Boarding</B> : Each record of passenger table
				is linked to a passenger information and a flight code. So for each
				check-in or boarding process, the only thing needed is the index of
				the record. The personnel can get it at the "Check the Check-in
				Status" part, and use it to perform the check-in and boarding
				status.
			</p>
			<a href="welcome.jsp">Back to the main page</a><br>
		</div>

		<div id="content-fluid"
			style="background-color: #EEEEEE; height: 800px; width: 60%; margin: 0px; float: left;">

			<form name="form" action="CCservlet" method="post">

				<B><font size="4">Check Check-in status</font></B><br> Please
				input the passenger name, idnumber : <br> Name :
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text"
					name="name"><br> ID number : &nbsp;<input type="text"
					name="ID"><br> <input type="submit" name="Check"
					value="Find check-in"><br>Passenger id in database : <input
					type="text" name="pid"><br> <input type="submit"
					name="Updatecheckin" value="Update check-in"><br> <input
					type="submit" name="Updateboarding" value="Update Boarding">

				<%
					String passengerName = (String) session.getAttribute("passengerName");
					String passengerId = (String) session.getAttribute("passengerId");
					String[][] dataset = (String[][]) session.getAttribute("dataset");
				%>
				<br>The selected passenger Name :<%=passengerName%><br> ID
				number :<%=passengerId%><br>
				<%
					if (dataset != null) {
						int rownumber = dataset.length;
						for (int i = 0; i < rownumber; i++) {
							if (i == 0 && rownumber != 1) {
								out.print("Passenger id in database :" + dataset[i][0] + " |");
							} else if (i == 0 && rownumber == 1) {
								out.print("Passenger id in database :" + dataset[i][0]);
							} else if (i != 0 && i < rownumber - 1) {
								out.print(" " + dataset[i][0] + " |");
							} else {
								out.print(" " + dataset[i][0]);
							}
						}
					}
				%><br>
				<%
					if (dataset != null) {
						int rownumber = dataset.length;
						for (int i = 0; i < rownumber; i++) {
							if (i == 0 && rownumber != 1) {
								out.print("Flight taken by passenger :" + dataset[i][1] + " |");
							} else if (i == 0 && rownumber == 1) {
								out.print("Flight taken by passenger :" + dataset[i][1]);
							} else if (i != 0 && i < rownumber - 1) {
								out.print(" " + dataset[i][1] + " |");
							} else {
								out.print(" " + dataset[i][1]);
							}
						}
					}
				%><br>
				<%
					if (dataset != null) {
						int rownumber = dataset.length;
						for (int i = 0; i < rownumber; i++) {
							if (i == 0 && rownumber != 1) {
								out.print("Check-in status :" + dataset[i][2] + " |");
							} else if (i == 0 && rownumber == 1) {
								out.print("Check-in status :" + dataset[i][2]);
							} else if (i != 0 && i < rownumber - 1) {
								out.print(" " + dataset[i][2] + " |");
							} else {
								out.print(" " + dataset[i][2]);
							}
						}
					}
				%><br>
				<%
					if (dataset != null) {
						int rownumber = dataset.length;
						for (int i = 0; i < rownumber; i++) {
							if (i == 0 && rownumber != 1) {
								out.print("On board status :" + dataset[i][3] + " |");
							} else if (i == 0 && rownumber == 1) {
								out.print("On board status :" + dataset[i][3]);
							} else if (i != 0 && i < rownumber - 1) {
								out.print(" " + dataset[i][3] + " |");
							} else {
								out.print(" " + dataset[i][3]);
							}
						}
					}
				%>
			</form>


		</div>

		<div id="footer"
			style="background-color: #FFA500; clear: both; text-align: center;">
			Copyright © AODB</div>

	</div>
</body>
</html>