<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Arrival Management</title>
</head>
<body>
	<div id="container-fluid">

		<div id="header" style="background-color: #FFA500;">
			<center>
				<h1 style="margin-bottom: 0;">Welcome, Air traffic
					controller(Arrival)</h1>
			</center>
		</div>

		<div id="menu-fluid"
			style="background-color: #FFD700; height: 800px; width: 40%; margin: 0px; float: left;">
			<h2>Instruction</h2>
			<p>
				<B>Aircraft Status Update</B> : The ATC will receive signal from
				aircrafts and change the status of arrival flights.

			</p>
			<p>
				<B>Take-off Management</B> : The ATC will query for any arrival
				aircrafts with the "Ready to land" status sorted with earliest
				arrival time, then, if the weather permits, assign runway for the
				aircrafts. ATC will perform the same action again for the safe
				distance between landing flights (for about 5 minutes).
			</p>
			<a href="welcome.jsp">Back to the main page</a><br>
		</div>

		<div id="content-fluid"
			style="background-color: #EEEEEE; height: 800px; width: 60%; margin: 0px; float: left;">

			<form name="form" action="ArrivalServlet" method="post">

				<B><font size="4">Change Aircraft status</font></B><br> <font
					size="3">Find arrival id in database</font><br> Please input
				the flight code: <br> Flight code : <input type="text"
					name="flightcode"><br> <input type="submit"
					name="findaid" value="Find Arrival id">
				<%
					String flightcode = (String) session.getAttribute("flightcode");
					String aid = (String) session.getAttribute("aid");
					String astatus = (String) session.getAttribute("astatus");
				%>

				<br>The selected Flight code is :<%=flightcode%><br> id in
				the arrival table :<%=aid%><br>flight status :
				<%=astatus%>
				<br>id in arrival :<input type="text" name="aid"><br>
				<input type="radio" name="actors" value="En Route" checked>En
				Route <input type="radio" name="actors" value="Ready to land">Ready
				to land<br> <input type="radio" name="actors" value="Landing">Landing
				<input type="radio" name="actors" value="Landed">Landed <br>
				<input type="submit" name="updateflightstatus"
					value="Update Flight Status"> <br> <B><font
					size="4">Flight ready to land</font></B><br>Show flights which are
				ready to land with earliest arrival time: <br> at most 3 since
				three runways<br> <input type="submit" name="queryland"
					value="Show flight">
				<%
					String[][] dataset = (String[][]) session.getAttribute("landresult");
					int num;
					if (dataset != null) {
						num = dataset.length;
					} else {
						num = 0;
					}
				%><br>There are
				<%=num%>
				planes ready to land. Below are the detailed information :<br>
				<%
					if (dataset != null) {
						int rownumber = dataset.length;
						for (int i = 0; i < rownumber; i++) {
							if (i == 0 && rownumber != 1) {
								out.print("Flight id in database :" + dataset[i][0] + " |");
							} else if (i == 0 && rownumber == 1) {
								out.print("Flight id in database :" + dataset[i][0]);
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
								out.print("Flight code :" + dataset[i][1] + " |");
							} else if (i == 0 && rownumber == 1) {
								out.print("Flight code :" + dataset[i][1]);
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
								out.print("Arrival time :" + dataset[i][2] + " |");
							} else if (i == 0 && rownumber == 1) {
								out.print("Arrival time :" + dataset[i][2]);
							} else if (i != 0 && i < rownumber - 1) {
								out.print(" " + dataset[i][2] + " |");
							} else {
								out.print(" " + dataset[i][2]);
							}
						}
					}
				%><br> <B><font size="4">Weather information</font></B> <input
					type="submit" name="weather" value="Show weather">
				<%
					String[][] dataset2 = (String[][]) session.getAttribute("dataset");
					String weather = null;

					String temp = null;
					if (dataset2 != null) {
						if (dataset2[0][1].equals("1")) {
							temp = "Safe to land";
						} else {
							temp = "Dangerous to land";
						}
						weather = dataset2[0][0];
					}
				%><br> Weather type :
				<%=weather%>
				<br> decision :
				<%=temp%><br> <B><font size="4">Assign runway</font></B> <br>id
				in arrival :<input type="text" name="aid2"><br>runway
				number : <input type="text" name="runway"><br> <input
					type="submit" name="assignrunway" value="Assign runway">
			</form>


		</div>

		<div id="footer"
			style="background-color: #FFA500; clear: both; text-align: center;">
			Copyright © AODB</div>

	</div>
</body>
</html>