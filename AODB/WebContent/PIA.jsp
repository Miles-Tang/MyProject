<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Passenger Information</title>
</head>

<body>

	<div id="container-fluid">

		<div id="header" style="background-color: #FFA500;">
			<center>
				<h1 style="margin-bottom: 0;">Welcome, Passenger information
					administrator</h1>
			</center>
		</div>

		<div id="menu-fluid"
			style="background-color: #FFD700; margin: 0px; height: 800px; width: 40%; float: left;">
			<h2>Instruction</h2>

			<p>
				<B>Find Departure Flight Information</B> : By inputting a unique
				flight code, the system will return the code, its id stored in
				database, departure time, status and the name of its airline.

			</p>
			<p>
				<B>View Passenger Information</B> : By inputting the name and ID
				number (not the ID in the sense of primary key), the system can
				retrieve the full information of the passenger. If the passenger
				have two or more departure flights, the system will return an table
				with different flight codes.

			</p>
			<p>
				<B>Add, update and delete passenger information</B> : this part
				consists 4 fields and 3 buttons. First 3 fields are used by add and
				update for the newest information of a passenger. The last field is
				used by update and delete to find the index of the existing record,
				which need to be found by the "View Passenger information" part, to
				be updated or deleted. Of course, the three buttons perform those
				three operations.
			</p>
			<p><a href="welcome.jsp">Back to the main page</a></p>

		</div>

		<div id="content-fluid"
			style="background-color: #EEEEEE; margin: 0px; height: 800px; width: 60%; float: left;">

			<form name="form" action="PassengerServlet" method="post">
				<p>
					<B><font size="4">Find Flight information</font></B><br>
					Please input the flight code below : <br> Flight code : <input
						type="text" name="flightcode"><input type="submit"
						name="FlightInfo" value="FlightInfo" onclick=""><br>
					<%
						String flightcode = (String) session.getAttribute("flightcode");

						String[][] dataset = (String[][]) session.getAttribute("dataset");
						//String flightcode=(String)request.getParameter("flightcode");
					%>
					The selected flight code is
					<%=flightcode%>

					<br>
					<%
						if (dataset != null) {
							int rownumber = dataset.length;
							for (int i = 0; i < rownumber; i++) {
								if (i == 0 && rownumber != 1) {
									out.print("Departure flight id :" + dataset[i][0]
											+ " |");
								} else if (i == 0 && rownumber == 1) {
									out.print("Departure flight id :" + dataset[i][0]);
								} else if (i != 0 && i < rownumber - 1) {
									out.print(" " + dataset[i][0] + " |");
								} else {
									out.print(" " + dataset[i][0]);
								}
							}
						}
					%>
					<br>
					<%
						if (dataset != null) {
							int rownumber = dataset.length;
							for (int i = 0; i < rownumber; i++) {
								if (i == 0 && rownumber != 1) {
									out.print("Departure time :" + dataset[i][1] + " |");
								} else if (i == 0 && rownumber == 1) {
									out.print("Departure time : " + dataset[i][1]);
								} else if (i != 0 && i < rownumber - 1) {
									out.print(" " + dataset[i][1] + " |");
								} else {
									out.print(" " + dataset[i][1]);
								}
							}
						}
					%>
					<br>
					<%
						if (dataset != null) {
							int rownumber = dataset.length;
							for (int i = 0; i < rownumber; i++) {
								if (i == 0 && rownumber != 1) {
									out.print("Departure status    :" + dataset[i][2]
											+ " |");
								} else if (i == 0 && rownumber == 1) {
									out.print("Departure status    :" + dataset[i][2]);
								} else if (i != 0 && i < rownumber - 1) {
									out.print(" " + dataset[i][2] + " |");
								} else {
									out.print(" " + dataset[i][2]);
								}
							}
						}
					%>
					<br>
					<%
						if (dataset != null) {
							int rownumber = dataset.length;
							for (int i = 0; i < rownumber; i++) {
								if (i == 0 && rownumber != 1) {
									out.print("Airline name       :" + dataset[i][3] + " |");
								} else if (i == 0 && rownumber == 1) {
									out.print("Airline name       :" + dataset[i][3]);
								} else if (i != 0 && i < rownumber - 1) {
									out.print(" " + dataset[i][3] + " |");
								} else {
									out.print(" " + dataset[i][3]);
								}
							}
						}
					%>
				</p>
			</form>
			<form name="form" action="PassengerServlet2" method="post">
				<p>
					<B><font size="4">View passenger information</font></B><br>
					Please input the Passenger information below : <br> Name : <input
						type="text" name="name" /> ID : <input type="text" name="ID" />
					<input type="submit" name="search" value="search" align="middle" />
					<%
						String passengerName = (String) session
								.getAttribute("passengerName");
						String passengerId = (String) session.getAttribute("passengerId");
						String[][] dataset2 = (String[][]) session.getAttribute("dataset2");
					%>
					<br>The selected passenger Name :<%=passengerName%>
					and IDnumber :<%=passengerId%><br>
					<%
						if (dataset2 != null) {
							int rownumber = dataset2.length;
							for (int i = 0; i < rownumber; i++) {
								if (i == 0 && rownumber != 1) {
									out.print("Passenger id in database :" + dataset2[i][0]
											+ " |");
								} else if (i == 0 && rownumber == 1) {
									out.print("Passenger id in database :" + dataset2[i][0]);
								} else if (i != 0 && i < rownumber - 1) {
									out.print(" " + dataset2[i][0] + " |");
								} else {
									out.print(" " + dataset2[i][0]);
								}
							}
						}
					%><br>
					<%
						if (dataset2 != null) {
							int rownumber = dataset2.length;
							for (int i = 0; i < rownumber; i++) {
								if (i == 0 && rownumber != 1) {
									out.print("Flight taken by passenger :"
											+ dataset2[i][4] + " |");
								} else if (i == 0 && rownumber == 1) {
									out.print("Flight taken by passenger :"
											+ dataset2[i][4]);
								} else if (i != 0 && i < rownumber - 1) {
									out.print(" " + dataset2[i][4] + " |");
								} else {
									out.print(" " + dataset2[i][4]);
								}
							}
						}
					%>

				</p>
			</form>


			<form name="form" action="PassengerServlet3" method="post">
				<B><font size="4">Add, update and delete passenger
						information</font></B><br> New passenger name : <input type="text"
					name="name1" /><br>New passenger ID
				:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="ID1" /><br>New
				travelswith : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="text" name="travelswith" /><br>The passenger's id in
				the database to be changed<input type="text" name="passengerid" />
				<br> <input type="submit" name="add" value="add" align="middle" />
				<input type="submit" name="update" value="update" align="middle" />
				<input type="submit" name="delete" value="delete" align="middle" /><br>

			</form>



		</div>

		<div id="footer"
			style="background-color: #FFA500; clear: both; text-align: center;">
			Copyright © AODB</div>

	</div>
</body>

</html>